var app = angular.module('kweek-login', ['ngCookies']);

app.controller('LoginController', ['$scope', '$rootScope', '$cookies', 'LoginService', function (
	$scope, $rootScope, $cookies, LoginService) {

	var KWEEK_HOST = "http://localhost:9080";

	$scope.credentials = {};
	$scope.new_user = {};
	$scope.login_error = false;
	$scope.registration_error = false;

	$scope.login = function () {
		LoginService.login($scope.credentials, function (response) {
			if(response.data){
				window.location.href = KWEEK_HOST + "/";
			} else{
				$scope.login_error = true;
				$scope.login_error_message = "Invalid username or password";
			}
		}, function (response, status) {
			$scope.login_error = true;
			$scope.login_error_message = "A network error has occured. Please try again later";
		});
	};

	$scope.registerUser = function () {
		LoginService.registerUser($scope.new_user, function (response) {
			/*if (response) {
				window.location.href = KWEEK_HOST + "/";
			} else {
				window.location.href = KWEEK_HOST + "/login";
			}*/
		}, function (response, status) {
			$scope.registration_error = true;
			$scope.registration_error_message = "A network error has occured. Please try registering again.";
		});
	};

}]);

app.service('LoginService', ['APIService', function (APIService) {

	var KWEEK_HOST = "http://localhost:9080";

	/*this.login = function (credentials, successHandler, errorHandler) {
		APIService.post(KWEEK_HOST + "/login?username=" + credentials.username + "&password=" + credentials.password, credentials, successHandler, errorHandler);
	};*/

	this.login = function (credentials, successHandler, errorHandler) {
    	APIService.post(KWEEK_HOST + "/authenticate", credentials, successHandler, errorHandler);
    };

	this.registerUser = function (userDetails, successHandler, errorHandler) {
		APIService.post(KWEEK_HOST + "/api/user/new", userDetails, successHandler, errorHandler);
	};

}]);

app.service('APIService', ['$http', function ($http) {

	this.get = function (url, successHandler, errorHandler) {
		$http.get(url)
			.then(successHandler, errorHandler);
	};

	this.post = function (url, data, successHandler, errorHandler) {
		$http.post(url, data)
			.then(successHandler, errorHandler);
	};

}]);