app.controller('UserController', ['$rootScope', '$scope', '$location', 'UserService', function ($rootScope, $scope, $location, UserService) {

    //remember to add the $location dependency in the UserController

    $scope.credentials = {};

    $scope.initialize = function () {
//        authenticate();
    };

}]);

app.service('UserService', ['APIService', function (APIService) {

}]);