(function(){
    'use strict';
    var module = angular.module('app', ['onsen','ngAnimate', 'ngTouch']);

    module.controller('AppController', function($scope, $data) {

    });

    module.factory('$data', function($http) {
        var data = {};
        data.defaultText ="Đang cập nhật.."
        data.lang='all';
        data.wsUrl ='http://lazyeng.com:8080/xmlservice';
        data.limit=10;
        data.getImage = function(imgUrl){
            var defaultImageUrl="images/no-image.png";
            if(imgUrl==data.defaultText){
                return defaultImageUrl;
            }
            return imgUrl;
        }

        data.getDataByName=function(cb,searchKey,offset){
            if(searchKey){
                $http.get(data.wsUrl + '/getByName?limit=' + data.limit + '&offset=' + offset + '&name=' +searchKey).then(function(xml) {
                    var json = $.xml2json(xml.data);
                    cb(json);
                });
            }else{
                $http.get(data.wsUrl + '/getByName?limit=' + data.limit + '&offset=' + offset +'&name=').then(function(xml) {
                    var json = $.xml2json(xml.data);
                    cb(json);
                });
            }

        }
        data.getDataByType = function(cb,lang,offset){
            switch (lang){
                case 'Vietnamese':
                    $http.get(data.wsUrl + '/getIzManga?limit=' + data.limit + '&offset='+offset).then(function(xml){
                        var json = $.xml2json(xml.data);
                        cb(json);
                });
                    break;
                case 'English':
                    $http.get(data.wsUrl + '/getKissManga?limit=' + data.limit + '&offset='+offset).then(function(xml){
                        var json = $.xml2json(xml.data);
                        cb(json);
                    });
                    break;
                case 'Japanese':
                    $http.get(data.wsUrl + '/getMangaHead?limit=' + data.limit + '&offset='+offset).then(function(xml){
                        var json = $.xml2json(xml.data);
                        cb(json);
                    });
                    break;
            }
        }
        return data;
    });
})();

