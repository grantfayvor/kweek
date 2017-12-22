var app = angular.module('kweek-ride', ['ngCookies']);

app.config(['$httpProvider', '$cookiesProvider', function ($httpProvider, $cookiesProvider) {
	$httpProvider.defaults.headers.common['Accept'] = "application/json";
	$httpProvider.defaults.headers.common['Content-Type'] = "application/json";
	$httpProvider.defaults.useXDomain = true;

	$cookiesProvider.defaults.path = "/";
	$cookiesProvider.defaults.secure = true;
	// $cookiesProvider.defaults.expires = fill in a date;
}]);

app.controller('RideController', ['$scope', function($scope){

    $scope.acceptTheRide = function(){

    };

    $scope.declineTheRide = function(){

    };

}]);

app.service('RideService', ['APIService', function(APIService){

    this.acceptRide = function(rideInfo, successHandler, errorHandler){

    };

    this.declineRide = function(rideInfo, successHandler, errorHandler){

    };
}]);


app.service('APIService', ['$http', function ($http) {

	var KWEEK_HOST = "http://localhost:9080";

	this.get = function (url, successHandler, errorHandler) {
		$http.get(KWEEK_HOST + url)
			.then(successHandler, errorHandler);
	};

	this.post = function (url, data, successHandler, errorHandler) {
		$http.post(KWEEK_HOST + url, data)
			.then(successHandler, errorHandler);
	};

}]);