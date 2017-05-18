app.controller('ReservationController', ['$scope', '$rootScope', 'ReservationService', function ($scope, $rootScope, ReservationService){
        
    $scope.user_reservations = [];

    $scope.getUserReservation = function () {
        ReservationService.getUserReservation(function (response) {
            $scope.user_reservations = response.data;
        }, function (data, status) {
            console.log("error occured while fetching user reservations");
        });
    };

    $scope.deleteReservation = function (reservationId) {
        ReservationService.deleteReservation(reservationId, function (response) {
            console.log("reservation has been deleted");
            $scope.getUserReservation();
        }, function (response, status) {
            console.log("error deleting new reservation");
        });
    };

}]);

app.service('ReservationService', ['APIService', function (APIService) {
    
    var KWEEK_HOST = "http://localhost:9080";

    this.getUserReservation = function (successHandler, errorHandler) {
        APIService.get(KWEEK_HOST + "/api/reservation/user", successHandler, errorHandler);
    }; 

    this.getAllReservations = function (successHandler, errorHandler) {
        APIService.get(KWEEK_HOST + "/api/reservation/", successHandler, errorHandler);
    };

    this.newReservation = function (reservation, successHandler, errorHandler) {
        APIService.post(KWEEK_HOST + "/api/reservation/new-reservation", reservation, successHandler, errorHandler);
    };

    this.deleteReservation = function (reservationId, successHandler, errorHandler) {
        APIService.delete(KWEEK_HOST + "/api/reservation/delete?id="+ reservationId, successHandler, errorHandler);
    };

}]);