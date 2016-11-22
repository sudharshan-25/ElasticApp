/**
 * 
 */
elasticApp.controller('newRequestController', [ '$scope', '$http',
		'RequestService', function($scope, $http, RequestService) {
		
		$scope.requestForm = {};
		$scope.requestVO = {};
		$scope.requestVO.moduleVO = {};
		$scope.requestVO.moduleVO.databaseVendorId = '';
		$scope.requestVO.dataBaseName = '';
		$scope.requestVO.moduleVO.dbServerName = '';
		$scope.requestVO.moduleVO.dbPortNumber= 0;
		$scope.requestVO.moduleVO.dbUserName = '';
		$scope.requestVO.moduleVO.dbPassword = '';
	
		$scope.requestVO['moduleVO'] = $scope.moduleVO;
	
		$scope.initDBConnection = function() {
			
			$scope.requestForm = {};
			$scope.requestVO = {};
			$scope.requestVO.moduleVO = {};
			$scope.requestVO.moduleVO.databaseVendorId = '';
			$scope.requestVO.dataBaseName = '';
			$scope.requestVO.moduleVO.dbServerName = '';
			$scope.requestVO.moduleVO.dbPortNumber= 0;
			$scope.requestVO.moduleVO.dbUserName = '';
			$scope.requestVO.moduleVO.dbPassword = '';
			
			
			RequestService.fetchRequestForm().then(function(d) {
				$scope.requestForm['dbTypes'] = d.dbTypes;
				$scope.requestForm['availableProjects'] = d.availableProjects;
				$scope.requestForm['queryTypes'] = d.queryTypes;
				$scope.requestForm['updateFreqList'] = d.updateFreqList;
				$scope.requestForm['dataTypeList'] = d.dataTypeList;
				$scope.requestForm['analyserList'] = d.analyserList;
				//$scope.$apply();
			}, function(errResponse) {
				alert('Error while fetching Users');
			});
		}
				
		$scope.testConnection = function(form){
			
			if ($scope[form].$valid) {
				RequestService.testConnection($scope.requestVO['moduleVO']).then(function(d) {
					
					if(d.success){
						$scope.connectionChecked = true;
						alert(d.success);
					}else if(d.error){
						alert(d.error);
					}
					
				}, function(errResponse) {
					alert('Error Testing connection -> ' + errResponse);
				});
			}else{
				document.forms[form].reportValidity();
			}
		}
		
		
		$scope.checkUniqueName = function(){
			if($scope.requestVO['queryName']){
				RequestService.checkUniqueName($scope.requestVO['queryName']).then(function(d) {
					
					if(d.success){
						alert(d.success);
						$scope.queryChecked = true;
					}else if(d.error){
						alert(d.error);
					}
					
				}, function(errResponse) {
					alert('Error Testing connection -> ' + errResponse);
				});
			}else{
				alert('QueryName cannot be empty')
			}
		}
		
		
		$scope.fetchColumnMapping = function(form){
			if ($scope[form].$valid) {
			
				RequestService.fetchColumnMapping($scope.requestVO).then(function(d) {
					if(d.success){
						alert(d.success);
						$scope.requestVO.columnMapping = d.data;
						
						$.each($scope.requestVO.columnMapping, function(index, column){
							column.queryDataType = '' + column.queryDataType;
						});
						
						$scope.columnMapped = true;
						$scope.requestVO.reIndexData = true;
					}else if(d.error){
						alert(d.error);
					}
					
				}, function(errResponse) {
					alert('Error Testing connection -> ' + errResponse);
				});
			}else {
				document.forms[form].reportValidity();
			}
			
		}
		
		$scope.saveRequest = function(form){
			if ($scope[form].$valid) {
				
				RequestService.saveRequest($scope.requestVO).then(function(d) {
					if(d.success){
						alert(d.success);
						localStorage.setItem('summary', JSON.stringify(d.data));
						$('#summary a').click();
					}else if(d.error){
						alert(d.error);
					}
					
				}, function(errResponse) {
					alert('Error Testing connection -> ' + errResponse);
				});
				
			}else{
				document.forms[form].reportValidity();
			}
		}
		
		$scope.gotoSection1 = function(form, noCheck) {
			if (noCheck || $scope[form].$valid) {
								
				$('#newRequestLabelDiv').removeClass('hide');
				$('#elasticInfoDiv').removeClass('hide');
				$('#QueryTypeDiv').removeClass('hide');

				$('#elasticInfoDiv').addClass('hide');
				$('#QueryTypeDiv').addClass('hide');
			} else {
				document.forms[form].reportValidity();
			}
		}

		$scope.gotoSection2 = function(form, noCheck) {
			if ($scope[form].$valid) {
				
				$('#newRequestLabelDiv').removeClass('hide');
				$('#elasticInfoDiv').removeClass('hide');
				$('#QueryTypeDiv').removeClass('hide');
				$('#newRequestLabelDiv').addClass('hide');
				$('#QueryTypeDiv').addClass('hide');
				$scope.queryChecked = false;
				$scope.columnMapped = false;
				
			} else {
				document.forms[form].reportValidity();
			}
		}
		
		$scope.gotoSection3 = function(form) {
			if ($scope[form].$valid) {
				
				$('#newRequestLabelDiv').removeClass('hide');
				$('#elasticInfoDiv').removeClass('hide');
				$('#QueryTypeDiv').removeClass('hide');
				$('#newRequestLabelDiv').addClass('hide');
				$('#elasticInfoDiv').addClass('hide');
				
			} else {
				document.forms[form].reportValidity();
			}
		}
}]);