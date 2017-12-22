app.controller('MainController', ['$scope', '$rootScope', '$cookies', '$http', 'MainService', function ($scope, $rootScope, $cookies, $http, MainService) {

    $scope.passenger = {};
    $scope.availableDrivers = [];
    $scope.user_role = $cookies.get('user_role');
    $scope.call = 0;
    $scope.status = 0;
    var pubnub;

    $scope.initialize = function () {
        initMap();

        /*setTimeout(function () {
            var pnChannel = "map-channel";
            if (PubNub !== undefined || PubNub) {
                pubnub = new PubNub({
                    publishKey: 'pub-c-88d0781d-6088-4982-b12c-85e42a852fb7',
                    subscribeKey: 'sub-c-5677dbd0-7e0a-11e7-ad83-5a7e2f01c2eb'
                });
            }
            pubnub.subscribe({ channels: [pnChannel] });
            pubnub.addListener({ message: redraw });
            setInterval(function () {
                pubnub.publish({
                    channel: pnChannel,
                    message: {
                        lat: pos.lat + 0.001,
                        lng: pos.lng + 0.01
                    }
                });
            }, 5000);
        }, 500);*/
    };

    $scope.showCabModal = function(){
        $('#modal1').modal('show');
    };

    $scope.callACab = function () {
        $('#modal1').modal('hide');
        MainService.callACab(marker ? marker.getPosition().toJSON() : pos, $scope.destination, function (response) {
            $cookies.putObject("coordinates", marker.getPosition);
            console.log("congrats, nearby drivers have all been alerted");
            $scope.call = 1;
            setTimeout(function () {
                $("#fade1").fadeOut(5000);
                $scope.call = 2;
            }, 1000);
        }, function (response, status) {
            console.log("an error occured while alerting nearby drivers");
        });
    };

    $scope.driverIsReady = function () {
        MainService.driverIsReady(marker.getPosition().toJSON(), true, function (response) {
            if (response.data === true) {
                console.log("yes dearie... driver is ready");
                $scope.status = 1;
                setTimeout(function () {
                    $("#fade2").fadeOut(5000);
                    $scope.status = 2;
                }, 1000);
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
        //confirm this code
        if (google === undefined || !google) {
            $http.get("https://maps.googleapis.com/maps/api/js?key=AIzaSyBC2k94EGV8e3uVNgElIEUSXa8X-Rfw8ZY&sensor=false");
        }
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
                //made pos a global object defined in config.js
                pos = {
                     lat: position.coords.latitude,
                     lng: position.coords.longitude
//                    lat: 6.247214,
//                    lng: 7.116212099999984
                };

                marker.setPosition(pos);
                map.setCenter(pos);
                populateDrivers();
                //added something here
//                lineCoords.push(new google.maps.LatLng(pos.lat, pos.lng));
            }, function () {
                handleLocationError(true, infoWindow, map.getCenter());
            }, {
                    maximumAge: 600000,
                    timeout: 5000,
                    enableHighAccuracy: true
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
        for (var i = 0; i < $scope.availableDrivers.length; i++) {
            drivers[i] = new google.maps.Marker({
                // position: $scope.availableDrivers[i],
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


    //added these next functions

    var redraw = function (payload) {
        pos.lat = payload.message.lat;
        pos.lng = payload.message.lng;

        map.setCenter(pos);
        marker.setPosition({ lat: pos.lat, lng: pos.lng, alt: 0 });

        lineCoords.push(new google.maps.LatLng(pos.lat, pos.lng));

        var lineCoordinatesPath = new google.maps.Polyline({ path: lineCoords, geodesic: true, strokeColor: '#2E10FF' });
        lineCoordinatesPath.setMap(map);
    };



}]);

app.service('MainService', ['APIService', function (APIService) {

    this.callACab = function (coordinates, destination, successHandler, errorHandler) {
        APIService.post("/api/cab/call-a-cab?destination=" +destination, coordinates, successHandler, errorHandler);
    };

    this.driverIsReady = function (coordinates, readyState, successHandler, errorHandler) {
        APIService.post("/api/cab/driver?ready=" + readyState, coordinates, successHandler, errorHandler);
    };

    this.findAvailableDrivers = function (successHandler, errorHandler) {
        APIService.get("/api/cab/available-drivers", successHandler, errorHandler);
    };
}]);
