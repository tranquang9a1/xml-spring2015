/**
 * Created by Lenovo on 2/2/2015.
 */
angular.module('app').controller('ChapterDetailController',function($scope, $data){
    $scope.images = $data.chapterImages;

//    $scope.images = [
//        {image: 'images/img00.jpg', description: 'Image 00'},
//        {image: 'images/img01.jpg', description: 'Image 01'},
//        {image: 'images/img02.jpg', description: 'Image 02'},
//        {image: 'images/img03.jpg', description: 'Image 03'},
//        {image: 'images/img04.jpg', description: 'Image 04'}
//    ];


//    $scope.direction = 'left';
//    $scope.currentIndex = 0;
//
//    $scope.setCurrentSlideIndex = function (index) {
//        $scope.direction = (index > $scope.currentIndex) ? 'left' : 'right';0
//        $scope.currentIndex = index;
//    };
//
//    $scope.isCurrentSlideIndex = function (index) {
//        return $scope.currentIndex === index;
//    };
//
//    $scope.prevSlide = function () {
//        $scope.direction = 'left';
//        $scope.currentIndex = ($scope.currentIndex < $scope.images.length - 1) ? ++$scope.currentIndex : 0;
//    };
//
//    $scope.nextSlide = function () {
//        $scope.direction = 'right';
//        $scope.currentIndex = ($scope.currentIndex > 0) ? --$scope.currentIndex : 0;
//    };
//}).animation('.slide-animation', function () {
//    return {
//        addClass: function (element, className, done) {
//            var scope = element.scope();
//
//            if (className == 'ng-hide') {
//                var finishPoint = $(element.parent()).width();
//                if(scope.direction !== 'right') {
//                    finishPoint = -finishPoint;
//                }
//                TweenMax.to(element, 0.5, {left: finishPoint, onComplete: done });
//            }
//            else {
//                done();
//            }
//        },
//        removeClass: function (element, className, done) {
//            var scope = element.scope();
//
//            if (className == 'ng-hide') {
//                element.removeClass('ng-hide');
//
//                var startPoint = $(element.parention === 'right') {
//                    startPoint = -startPoint;
//                }
//
//                TweenMax.set(element, { left: startPoint });
//                TweenMax.to(element, 0.5, {left: 0, onComplete: done });
//            }t()).width();
//                if(scope.direc
//            else {
//                done();
//            }
//        }
//    };
});