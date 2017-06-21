app.controller('VehicleController', ['$scope', '$rootScope', 'VehicleService', 'ReservationService', function ($scope, $rootScope, VehicleService, ReservationService) {

    $scope.new_vehicle = {};
    $scope.all_vehicles = [];
    $scope.all_Vehicle_categories = [];
    $scope.new_reservation = {};
    $scope.vehicle_details = {};
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

    $scope.makeReservation = function () {
        $scope.page_title = "make-reservation";
        $scope.new_reservation.vehicle = $scope.vehicle_details;
    };

    $scope.addNewReservation = function () {
        ReservationService.newReservation($scope.new_reservation, function (response) {
            console.log("added new reservation");
        }, function (data, status) {
            console.log("error in adding new reservation");
        });
    };

    $scope.showVehicleDetails = function (vehicle) {
        $scope.page_title = "vehicle-details";
        $scope.vehicle_details = vehicle;
    };

    $scope.showAllVehicles = function () {
        $scope.page_title = "view-vehicles";
    };


}]);

app.service('VehicleService', ['APIService', function (APIService) {


    this.addNewVehicle = function (vehicleDetails, successHandler, errorHandler) {
        APIService.post('/api/vehicle/new-vehicle', vehicleDetails, successHandler, errorHandler);
    };

    this.getAllVehicles = function (successHandler, errorHandler) {
        APIService.get('/api/vehicle', successHandler, errorHandler);
    };

    this.getAllVehicleCategories = function (successHandler, errorHandler) {
        APIService.get('/api/vehicle/all_types', successHandler, errorHandler);
    };

    this.findVehicle = function (vehicleDetail, successHandler, errorHandler) {
        APIService.get('/api/vehicle/{param}', successHandler, errorHandler);
    };

}]);