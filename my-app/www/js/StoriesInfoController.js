/**
 * Created by Lenovo on 2/2/2015.
 */
angular.module('app').controller('StoriesInfoController', function($scope, $data) {
    $scope.info = $data.selectedStory;
    $scope.isViewMore = false;
    $scope.getImage = $data.getImage;
    $scope.chapters=$data.selectedStory.chapters.chapter;
    $scope.getType = function(){
        var types = $scope.info.type;
        if(types == $data.defaultText){
            return types;
        }
        var result = "";
        for(index = 0; index < types.length; index++){
            result += index == (types.length-1) ? types[index] : types[index] + " | ";
        }
        return result;
    }
    $scope.viewMore = function(){
        if($scope.isViewMore){
            $('#desArea').addClass("desArea");
            $('#description').addClass("line-clamp");
            $('#viewMoreText').text("View More");
            $scope.isViewMore=false;
        }else{
            $scope.isViewMore=true;
            $('#desArea').removeClass("desArea");
            $('#description').removeClass("line-clamp");
            $('#viewMoreText').text("Collapse");
        }

    }
    if(!$scope.chapters.length){
        var tmp= '[' +JSON.stringify($scope.chapters)+']';
        $scope.chapters = JSON.parse(tmp);
    }
    $scope.viewChapter = function($index){
        menu.setSwipeable(false);
        $data.selectedChapter = $index;
        var imagelinks = $scope.chapters[$index].data
        $data.chapterImages = imagelinks.split('|');
        $scope.app.navi.home.pushPage('views/story-view.html');
    };
});