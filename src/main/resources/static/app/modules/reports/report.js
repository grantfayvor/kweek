app.controller('ReportController', ['$scope', '$rootScope', 'ReportService', function ($scope, $rootScope, ReportService) {

    $scope.reservations = [];

    $scope.initialize = function () {
//        $scope.getAllReservations();
    };

    $scope.getAllReservations = function () {
        ReportService.getAllReservations(function (response) {
            $scope.reservations = response.data;
        }, function (data, status) {
            console.log("error occured while fetching all reservations");
        });
    };

    $scope.deleteReservation = function (reservationId) {
        ReportService.deleteReservation(reservationId, function (response) {
            console.log("reservation has been deleted");
            $scope.getAllReservations();
        }, function (response, status) {
            console.log("error deleting new reservation");
        });
    };

    $scope.getAllUsers = function () {
        ReportService.getAllUsers(function (response) {
            $scope.users = response.data;
        }, function(data, status){
            console.log("error occured while fetching registered users");
        });
    };

    $scope.deleteUser = function (userId) {
        ReportService.deleteUser(userId, function (response) {
            console.log("user has been deleted");
            $scope.getAllUsers();
        }, function (response, status) {
            console.log("error occured while deleting user");
        });
    };

    $scope.getAllVehicles = function () {
        ReportService.getAllVehicles(function (response) {
            $scope.vehicles = response.data;
        }, function(data, status){
            console.log("error occured while fetching available vehicles");
        });
    };

    $scope.deleteVehicle = function (vehicleId) {
        ReportService.deleteVehicle(vehicleId, function (response) {
            console.log("vehicle has been deleted");
            $scope.getAllVehicles();
        }, function (response, status) {
            console.log("error occured while deleting vehicle");
        });
    };

}]);

app.service('ReportService', ['APIService', function (APIService) {

    this.getAllReservations = function (successHandler, errorHandler) {
        APIService.get("/api/reservation/", successHandler, errorHandler);
    };

    this.deleteReservation = function (reservationId, successHandler, errorHandler) {
        APIService.delete("/api/reservation/delete?id=" + reservationId, successHandler, errorHandler);
    };

    this.getAllUsers = function(successHandler, errorHandler){
        APIService.get("/api/user", successHandler, errorHandler);
    };

    this.deleteUser = function(userId, successHandler, errorHandler){
        APIService.delete("/api/user/delete?id=" +userId, successHandler, errorHandler);
    };

    this.getAllVehicles = function(successHandler, errorHandler){
        APIService.get("/api/vehicle", successHandler, errorHandler);
    };

    this.deleteVehicle = function(vehicleId, successHandler, errorHandler){
        APIService.delete("/api/vehicle/delete?id=" +vehicleId, successHandler, errorHandler);
    };

}]);