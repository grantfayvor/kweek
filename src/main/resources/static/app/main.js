app.controller('MainController', ['$scope', '$rootScope', '$cookies', 'MainService', function ($scope, $rootScope, $cookies, MainService) {

    $scope.passenger = {};
    $scope.availableDrivers = [];
    $scope.user_role = $cookies.get('user_role');

    $scope.initialize = function () {
        initMap();
    };

    $scope.callACab = function () {
        MainService.callACab(marker.getPosition().toJSON(), function (response) {
            $cookies.putObject("coordinates", marker.getPosition);
            console.log("congrats, nearby drivers have all been alerted");
        }, function (response, status) {
            console.log("an error occured while alerting nearby drivers");
        });
    };

    $scope.driverIsReady = function () {
        MainService.driverIsReady(marker.getPosition().toJSON(), true, function (response) {
            if (response.data) {
                console.log("yes dearie... driver is ready");
            } else {
                console.log("driver is not ready but still success callback");
            }
        }, function (response, status) {
            console.log("driver is not ready and did not reach success callback but instead reached error callback");
        });
    };

    $scope.findAvailableDrivers = function () {
        MainService.findAvailableDrivers(function (response) {
            $scope.availableDrivers = response.data;
        }, function (response, status) {
            console.log("sorry an error has occured");
        });
    };


    var initMap = function () {
        console.log("initMap function has just been called");
        google.maps.visualRefresh = true;
        var mapOptions = {
            center: new google.maps.LatLng(17.240498, 82.287598),
            zoom: 18,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("google-map"), mapOptions);
        // var geoMarker = new GeolocationMarker(map);
        var markerOptions = {
            position: map.getCenter(),
            map: map,
            animation: google.maps.Animation.Drop
        };
        marker = new google.maps.Marker(markerOptions);
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };

                marker.setPosition(pos);
                map.setCenter(pos);
                populateDrivers();
            }, function () {
                handleLocationError(true, infoWindow, map.getCenter());
            });
        } else {
            handleLocationError(false, infoWindow, map.getCenter);
        }
        var infoWindow = new google.maps.InfoWindow({
            content: "current center of the map"
        });
        google.maps.event.addListener(marker, 'click', function () {
            infoWindow.open(map, marker);
        });
    };

    var populateDrivers = function () {
        $scope.findAvailableDrivers();
        var drivers = [];
        for (var i = 0; i < $scope.availableDrivers.length; i++){
            drivers[i] = new google.maps.Marker({
            position: {
                lat: $scope.availableDrivers[i].lat,
                lng: $scope.availableDrivers[i].lng
            },
            map: map,
            animation: google.maps.Animation.BOUNCE,
            title: 'Hey! you can call for me...',
            icon: 'http://maps.google.com/mapfiles/ms/icons/blue-dot.png'
        });
        }
    };

    var handleLocationError = function (browserHasGeoLocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeoLocation ? "Error: The GeoLocation service failed." : "Error: Your browser does not support geolocation");
        infoWindow.open(map);
    };


}]);

app.service('MainService', ['APIService', function (APIService) {

    this.callACab = function (coordinates, successHandler, errorHandler) {
        APIService.post("/api/cab/call-a-cab", coordinates, successHandler, errorHandler);
    };

    this.driverIsReady = function (coordinates, readyState, successHandler, errorHandler) {
        APIService.post("/api/cab/driver?ready=" + readyState, coordinates, successHandler, errorHandler);
    };

    this.findAvailableDrivers = function (successHandler, errorHandler) {
        APIService.get("/api/cab/available-drivers", successHandler, errorHandler);
    };
}]);
