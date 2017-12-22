var app = angular.module('kweek-login', ['ngCookies']);

app.config(['$httpProvider', '$cookiesProvider', function ($httpProvider, $cookiesProvider) {
	$httpProvider.defaults.headers.common['Accept'] = "application/json";
	$httpProvider.defaults.headers.common['Content-Type'] = "application/json";
	$httpProvider.defaults.useXDomain = true;

	$cookiesProvider.defaults.path = "/";
	$cookiesProvider.defaults.secure = true;
	// $cookiesProvider.defaults.expires = fill in a date;
}]);

app.controller('LoginController', ['$scope', '$rootScope', '$cookies', 'LoginService', function (
	$scope, $rootScope, $cookies, LoginService) {

	$scope.credentials = {};
	$scope.new_user = {};
	$scope.login_error = false;
	$scope.registration_error = false;
	$scope.registration_success = 0;

	var KWEEK_HOST = "http://localhost:9080";

	$scope.login = function () {
		LoginService.login($scope.credentials, function (response) {
			console.log("response value: " + response.data);
			if (response.data) {
				console.log(response.data);
				console.log($cookies);
				// $cookies.put('user_role', response.data);
				document.cookie = "user_role=" + response.data;
				window.location.href = KWEEK_HOST + "/";
			} else {
				$scope.login_error = true;
				$scope.login_error_message = "Invalid username or password";
			}
		}, function (response, status) {
			$scope.login_error = true;
			$scope.login_error_message = "Invalid username or password";
		});
	};

	$scope.registerUser = function () {
		LoginService.registerUser($scope.new_user, function (response) {
			/*if (response) {
				window.location.href = KWEEK_HOST + "/dashboard";
			} else {
				window.location.href = KWEEK_HOST + "/login";
			}*/

			$scope.credentials.username = $scope.new_user.username;
			$scope.credentials.password = $scope.new_user.rawPassword;
			$scope.login();
		}, function (response, status) {
			$scope.registration_error = true;
			$scope.registration_error_message = "A network error has occured. Please try registering again.";
		});
	};

	$scope.registerDriver = function () {
		$scope.new_user.accountType = "ROLE_DRIVER";
		LoginService.registerUser($scope.new_user, function (response) {
			/*if (response) {
				window.location.href = KWEEK_HOST + "/dashboard";
			} else {
				window.location.href = KWEEK_HOST + "/login";
			}*/
			$scope.registration_success = 1;
		}, function (response, status) {
			$scope.registration_error = true;
			$scope.registration_error_message = "A network error has occured. Please try registering again.";
		});
	};

}]);

app.service('LoginService', ['APIService', function (APIService) {

	/*this.login = function (credentials, successHandler, errorHandler) {
		APIService.post("/login?username=" + credentials.username + "&password=" + credentials.password, credentials, successHandler, errorHandler);
	};*/

	this.login = function (credentials, successHandler, errorHandler) {
		APIService.post("/authenticate", credentials, successHandler, errorHandler);
	};

	this.registerUser = function (userDetails, successHandler, errorHandler) {
		APIService.post("/api/user/new", userDetails, successHandler, errorHandler);
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