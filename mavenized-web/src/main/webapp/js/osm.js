var osm = angular.module('osm', []);
var controllers = {}
controllers.userController = function ($scope) {
                                 $scope.users = [
                                     {username : "admin"},
                                     {username : "user"},
                                     {username : "user1"}
                                 ];
                             };
osm.controller (controllers);