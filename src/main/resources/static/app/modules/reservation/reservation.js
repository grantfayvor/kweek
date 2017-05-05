app.controller('ReservationController', ['$scope', '$rootScope', 'ReservationService', function ($scope, $rootScope, ReservationService){
        
    $scope.user_reservation = {};

    $scope.getCurrentReservation = function () {
        ReservationService.getUserReservation($rootScope.userId, function (response) {
            $scope.user_reservation = response.data;
        }, function (data, status) {
            console.log("error occured while fetching user reservations");
        });
    };

}]);

app.service('ReservationService', ['APIService', function (APIService) {
    
    var KWEEK_HOST = "http://localhost:9080";

    this.getUserReservation = function (userId, successHandler, errorHandler) {
        APIService.get(KWEEK_HOST + "/api/reservation/{" + userId + "}", successHandler, errorHandler);
    }; 

    this.getAllReservations = function (successHandler, errorHandler) {
        APIService.get(KWEEK_HOST + "/api/reservation/", successHandler, errorHandler);
    };

}]);