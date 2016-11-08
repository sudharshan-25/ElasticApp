/**
 * 
 */
elasticApp.controller('searchController', [ '$scope', '$http',
		'RequestService', function($scope, $http, RequestService) {
	
	$scope.searchRequestForm = {};
	$scope.searchRequestForm.queryName = '';
	$scope.searchRequestForm.queryType = '';
	$scope.searchRequestForm.projectId = '';
	$scope.searchRequestForm.dbType = '';
	$scope.searchRequestForm.updateFreq = '';
	$scope.searchRequestForm.statusId = '';
	
		
	$scope.loadInitData = function(){
		$scope.searchRequestForm = {};
		$scope.searchRequestForm.queryName = '';
		$scope.searchRequestForm.queryType = '';
		$scope.searchRequestForm.projectId = '';
		$scope.searchRequestForm.dbType = '';
		$scope.searchRequestForm.updateFreq = '';
		$scope.searchRequestForm.statusId = '';
		$scope.searchRequestForm.searchResults = [];
		
		RequestService.fetchRequestForm().then(function(d) {
			$scope.searchRequestForm['dbTypes'] = d.dbTypes;
			$scope.searchRequestForm['availableProjects'] = d.availableProjects;
			$scope.searchRequestForm['queryTypes'] = d.queryTypes;
			$scope.searchRequestForm['updateFreqList'] = d.updateFreqList;
			$scope.searchRequestForm['statusList'] = d.dataTypeList;
		}, function(errResponse) {
			alert('Error while fetching Users');
		});
	}
	
	$scope.searchRequests = function(){
		
		if($scope.searchRequestForm.queryName){
			RequestService.searchRequest($scope.searchRequestForm).then(function(d) {
				$scope.searchRequestForm.searchResults = d.data;
			}, function(errResponse) {
				alert('Error while fetching search Results');
			});			
		}else{
			alert('Atleast query name is needed');
		}
		
		
	}

	
}]);