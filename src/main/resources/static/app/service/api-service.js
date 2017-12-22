app.service('APIService', ['$http', '$q', function ($http, $q) {

    var KWEEK_HOST = "http://localhost:9080";

    this.get = function (url, successHandler, errorHandler) {
        $http.get(KWEEK_HOST +url)
            .then(successHandler, errorHandler);
    };

    this.getWithHeader = function(url, headers, successHandler, errorHandler) {
        $http.get(KWEEK_HOST +url, headers)
            .success(successHandler)
            .error(errorHandler);
    };

    this.post = function (url, data, successHandler, errorHandler) {
        $http.post(KWEEK_HOST +url, data)
            .then(successHandler, errorHandler);
    };

    this.postWithHeader = function(url, data, headers, successHandler, errorHandler) {
        $http.post(KWEEK_HOST +url, data, headers)
            .success(successHandler)
            .error(errorHandler);
    };

    this.postWithIdentity = function (url, data, successHandler, errorHandler) {
        $http.post(KWEEK_HOST + url, data, {
            transformRequest: angular.identity
        }).then(successHandler, errorHandler);
    };

    this.delete = function (url, successHandler, errorHandler) {
        $http.delete(KWEEK_HOST +url)
            .then(successHandler, errorHandler);
    };

    this.put = function (url, data, successHandler, errorHandler) {
        $http.put(KWEEK_HOST +url, data)
            .success(successHandler)
            .error(errorHandler);
    };

    this.head = function (url, notifyMsg) {
        var deferred = $q.defer();

        $http.head(KWEEK_HOST +url)
            .success(function (data) {
                deferred.resolve(data);
            })
            .error(function (error) {
                deferred.reject(error);
                deferred.notify(notifyMsg);
            });

        return deferred.promise;
    };


}]);