choresControllers.controller('sessionController', ['$scope', '$http',
	function ($scope, $http) {
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
		
		$scope.login = function() {
			var headers = {
				authorization : "Basic " +
				btoa($scope.credentials.username + ":" + $scope.credentials.password)
			}
			$http.post('session', {}, { headers : headers }).success(function(data) {
				$scope.authenticated = true;
				$scope.error = false;
			}).error(function() {
				$scope.authenticated = false;
				$scope.error = true;
			});
		};

		$scope.logout = function() {
			$http.delete('session').success(function() {
			    $scope.authenticated = false;
			}).error(function(data) {
			    $scope.authenticated = false;
			});
		}
	}
]);
