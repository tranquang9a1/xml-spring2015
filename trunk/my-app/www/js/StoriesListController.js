/**
 * Created by Lenovo on 2/2/2015.
 */



angular.module('app').controller('StoriesListController', function($scope, $data, $timeout) {
    modal.show();
    $scope.isEmpty=false;
    $scope.hideModel = function (){
        modal.hide();
        if ($scope.items.length==0){
            $scope.isEmpty=true;
            $('#errorText').html("There are some problem with network.<br> Please try again");
        }
    }
    $timeout($scope.hideModel,10000);
    $scope.lang = $data.lang;
    $scope.searchable = $scope.lang!='all'? true :false;
    if(!$scope.offset){
        $scope.offset=0;
    }
    if(!$scope.items){
        $scope.items=[];
    }
    $scope.searchKey="";
    $data.getDataCb = function(json){
        $scope.isEmpty= false;
        modal.hide();
        if (json==""){
            $('#btn_loadMore').text('There is no story available');
            return;
        }
        $scope.items= $scope.offset==0?json.story:$scope.items.concat(json.story);
        if(!$scope.items.length){
            var tmp= '[' +JSON.stringify($scope.items)+']';
            $scope.items = JSON.parse(tmp);
        }
        $scope.getImage = $data.getImage;
        $data.items = $scope.items;
        $scope.showDetail = function(index) {
            if (menu.isMenuOpened()){
                menu.closeMenu();
            }
            var selectedStory = $data.items[index];
            $data.selectedStory = selectedStory;
            $scope.ons.navigator.pushPage('views/story-info.html');
        };
        return json.story;
    }
    if($scope.lang==='all'){
        $data.getDataByName($data.getDataCb,$scope.searchKey,$scope.offset);
    }else{
        $data.getDataByType($data.getDataCb,$scope.lang,$scope.offset)
    }


    $scope.getDataByName = function(){
        $timeout(function(){
            $scope.offset = 0;
            $data.getDataByName($data.getDataCb,$scope.searchKey,$scope.offset);
        },1000);
    }
    $scope.getMoreData = function(){
        $scope.offset +=$data.limit;
        if ($scope.lang=='all'){
            $data.getDataByName($data.getDataCb,$scope.searchKey,$scope.offset);
        }else{
            $data.getDataByType($data.getDataCb,$scope.lang,$scope.offset)
        }

    }
}).directive('whenScrolled', function() {
    return function(scope, elm, attr) {
        var raw = elm[0];
        var scrollElm = raw.children[1];

        $(scrollElm).scroll(function() {
            if (raw.scrollTop + raw.offsetHeight >= raw.scrollHeight) {
                scope.$apply(attr.whenScrolled);
            }
        });
    };
});