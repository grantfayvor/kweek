var config = function () {
    this.kweek_host = 'http://localhost:9080';
    this.admin_host = 'http://localhost:9090';

    this.API_ENDPOINTS_HOST = [
        {
            id: "KWEEK_HOST",
            value: this.kweek_host
        },
        {
            id: "ADMIN_HOST",
            value: this.admin_host
        }
    ];
};

/*var map;

var loadMap = function () {
    console.log("loadMap function has just been called");
    google.maps.visualRefresh = true;
    var mapOptions = {
        center: new google.maps.LatLng(17.240498, 82.287598),
        zoom: 13,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    map = new google.maps.Map(document.getElementById("google-map"), mapOptions);
    var geoMarker = new GeolocationMarker(map);
    var markerOptions = {
        position: map.getCenter(),
        map: map,
        animation: google.maps.Animation.BOUNCE
    };
    var marker = new google.maps.Marker(markerOptions);
    var infoWindow = new google.maps.InfoWindow({
        content: "current location"
    });
    google.maps.event.addListener(marker, 'click', function () {
        infoWindow.open(map, marker);
    });
	if(navigator.geolocation){
		navigator.geolocation.getCurrentPosition(function(position){
			var pos = {
				lat: position.coords.latitude,
				lng: position.coords.longitude
			};

			marker.setPosition(pos);
			map.setCenter(pos);
		}, function(){
			handleLocationError(true, infoWindow, map.getCenter());
		});
	} else{
		handleLocationError(false, infoWindow, map.getCenter);
	}
};*/

/*function handleLocationError(browserHasGeoLocation, infoWindow, pos){
	infoWindow.setPosition(pos);
	infoWindow.setContent(browserHasGeoLocation ? "Error: The GeoLocation service failed." : "Error: Your browser does not support geolocation");
	infoWindow.open(map);
};*/

// loadMap();

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