var map;
var marker;
//added something here
var lineCoords = [];
var pos = {
    lat: 6.247214,
    lng: 7.116212099999984
};

var app = angular.module('kweek', ['ngCookies', 'ui.router']);

app.config(['$httpProvider', '$locationProvider', '$cookiesProvider', '$stateProvider', '$urlRouterProvider',
    function ($httpProvider, $locationProvider, $cookiesProvider, $stateProvider, $urlRouterProvider) {

        $httpProvider.defaults.headers.common['Accept'] = "application/json";
        $httpProvider.defaults.headers.common['Content-Type'] = "application/json";
        $httpProvider.defaults.useXDomain = true;

        $cookiesProvider.defaults.path = "/";
        $cookiesProvider.defaults.secure = true;
        // $cookiesProvider.defaults.expires = fill in a date;

        $locationProvider.html5Mode(true);

        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('home', {
                url: '/',
                controller: 'MainController',
                templateUrl: '/app/home.html'
            })
            .state('view-car-suv', {
                url: '/vehicles/suv',
                controller: 'VehicleController',
                templateUrl: '/app/modules/vehicle/view-suv.html'
            })
            .state('view-car-all-terrain', {
                url: '/vehicles/all-terrain',
                controller: 'VehicleController',
                templateUrl: '/app/modules/vehicle/view-vehicles.html'
            })
            .state('view-car-salon', {
                url: '/vehicles/salon',
                controller: 'VehicleController',
                templateUrl: '/app/modules/vehicle/view-salon.html'
            })
            .state('new-vehicle', {
                url: '/new-vehicle',
                controller: 'VehicleController',
                templateUrl: '/app/modules/vehicle/new-vehicle.html'
            })
            .state('view-vehicles', {
                url: '/all-vehicles',
                controller: 'VehicleController',
                templateUrl: '/app/modules/vehicle/view-vehicles.html'
            })
            .state('new-reservation', {
                url: '/new-reservation',
                controller: 'ReservationController',
                templateUrl: '/app/modules/reservation/new-reservation.html'
            })
            .state('all-reservations', {
                url: '/all-reservations',
                controller: 'ReservationController',
                templateUrl: '/app/modules/reservation/view-reservations.html'
            })
            .state('user-profile', {
                url: '/user/profile',
                controller: 'UserController',
                templateUrl: '/app/modules/user/user-profile.html'
            })
            .state('reservation-report', {
                url: '/reports/reservation',
                controller: 'ReportController',
                templateUrl: '/app/modules/reports/reservation/report.html'
            })
            .state('users-report', {
                url: '/reports/users',
                controller: 'ReportController',
                templateUrl: '/app/modules/reports/users/report.html'
            })
            .state('cars-report', {
                url: '/reports/cars',
                controller: 'ReportController',
                templateUrl: '/app/modules/reports/cars/report.html'
            })
            .state('feedback', {
                url: '/user/feedback',
                controller: 'UserController',
                templateUrl: '/app/modules/user/feedback.html'
            })
            .state('new-driver', {
                url: '/new-driver',
                controller: 'UserController',
                templateUrl: '/app/modules/user/register-driver.html'
            });

    }]);