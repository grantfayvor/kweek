app.controller('ReservationController', ['$scope', '$rootScope', 'ReservationService', function ($scope, $rootScope, ReservationService) {

    $scope.user_reservations = [];

    $scope.initialize = function () {
        initDateRangePicker();
    };

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

    var initDateRangePicker = function () {
        console.log("hey i just initialized daterangepicker");
        $('#myReservation').daterangepicker(null, function(start, end, label) {
			console.log(start.toISOString(), end.toISOString(), label);
		});
    };

}]);

app.service('ReservationService', ['APIService', function (APIService) {

    this.getUserReservation = function (successHandler, errorHandler) {
        APIService.get("/api/reservation/user", successHandler, errorHandler);
    };

    this.getAllReservations = function (successHandler, errorHandler) {
        APIService.get("/api/reservation/", successHandler, errorHandler);
    };

    this.newReservation = function (reservation, successHandler, errorHandler) {
        APIService.post("/api/reservation/new-reservation", reservation, successHandler, errorHandler);
    };

    this.deleteReservation = function (reservationId, successHandler, errorHandler) {
        APIService.delete("/api/reservation/delete?id=" + reservationId, successHandler, errorHandler);
    };

}]);