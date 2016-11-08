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

	// route for the about page
	.when('/newRequest', {
		templateUrl : 'static/newRequest.html',
		controller : 'newRequestController'
	})

	// route for the contact page
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
	
	;
});


elasticApp.controller('elasticAppController', [ '$scope', '$http', function($scope, $http) {
	
	
}]);

