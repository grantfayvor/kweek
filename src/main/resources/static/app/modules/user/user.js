app.controller('UserController', ['$rootScope', '$scope', 'UserService', function ($rootScope, $scope, UserService) {

    //remember to add the $location dependency in the UserController

    $scope.credentials = {};
    $scope.new_user = {};
    $scope.success = 0;
    $scope.registration_error = false;
    $scope.registration_success = 0;

    $scope.initialize = function () {
//        authenticate();
    };

    $scope.addFeedback = function () {
        UserService.addFeedback($scope.message, function (response) {
            if (response.data === true) {
                $scope.success = 1;
            }
            setTimeout(function () {
                $("#fade1").fadeOut(5000);
                $scope.success = 2;
            }, 1000);
        }, function (response, status) {
            console.log("error in sending feedback");
        });
    };

    $scope.getFeedbacks = function () {
        UserService.getFeedbacks(function (response) {
            $scope.all_feedbacks = response.data;
        }, function (response, status) {
            console.log("error in getting feedbacks");
        });
    };

    $scope.registerDriver = function () {
    	$scope.new_user.accountType = "ROLE_DRIVER";
    	UserService.registerUser($scope.new_user, function (response) {
   			/*if (response) {
   				window.location.href = KWEEK_HOST + "/dashboard";
   			} else {
   				window.location.href = KWEEK_HOST + "/login";
   			}*/
    		$scope.registration_success = 1;
    		$scope.new_user = {};
    	}, function (response, status) {
   			$scope.registration_error = true;
   			$scope.registration_error_message = "A network error has occured. Please try registering again.";
   		});
   	};

}]);

app.service('UserService', ['APIService', function (APIService) {

    this.addFeedback = function (message, successHandler, errorHandler) {
        APIService.post("/api/user/feedback", message, successHandler, errorHandler);
    };

    this.getFeedbacks = function (successHandler, errorHandler) {
        APIService.post("/api/user/feedback/all", successHandler, errorHandler);
    };

	this.registerUser = function (userDetails, successHandler, errorHandler) {
		APIService.post("/api/user/new", userDetails, successHandler, errorHandler);
	};
}]);