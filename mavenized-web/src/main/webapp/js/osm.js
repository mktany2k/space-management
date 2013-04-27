angular.module('osm', []);

function userController($scope) {
    $scope.users = [
        {username : "admin"},
        {username : "user"},
        {username : "user1"}
    ];
}