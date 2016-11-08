/**
 * 
 */

elasticApp.controller('summaryController', [ '$scope', '$http',
		'RequestService', function($scope, $http, RequestService) {
	
	
	$scope.requestForm = {};
	
	$scope.initDBConnection = function() {
		
		$scope.requestVO = JSON.parse(localStorage.getItem('summary'));
		
		RequestService.fetchRequestForm().then(function(d) {
			$scope.requestForm['dbTypes'] = d.dbTypes;
			$scope.requestForm['availableProjects'] = d.availableProjects;
			$scope.requestForm['queryTypes'] = d.queryTypes;
			$scope.requestForm['updateFreqList'] = d.updateFreqList;
			$scope.requestForm['dataTypeList'] = d.dataTypeList;
			$scope.requestForm['statusList'] = d.statusList;
			//$scope.$apply();
		}, function(errResponse) {
			alert('Error while fetching Users');
		});
	}
	
}]);