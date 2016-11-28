/**
 * 
 */

'use strict';

elasticApp.factory('SearchService', [ '$http', '$q', function($http, $q) {
	var REST_SERVICE_URI = 'http://localhost:8080/ElasticAppWeb/dataSearch/';

	var factory = {
		simpleSearch : simpleSearch,
		advancedSearch : advancedSearch,
	};

	return factory;

	function simpleSearch(simpleSearchCriteria) {
		var deferred = $q.defer();
		var data = '?';
		data += "appToken="+simpleSearchCriteria.appToken;
		data += "&term="+simpleSearchCriteria.term;
		if(simpleSearchCriteria.fields){
			data += "&fields="+simpleSearchCriteria.fields;
		}
		if(simpleSearchCriteria.wildcard){
			data += "&wildcard="+simpleSearchCriteria.wildcard;
		}
		$http.get(REST_SERVICE_URI + 'simpleSearch'+data).then(function(response) {
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.error('Error while fetching simple search');
			deferred.reject(errResponse);
		});
		return deferred.promise;
	}

	function advancedSearch(searchCriteria) {
		var deferred = $q.defer();
		$http.post( REST_SERVICE_URI + 'advancedSearch',searchCriteria).then(function(response) {
			deferred.resolve(response.data);
		}, function(errResponse) {
			console.error('Error while fetching advanced Search');
			deferred.reject(errResponse);
		});
		return deferred.promise;
		
	}

} ])