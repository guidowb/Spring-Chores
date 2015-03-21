choreControllers.controller('sessionController', ['$scope', '$http', '$window',
	function ($scope, $http, $window) {
	
		$scope.login = function() {
			var headers = {
				authorization : "Basic " +
				btoa($scope.credentials.username + ":" + $scope.credentials.password)
			}
			$http.post('session', {}, { headers : headers }).success(function(data) {
			    $window.location.reload(); // This is to cause "save password" at the appropriate time
				$scope.authenticated = true;
				$scope.error = false;
			}).error(function() {
				$scope.authenticated = false;
				$scope.error = true;
			});
		};
	
		$scope.logout = function() {
			$http.delete('session').success(function() {
			    $window.location.reload(); // This is to force refresh of the CSRF token
			}).error(function(data) {
				$window.location.reload(); // This is to force refresh of the CSRF token
			});
		}

		$scope.verify = function() {
			$http.get('session').success(function(data) {
				$scope.authenticated = true;
			}).error(function() {
				$scope.authenticated = false;
			});
		}
	
		$scope.credentials = {};
		$scope.authenticated = false;
		$scope.error = false;

		$scope.verify();
	}
]);
