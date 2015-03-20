var choresApp = angular.module('choresApp',
	[
	 	'ui.router',
	 	'ui.bootstrap',
	 	'choresControllers'
	]
);

choresApp.config(['$httpProvider', '$stateProvider', '$urlRouterProvider',
    function($httpProvider, $stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise("/");
		$stateProvider
			.state('default', {
				url: '/',
				templateUrl: 'views/chore-list.html',
				controller: 'choreListController'
			});
		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	}
]);

choresControllers = angular.module('choresControllers', []);
