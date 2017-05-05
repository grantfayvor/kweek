app.service('APIService', ['$http', '$q', function ($http, $q) {

    this.get = function (url, successHandler, errorHandler) {
        $http.get(url)
            .then(successHandler, errorHandler);
    };

    this.getWithHeader = function(url, headers, successHandler, errorHandler) {
        $http.get(url, headers)
            .success(successHandler)
            .error(errorHandler);
    };

    this.post = function (url, data, successHandler, errorHandler) {
        $http.post(url, data)
            .then(successHandler, errorHandler);
    };

    this.delete = function (url, successHandler, errorHandler) {
        $http.delete(url)
            .success(successHandler)
            .error(errorHandler);
    };

    this.put = function (url, data, successHandler, errorHandler) {
        $http.put(url, data)
            .success(successHandler)
            .error(errorHandler);
    };

    this.head = function (url, notifyMsg) {
        var deferred = $q.defer();

        $http.head(url)
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