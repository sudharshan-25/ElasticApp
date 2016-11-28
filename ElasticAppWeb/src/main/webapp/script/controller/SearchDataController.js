/**
 * 
 */

elasticApp.controller('searchDataController', [ '$scope', '$http',
		'SearchService', function($scope, $http, SearchService) {
	
	$scope.selectedSearch = 'simple';
	
	$scope.simpleSearchCriteria = {};
	
	$scope.searchCriteria = {};
	$scope.searchCriteria.allMatch = [];
	$scope.searchCriteria.noMatch = [];
	$scope.searchCriteria.fewMatch = [];
	
	$scope.searched =false;
	
	$scope.simpleSearch = function(form){
		if ($scope[form].$valid) {
			$scope.searched = false;
			$('#result')[0].innerText = '';
			SearchService.simpleSearch($scope.simpleSearchCriteria).then(function(d) {
				if(d.error){
					alert(d.error);
				}else{
					$scope.searched = true;
					$('#result')[0].innerText = '' +JSON.stringify(d.data, null, 2)
				}				
			}, function(errResponse) {
				alert('Error Simple Search -> ' + errResponse);
			});
		
		}else{
			document.forms[form].reportValidity();
		}
	}
	
	$scope.advancedSearch = function(form){
		if ($scope[form].$valid) {
			
			if($scope.searchCriteria.allMatch.length == 0 && $scope.searchCriteria.noMatch.length == 0 && $scope.searchCriteria.fewMatch.length == 0){
				alert('Atleast one Search Criteria needed');
				return false;
			}
			
			$scope.searched = false;
			$('#result')[0].innerText = '';
			SearchService.advancedSearch($scope.searchCriteria).then(function(d) {
				if(d.error){
					alert(d.error);
				}else{
					$scope.searched = true;
					$('#result')[0].innerText = '' +JSON.stringify(d.data, null, 2)
				}				
			}, function(errResponse) {
				alert('Error Simple Search -> ' + errResponse);
			});
			
			
		}else{
			document.forms[form].reportValidity();
		}
	}
	
	
	$scope.addSearchFields = function(array){
		var searchField = {};
		array.push(searchField);
	}
	
	$scope.removeSearchFields = function(array, field){
		var index = array.indexOf(field);
		array.splice(index, 1);
	}
	
	
	
}]);