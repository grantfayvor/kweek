app.controller('VehicleController', ['$scope', '$rootScope', 'VehicleService', function ($scope, $rootScope, VehicleService) {

    $scope.new_vehicle = {};
    $scope.all_vehicles = [];
    $scope.all_Vehicle_categories = [];
    $scope.new_reservation = {};
    $scope.page_title = "view-vehicles";


    $scope.getAllVehicles = function () {
        VehicleService.getAllVehicles(function (response) {
            $scope.all_vehicles = response.data;
        }, function (data, status) {
            console.log("get request unsuccessful");
        });
    };

    $scope.getAllVehicleCategories = function () {
        VehicleService.getAllVehicleCategories(function (response) {
            $scope.all_Vehicle_categories = response.data;
        }, function (data, status) {
            console.log("get request unsuccessful");
        });
    };

    $scope.addNewVehicle = function () {
        VehicleService.addNewVehicle($scope.new_vehicle, function (response) {
            console.log("vehicle has been added successfully");
        }, function (data, status) {
            console.log("vehicle addition failure");
        });
    };

    $scope.addNewReservation = function () {
        ReservationService.newReservation($scope.new_reservation, function (response) {
            console.log("added new reservation");
        }, function (data, status) {

        });
    };

    $scope.showVehicleDetails = function (vehicle) {
        $scope.page_title = "vehicle-details";
        $scope.vehicle_details = vehicle;
    };


}]);

app.service('VehicleService', ['APIService', function (APIService) {

    var KWEEK_HOST = "http://localhost:9080";

    this.addNewVehicle = function (vehicleDetails, successHandler, errorHandler) {
        APIService.post(KWEEK_HOST + '/api/vehicle/new-vehicle', vehicleDetails, successHandler, errorHandler);
    };

    this.getAllVehicles = function (successHandler, errorHandler) {
        APIService.get(KWEEK_HOST + '/api/vehicle', successHandler, errorHandler);
    };

    this.getAllVehicleCategories = function (successHandler, errorHandler) {
        APIService.get(KWEEK_HOST + '/api/vehicle/all_types', successHandler, errorHandler);
    };

    this.findVehicle = function (vehicleDetail, successHandler, errorHandler) {
        APIService.get(KWEEK_HOST + '/api/vehicle/{param}', successHandler, errorHandler);
    };

    this.newReservation = function (reservationDetails, successHandler, errorHandler) {
        APIService.post(KWEEK_HOST + "/api/reservation/new", reservationDetails, successHandler, errorHandler);
    };

}]);