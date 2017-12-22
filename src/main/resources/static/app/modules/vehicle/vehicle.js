app.controller('VehicleController', ['$scope', '$rootScope', 'VehicleService', 'ReservationService', function ($scope, $rootScope, VehicleService, ReservationService) {

    $scope.new_vehicle = {};
    $scope.all_vehicles = [];
    $scope.all_Vehicle_categories = [];
    $scope.new_reservation = {};
    $scope.vehicle_details = {};
    $scope.page_title = "view-vehicles";
    $scope.reserved = 0;
    $scope.added = 0;

    $scope.initialize = function () {
        initDateRangePicker();
    };


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
        var payload = new FormData();
        payload.append("brand", $scope.new_vehicle.brand);
        payload.append("type", $scope.new_vehicle.type);
        payload.append("model", $scope.new_vehicle.model);
        payload.append("image", $scope.new_vehicle.image);
        payload.append("cost", $scope.new_vehicle.cost);
        payload.append("description", $scope.new_vehicle.description);
        VehicleService.addNewVehicle(payload, function (response) {
            $scope.new_vehicle = {};
            $scope.added = 1;
            console.log("vehicle has been added successfully");
            setTimeout(function () {
                $("#fade2").fadeOut(5000);
                $scope.added = 2;
            }, 1000);
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
            $scope.new_reservation = {};
            $scope.reserved = 1;
            console.log("added new reservation");
            setTimeout(function () {
                $("#fade1").fadeOut(5000);
                $scope.reserved = 2;
            }, 1000);
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

    var initDateRangePicker = function () {
        console.log("hey i just initialized daterangepicker");
        $('#myReservation').daterangepicker(null, function (start, end, label) {
            console.log(start.toISOString(), end.toISOString(), label);
        });
    };


}]);

app.service('VehicleService', ['APIService', '$http', function (APIService, $http) {


    this.addNewVehicle = function (vehicleDetails, successHandler, errorHandler) {
        // APIService.postWithIdentity('/api/vehicle/new-vehicle?image=' + payload, vehicleDetails, successHandler, errorHandler);
        $http.post('/api/vehicle/new-vehicle', vehicleDetails, {
            transformRequest: angular.identity,
            headers: {
                'Content-Type': undefined
            }
        }).then(successHandler, errorHandler);
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

app.directive("fileModel", ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);
