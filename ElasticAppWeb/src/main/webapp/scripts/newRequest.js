/**
 * Created by sudha on 06-Oct-16.
 */

$(function () {
    if($('#errorForm').val() == 'false'){
        $('input[type=text], input[type=password]').each(function (index, element) {
           element.value = '';
        });

    }
    

    if($('#errorForm').val() == 'true'){
    	$('#messageDiv').show();
    	$('#message')[0].innerText = $('#errorMessages').val();
    	$('#messageDiv').addClass('error');
    	
    }
    
});


$('#messageDiv').on('click', function(){
	$('#messageDiv').hide();
});

function testDBConnection() {

    var data = {};
    data.databaseVendorId = $('#moduleVO\\.databaseVendorId').val();
    data.dbServerName = $('#moduleVO\\.dbServerName').val();
    data.dbPortNumber =$('#moduleVO\\.dbPortNumber').val();
    data.dataBaseName = $('#moduleVO\\.dataBaseName').val();
    data.dbUserName = $('#moduleVO\\.dbUserName').val();
    data.dbPassword = $('#moduleVO\\.dbPassword').val();

    var error = false;
    var errorMessage = '';
    if(!data.databaseVendorId){
    	error = true;
    	errorMessage = 'DB Vendor cannot be empty';
    }else if(!data.dbServerName){
    	error = true;
    	errorMessage = 'DB Server Name cannot be empty';
    }else if(!data.dbPortNumber){
    	error = true;
    	errorMessage = 'DB Port Number cannot be empty';
    }else if (!data.dataBaseName){
    	error = true;
    	errorMessage = 'DB Name cannot be empty';
    }else if(!data.dbUserName){
    	error = true;
    	errorMessage = 'DB User Name cannot be empty';
    }else if(!data.dbPassword){
    	error = true;
    	errorMessage = 'DB Password cannot be empty';
    }
    
    if(error){
    	alert(errorMessage);
    	return false;
    }
    
    $('#messageDiv').removeClass('success');
    $('#messageDiv').removeClass('error');
    $('#messageDiv').hide();
    
    $.ajax({
        url : '/ElasticAppWeb/testDBConnection',
        data : {'module':JSON.stringify(data)},
        contentType : "application/json",
        success: function (result, status, xhr) {
        	if(result.error){
        		$('#messageDiv').show();
        		$('#message')[0].innerText = result.error;
        		$('#messageDiv').addClass('error');
        	}else{
        		$('#messageDiv').show();
        		$('#message')[0].innerText = result.success;
        		$('#messageDiv').addClass('success');
        	}
        },
        error : function (xhr, status, error) {
        	$('#messageDiv').show();
        	$('#message')[0].innerText = result.error;
    		$('#messageDiv').addClass('error');
        }
    });
}