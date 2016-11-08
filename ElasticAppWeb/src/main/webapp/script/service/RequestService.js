/**
 * 
 */

'use strict';

elasticApp.factory('RequestService', [ '$http', '$q', function($http, $q) {

	var REST_SERVICE_URI = 'http://localhost:8080/ElasticAppWeb/request/';

	var factory = {
		fetchRequestForm : fetchRequestForm,
		testConnection : testConnection,
		checkUniqueName : checkUniqueName,
		fetchColumnMapping : fetchColumnMapping,
		saveRequest : saveRequest,
		searchRequest : searchRequest,
		getRequest : getRequest
	};

	return factory;

	function fetchRequestForm() {
		var deferred = $q.defer();
		$http.get(REST_SERVICE_URI + 'fetchForm').then(function(response) {
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.error('Error while fetching Users');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}

	
	function testConnection(moduleVO){
		var deferred = $q.defer();
		
		var module = {
				'databaseVendorId' : moduleVO.databaseVendorId,
				'dataBaseName' : moduleVO.dataBaseName,
				'dbServerName': moduleVO.dbServerName,
				'dbPortNumber': moduleVO.dbPortNumber,
				'dbUserName' : moduleVO.dbUserName,
				'dbPassword': moduleVO.dbPassword,
				'dbConnectionURL' : ''
		};
		
		$http({
			method : "PUT",
		    url : REST_SERVICE_URI + 'testConnection',
		    data :  module,
		    dataType: 'json',
		    contentType: 'application/json',
		}).then(function(response) {
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.error('Error while fetching Users');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	function checkUniqueName(name){
		var deferred = $q.defer();
		$http.put( REST_SERVICE_URI + 'checkUniqueName/' + name).then(function(response) {
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.error('Error while fetching Users');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	function fetchColumnMapping(requestVO){
		var deferred = $q.defer();
		$http.put( REST_SERVICE_URI + 'fetchColumnMapping',requestVO).then(function(response) {
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.error('Error while fetching Users');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	function saveRequest(requestVO){
		var deferred = $q.defer();
		$http.post( REST_SERVICE_URI + 'saveRequest',requestVO).then(function(response) {
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.error('Error while fetching Users');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	function searchRequest(searchRequestForm){
		var deferred = $q.defer();
		$http.post( REST_SERVICE_URI + 'searchRequest',searchRequestForm).then(function(response) {
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.error('Error while fetching Users');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	function getRequest(requestID){
		var deferred = $q.defer();
		$http.get( REST_SERVICE_URI + 'getRequest/' + requestID).then(function(response) {
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.error('Error while fetching Requests');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}
	
	
	
} ]);