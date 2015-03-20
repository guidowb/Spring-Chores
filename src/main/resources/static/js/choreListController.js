choresControllers.controller('choreListController', ['$scope', '$http',
	function ($scope, $http) {
		$http.get('/chores').success(function(data) {
			$scope.chores = data;
		});
	}
]);
