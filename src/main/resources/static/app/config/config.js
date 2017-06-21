var map;
var marker;

var app = angular.module('kweek', ['ngCookies', 'ui.router']);

app.config(['$httpProvider', '$cookiesProvider', '$stateProvider', '$urlRouterProvider',
    function ($httpProvider, $cookiesProvider, $stateProvider, $urlRouterProvider) {

        $httpProvider.defaults.headers.common['Accept'] = "application/json";
        $httpProvider.defaults.headers.common['Content-Type'] = "application/json";
        $httpProvider.defaults.useXDomain = true;

        $cookiesProvider.defaults.path = "/";
        $cookiesProvider.defaults.secure = true;
        // $cookiesProvider.defaults.expires = fill in a date;

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
                url: '/vehicle/new-vehicle',
                controller: 'VehicleController',
                templateUrl: '/app/modules/vehicle/new-vehicle.html'
            })
            .state('view-vehicles', {
                url: '/vehicle/all-vehicles',
                controller: 'VehicleController',
                templateUrl: '/app/modules/vehicle/view-vehicles.html'
            })
            .state('new-reservation', {
                url: '/reservation/new-reservation',
                controller: 'ReservationController',
                templateUrl: '/app/modules/reservation/new-reservation.html'
            })
            .state('all-reservations', {
                url: '/reservation/all-reservations',
                controller: 'ReservationController',
                templateUrl: '/app/modules/reservation/view-reservations.html'
            })
            .state('user-profile', {
                url: '/user/profile',
                controller: 'UserController',
                templateUrl: '/app/modules/user/user-profile.html'
            });

    }]);