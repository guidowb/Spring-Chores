choresControllers.controller('choreListController', ['$scope', '$http',
	function ($scope, $http) {
		$http.get('/api/chores').success(function(data) {
			$scope.chores = data;
		});
	}
]);
