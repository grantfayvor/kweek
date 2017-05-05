app.controller('MainController', ['$scope', '$rootScope', 'MainService', function ($scope, $rootScope, MainService) {

    $scope.cookies = {};
    $scope.all_vehicles = [];
    $scope.all_categories = [];

    $scope.initialize = function () {

    };
    $scope.getAllVehicles = function () {
        VehicleService.getAllVehicles(function (data) {
            $scope.all_vehicles = data;
        }, function (data, status, error) {

        });
    };

    $scope.getAllCategories = function () {

    };

    $scope.loginUser = function () {
        MainService.loginUser();
    };


}]);

app.service('MainService', ['APIService', function (APIService) {

    var KWEEK_HOST = "http://localhost:9080";

    this.loginUser = function (userData, notifyMsg) {
        APIService.post(KWEEK_HOST + 'api/user/login', userData, notifyMsg);
    };

    this.getHomePage = function (successHandler, errorHandler) {
        APIService.get(KWEEK_HOST + '/home', successHandler, errorHandler);
    };

}]);
