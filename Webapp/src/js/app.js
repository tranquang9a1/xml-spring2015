/**
 * Created by khangtnse60992 on 2/6/2015.
 */
var app = angular.module("story", ['ngRoute']);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'IndexController',
            templateUrl: 'home.html'
        })
        .when('/view/:name', {
            controller: 'StoryController',
            templateUrl: 'view.html'
        })
        .when('/view/:name/:chapter', {
            controller: 'ChapterController',
            templateUrl: 'detail.html'
        })
        .when('/:typeManga/:page', {
            controller: 'StoriesController',
            templateUrl: 'home.html'
        })
        .otherwise({
            redirectTo: '/'
        });
}])
app.controller("IndexController", function ($scope, $routeParams, StoriesFactory) {
    $scope.getLink = function (data) {
        return encodeURI(data);
    }
//    var page = $routeParams.page;
    $scope.stories = [];

    if (StoriesFactory.getStories().length == 0 || StoriesFactory.getType() != null) {
        $scope.loading = true;
        StoriesFactory.getStoriesRequest(null, 0).then(function (data) {
            $scope.stories = data
            $scope.loading = false;
        });
    } else {
        $scope.stories = StoriesFactory.getStories();
    }
    $scope.addMore = function () {
        var page = +StoriesFactory.getPage() + 1;
        $scope.loading = true;
        StoriesFactory.getStoriesRequest(null, page).then(function (data) {
            $scope.stories = data
            $scope.loading = false;
        });
    };
});
app.controller("StoriesController", function ($scope, $routeParams, StoriesFactory) {
    $scope.getLink = function (data) {
        return encodeURI(data);
    }
    var page = $routeParams.page;
    var type = $routeParams.typeManga;
    $scope.stories = [];
    if (StoriesFactory.getStories().length == 0 || StoriesFactory.getType() != type || StoriesFactory.getType() == null) {
        StoriesFactory.resetPage();
        $scope.loading = true;
        StoriesFactory.getStoriesRequest(type, page).then(function (data) {
            $scope.stories = data
            $scope.loading = false;
        });
    } else {
        $scope.stories = StoriesFactory.getStories();
    }
    $scope.addMore = function () {
        page = +StoriesFactory.getPage() + 1;
        $scope.loading = true;
        StoriesFactory.getStoriesRequest(type, page).then(function (data) {
            $scope.stories = data
            $scope.loading = false;
        });
    };
});
app.controller("SearchController",function($scope,StoriesFactory) {
    $scope.searchText ="";
    $scope.searchResult = [];
    $scope.search = function(){
        StoriesFactory.getSearchRequest($scope.searchText).then(function (data) {
            $scope.searchResult = data
        });
    }
});
app.controller("ChapterController", function ($scope, $routeParams, StoriesFactory) {
    var name = $routeParams.name;
    var chapter = $routeParams.chapter;
    var imageProcess = function () {
        if (StoriesFactory.getStory().chapters.chapter.length > 0) {
            var flag = 0;
            for (var i = 0; i < StoriesFactory.getStory().chapters.chapter.length && flag == 0; i++) {
                if (StoriesFactory.getStory().chapters.chapter[i].name == chapter) {
                    $scope.chapter = StoriesFactory.getStory().chapters.chapter[i];
                    flag = 1;
                }
            }
        } else {
            $scope.chapter = StoriesFactory.getStory().chapters.chapter;
        }
        $scope.imageLinks = $scope.chapter.data.split('|');
    }
    if (StoriesFactory.getStory() == null) {
        $scope.loading = true;
        StoriesFactory.getStoryRequest(name).then(function (data) {
            $scope.story = data;
            $scope.loading = false;
            $scope.item = $scope.story.chapters.chapter[0];
            imageProcess();
        });
    } else {
        $scope.story = StoriesFactory.getStory();
        imageProcess();
    }
    $scope.selectChapter = function ($index) {
        var story = StoriesFactory.getStory();
        var name = story.name;
        var chapter = $scope.item.name;
        var url = "#/view/" + name + "/" + chapter;
        window.open(url);
    }

});
app.controller("StoryController", function ($scope, $routeParams, StoriesFactory) {
    var name = $routeParams.name;
    console.log(name);
    if (StoriesFactory.getStories().length == 0) {
        $scope.story = [];
        $scope.loading = true;
        StoriesFactory.getStoryRequest(name).then(function (data) {
            $scope.story = data
            $scope.loading = false;
        });
    } else {
        var tmp = StoriesFactory.getStories();
        var flag = 0;
        for (var i = 0; i < tmp.length && flag == 0; i++) {
            if (tmp[i].name == name) {
                $scope.story = tmp[i];
                console.log(tmp[i])
                flag = 1;
            }
        }
    }
});
app.factory("StoriesFactory", function ($http) {
    var stories = [];
    var factory = {};
    var story;
    var pageFlag;
    var typeFlag;
    factory.getStoriesRequest = function (type, page) {
        var url = "http://lazyeng.com:8080/xmlservice/";
        var limit = 10;
        var offset = 10 * +page;
        if (type == null) {
            if (page == null) {
                page = 0;
            }
            url = url + "update?limit=" + limit + "&offset=" + offset;
        } else if (type == "vn") {
            url = url + "getIzManga?limit=" + limit + "&offset=" + offset;
        } else if (type == "en") {
            url = url + "getKissManga?limit=" + limit + "&offset=" + offset;
        } else if (type == "jp") {
            url = url + "getMangaHead?limit=" + limit + "&offset=" + offset;
        }
        return $http.get(url).then(function (response) {
            console.log(response.data)
            var json = $.xml2json(response.data);
            if (json != "") {
                var tmp = json.story
                console.log(stories)
                if (tmp.length == null) {
                    tmp = "[" + JSON.stringify(tmp) + "]";
                    if (stories.length == 0) {
                        stories = JSON.parse(tmp);
                    } else {
                        stories.push.apply(stories, tmp);
                    }
                } else {
                    if (stories.length == 0 || typeFlag != type) {
                        stories = tmp;
                    } else {
                        if (stories[0].name != tmp[0].name) {
                            stories.push.apply(stories, tmp);
                        }
                    }
                }
                pageFlag = page;
                typeFlag = type;
                return stories;
            } else {
                return stories;
            }
        });
        return stories;
    };
    factory.getStories = function () {
        return stories;
    }
    factory.getSearchRequest = function(keyword){
        var url = "http://lazyeng.com:8080/xmlservice/getByName?name=" + keyword + "&limit=12&offset=0";
        return $http.get(url).then(function (response) {
            var json = $.xml2json(response.data);
            if (json != "") {
                var tmp = json.story
                if (tmp.length == null) {
                    tmp = "[" + JSON.stringify(tmp) + "]";
                }
                listSearch = tmp;
            }
            return listSearch
        });
        return listSearch
    };
    factory.getStoryRequest = function (name) {
        var url = "http://lazyeng.com:8080/xmlservice/getStory?name=" + name;
        return $http.get(url).then(function (response) {
            var json = $.xml2json(response.data);
            story = json.story
            console.log(story)
            return story;
        });
        return story;
    }
    factory.getStory = function () {
        return story;
    }
    factory.getPage = function () {
        return pageFlag;
    }
    factory.getType = function () {
        return typeFlag;
    }
    factory.resetPage = function () {
        pageFlag = 0;
    }
    return factory;
})
app.directive('loading', function () {
    return {
        restrict: 'E',
        replace:true,
        template: '<div id="circularG" style="margin: auto">' +
        '<div id="circularG_1" class="circularG">'+
        '</div><div id="circularG_2" class="circularG">'+
            '</div><div id="circularG_3" class="circularG">'+
            '</div><div id="circularG_4" class="circularG">'+
            '</div><div id="circularG_5" class="circularG">'+
            '</div><div id="circularG_6" class="circularG">'+
            '</div><div id="circularG_7" class="circularG">'+
            '</div><div id="circularG_8" class="circularG"></div></div>',
        link: function (scope, element, attr) {
            scope.$watch('loading', function (val) {
                if (val)
                    $(element).show();
                else
                    $(element).hide();
            });
        }
    }
})