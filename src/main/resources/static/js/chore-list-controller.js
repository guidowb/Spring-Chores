choreControllers.controller('chorelistController', ['$scope', '$http', '$modal',
	function ($scope, $http, $modal) {
	
		$scope.update = function() {
			$http.get('/chores').success(function(data) {
				$scope.chores = data;
			});
		}

		$scope.addChore = function() {
			var choreDialog = $modal.open({
				templateUrl: 'views/chore-form.html',
				controller: 'choreformController',
				resolve: {
					choreId: function() { return undefined; }
				}
			});
			choreDialog.result.then(function(chore) {
				$scope.update();
			})
		}
		
		$scope.editChore = function(choreId) {
			var choreDialog = $modal.open({
				templateUrl: 'views/chore-form.html',
				controller: 'choreformController',
				resolve: {
					choreId: function() { return choreId; }
				}
			});
			choreDialog.result.then(function(chore) {
				$scope.update();
			})
		}
		
		$scope.update();
	}
]);
