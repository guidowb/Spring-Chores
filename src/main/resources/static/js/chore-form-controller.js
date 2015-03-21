choreControllers.controller('choreformController', ['$scope', '$http', '$modalInstance', 'choreId',
	function ($scope, $http, $dialog, choreId) {
	
		$scope.ok = function() {
			$http.post('/chores', $scope.chore).success(function(data) {
				$dialog.close($scope.chore);
			});
		}
		
		$scope.cancel = function() {
			$dialog.dismiss('cancel');
		}
		
		$scope.toggle_day = function(index) {
			$scope.chore.due_days[index] = !$scope.chore.due_days[index];
		}
		
		$scope.get = function(choreId) {
			$http.get('/chores/' + choreId).success(function(data) {
				$scope.chore = data;
			});
		}

		$scope.init = function() {
			if ($scope.chore) return;
			$scope.chore = { due_days: [] };
		}

		if (choreId) $scope.get(choreId);
		else $scope.init();
	}
]);
