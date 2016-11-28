/**
 * 
 */

var elasticApp = angular.module('elasticApp', [ 'ngRoute' ]);

elasticApp.config(function($routeProvider) {
	$routeProvider

	// route for the home page
	.when('/', {
		templateUrl : 'static/home.html',
		controller : 'elasticAppController'
	})

	.when('/newRequest', {
		templateUrl : 'static/newRequest.html',
		controller : 'newRequestController'
	})

	.when('/search', {
		templateUrl : 'static/searchRequest.html',
		controller : 'searchController'
	})
	
	.when('/Summary', {
		templateUrl : 'static/summary.html',
		controller : 'summaryController'
	})
	
	.when('/updateRequest/:ID',{
		templateUrl : 'static/updateRequest.html',
		controller : 'updateRequestController'
	})

	.when('/searchData',{
		templateUrl : 'static/dataSearch.html',
		controller : 'searchDataController'
})
	;
});


elasticApp.controller('elasticAppController', [ '$scope', '$http', function($scope, $http) {
	
	
}]);

