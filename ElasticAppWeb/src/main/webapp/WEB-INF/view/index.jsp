<!DOCTYPE html>
<html ng-app="elasticApp">
	<head>
		<meta charset="utf-16">
		<meta http-equiv="Cache-Control" >
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/bootstrap.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/bootstrap-theme.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/home.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/style/requestUpdate.css">
	
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/jQuery.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/bootstrap.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/angularjs.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/angular-route.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/script.js"></script>
	</head>
	<body ng-controller="elasticAppController">
		
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Elastic Middleware Application</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
			    	<li><a href="#"><i class="icon-home"></i> Home</a></li>
			    	<li><a href="#newRequest"><i class="icon-edit"></i> New Request</a></li>
			    	<li><a href="#search"><i class="icon-search"></i> Search Request</a></li>
			    	<li>&nbsp;</li>
			    	<li>&nbsp;</li>
			    	<li><a class="navbar-brand" href="#">${sessionScope.userVO.userName}</a></li>
				</ul>
			 </div>
		</nav>
		<div id="messageDiv" class="hide">
        	<a href="#" class="close" data-dismiss="alert">&times;</a>
    	</div>
    	<div class="hide">
    		<div id="summary"><a href="#Summary"></a></div>
    	</div>
		<div id="pagecontent">
			<div id="main">
				<div ng-view></div>
			</div>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/controller/RequestController.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/controller/SummaryController.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/controller/SearchController.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/controller/UpdateController.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/script/service/RequestService.js"></script>
	</body>
</html>