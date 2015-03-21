var choreApp = angular.module('choreApp',
	[
	 	'ui.router',
	 	'ui.bootstrap',
	 	'choreControllers'
	]
);

choreApp.config(['$httpProvider', '$stateProvider', '$urlRouterProvider',
    function($httpProvider, $stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise("/");
		$stateProvider
			.state('default', {
				url: '/',
				templateUrl: 'views/chore-list.html',
				controller: 'chorelistController'
			});
		$httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	}
]);

choreControllers = angular.module('choreControllers', []);
