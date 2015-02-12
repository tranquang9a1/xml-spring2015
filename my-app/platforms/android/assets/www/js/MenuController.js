/**
 * Created by Lenovo on 2/5/2015.
 */
angular.module('app').controller('MenuController', function($scope, $data){
    $scope.goHome = function(){
        menu.setMainPage('views/home.html', {closeMenu: true})
        $('#'+$data.lang).removeClass("selectedLang");
        $data.lang='all';
    }
    $scope.changePage= function(pageUrl,lang){
        menu.setMainPage(pageUrl,{closeMenu: true});
        $('#'+$data.lang).removeClass("selectedLang");
        $data.lang = lang;
        $('#'+lang).addClass("selectedLang");
    }
    $scope.isShowLangMenu= false;
});