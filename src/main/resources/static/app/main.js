app.controller('MainController', ['$scope', '$rootScope', 'MainService', function ($scope, $rootScope, MainService) {

    var map;

    $scope.initialize = function () {
        initMap();
    };


    var initMap = function () {
        console.log("initMap function has just been called");
        google.maps.visualRefresh = true;
        var mapOptions = {
            center: new google.maps.LatLng(17.240498, 82.287598),
            zoom: 13,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById("google-map"), mapOptions);
        // var geoMarker = new GeolocationMarker(map);
        var markerOptions = {
            position: map.getCenter(),
            map: map,
            animation: google.maps.Animation.BOUNCE
        };
        var marker = new google.maps.Marker(markerOptions);
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function (position) {
                var pos = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                };

                marker.setPosition(pos);
                map.setCenter(pos);
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

    var handleLocationError = function(browserHasGeoLocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeoLocation ? "Error: The GeoLocation service failed." : "Error: Your browser does not support geolocation");
        infoWindow.open(map);
    };


}]);

app.service('MainService', ['APIService', function (APIService) {

    var KWEEK_HOST = "http://localhost:9080";

}]);
