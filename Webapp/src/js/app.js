/**
 * Created by khangtnse60992 on 2/6/2015.
 */
var app = angular.module("story", ['ngRoute']);
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            controller: 'IndexController',
            templateUrl: 'index.html'
        })
        .when('/view/:name', {
            controller: 'StoryController',
            templateUrl: 'view.html'
        })
        .when('/view/:name/:chapter', {
            controller: 'ChapterController',
            templateUrl: 'detail.html'
        })
        .when('/:type/:page', {
            controller: 'StoriesController',
            templateUrl: 'index.html'
        })
        .otherwise({
            redirectTo: '/'
        });
}])
app.controller("IndexController", function ($scope, $routeParams, StoriesFactory) {
    $scope.getLink = function(data) {
        return encodeURI(data);
    }
    var page = $routeParams.page;
    $scope.stories = [];
    StoriesFactory.getStoriesRequest(null, 0).then(function (data) {
        $scope.stories = data
    });
});
app.controller("ChapterController", function ($scope, $routeParams, StoriesFactory) {
    var name = $routeParams.name;
    var chapter = $routeParams.chapter;
    if (StoriesFactory.getStory() == null) {
        StoriesFactory.getStoryRequest(name).then(function (data) {
            $scope.story = data;
            imageProcess();
        });
    } else {
        $scope.story = StoriesFactory.getStory();
        imageProcess();
    }
    var imageProcess = function () {
        if ($scope.story.chapters.chapter.length > 0) {
            var flag = 0;
            for (var i = 0; i < $scope.story.chapters.chapter.length && flag == 0; i++) {
                if ($scope.story.chapters.chapter[i].name == name) {
                    $scope.chapter = $scope.story.chapters.chapter[i];
                    flag = 1;
                }
            }
        } else {
            $scope.chapter = $scope.story.chapters.chapter;
        }
        $scope.imageLinks = $scope.chapter.data.split('|');
    }

});
app.controller("StoryController", function ($scope, $routeParams, StoriesFactory) {
    var name = $routeParams.name;
    console.log(name);
    if (StoriesFactory.getStories().length == 0) {
        $scope.story = [];
        StoriesFactory.getStoryRequest(name).then(function (data) {
            $scope.story = data
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
    factory.getStoriesRequest = function (type, page) {
        var url = "http://lazyeng.com:8080/xmlservice/";
        var limit = 10;
        var offset = 10 * +page;
        if (type == null) {
            if (page == null) {
                page = 0;
            }
            url = url + "update?limit=" + limit + "&offset=" + offset;
        }
        return $http.get(url).then(function (response) {
            var json = $.xml2json(response.data);
            stories = json.story
            console.log(stories)
            if (stories.length == null) {
                var tmp = "[" + JSON.stringify(stories) + "]";
                stories = JSON.parse(tmp);
            }
            return stories;
        });
        return stories;
    };
    factory.getStories = function () {
        return stories;
    }
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

    return factory;
})