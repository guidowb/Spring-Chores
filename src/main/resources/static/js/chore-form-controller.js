choreControllers.controller('choreformController', ['$scope', '$http', '$modalInstance', 'choreId',
	function ($scope, $http, $dialog, choreId) {
	
		$scope.ok = function() {
			if (choreId) {
				$http.put('/chores/' + choreId, $scope.chore).success(function(data) {
					$dialog.close($scope.chore);
				});
			}
			else {
				$http.post('/chores', $scope.chore).success(function(data) {
					$dialog.close($scope.chore);
				});
			}
		}
		
		$scope.cancel = function() {
			$dialog.dismiss('cancel');
		}
		
		$scope.delete_start = function() {
			$scope.deleting = true;
			angular.element('.delete-confirm').focus();
		}
		
		$scope.delete_cancel = function() {
			$scope.deleting = false;
		}
		
		$scope.delete_confirm = function() {
			$http.delete('/chores/' + choreId, $scope.chore).success(function(data) {
				$scope.chore = undefined;
				$dialog.close(undefined);
			});
		}

		$scope.toggle_day = function(index) {
			$scope.chore.dueDays[index] = !$scope.chore.dueDays[index];
		}
		
		$scope.get = function(choreId) {
			$http.get('/chores/' + choreId).success(function(data) {
				$scope.chore = data;
			});
		}

		$scope.init = function() {
			if ($scope.chore) return;
			$scope.chore = { dueDays: [] };
		}

		if (choreId) $scope.get(choreId);
		else $scope.init();
	}
]);
