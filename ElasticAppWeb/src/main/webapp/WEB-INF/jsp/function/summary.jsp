<%--
  Created by IntelliJ IDEA.
  User: sudha
  Date: 04-Oct-16
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/theme/requestUpdate.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/newRequest.js"></script>
<div id="homeContainer">
	<form action="${pageContext.request.contextPath}/" method="post">
		 <input type="hidden" name="errorForm" value="${requestForm.errorForm}"/>
		 <input type="hidden" id="errorMessages" value="${requestForm.errorMessages}" />
		 <div id="newRequestLabel">
		     <fieldset id="QueryType">
		         <legend>Query Information</legend>
		         <p>
		             <span class="headerLabel">Query Name : </span>
		             <span class="headerValue">
		                 <input name="queryName" id="queryName" class="mandatory" disabled="disabled" readonly="readonly"
		                 value="${requestForm.queryName}"/>
		             </span>
		         </p>
		         <p>
		             <span class="headerLabel">Project Name : </span>
		             <span class="headerValue">
		             	<select disabled="disabled" name="projectId" id="projectId">
		             		<option value="">Select One</option>
		             		<c:forEach items="${requestScope.requestForm.availableProjects}" var="domainVO">
		             			<c:if test="${domainVO.id eq requestForm.projectId}">
		             				<option value="${domainVO.id}" selected="selected">${domainVO.value}</option>
		             			</c:if>
		             			<c:if test="${domainVO.id ne requestForm.projectId}">
		             				<option value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             		</c:forEach>
		             	</select>
		             </span>
		         </p>
		         <p>
		             <span class="headerLabel">Query Type : </span>
		             <span class="headerValue">
		              <select disabled="disabled" name="queryType" id="queryType">
		             		<option value="">Select One</option>
		             		<c:forEach items="${requestScope.requestForm.queryTypes}" var="domainVO">
			             		<c:if test="${domainVO.id eq requestForm.queryType}">
			             			<option selected="selected" value="${domainVO.id}">${domainVO.value}</option>
			             		</c:if>
			             		<c:if test="${domainVO.id ne requestForm.queryType}">
			             			<option value="${domainVO.id}">${domainVO.value}</option>
			             		</c:if>
		             		</c:forEach>
		             	</select>                    
		             </span>
		         </p>
		         <p>
		             <span class="headerLabel">Frequency : </span>
		             <span class="headerValue">
		             	<select disabled="disabled" name="updateFreq" id="updateFreq">
		             		<option value="">Select One</option>
		             		<c:forEach items="${requestScope.requestForm.updateFreqList}" var="domainVO">
		             			<c:if test="${domainVO.id eq requestForm.updateFreq}">
		             				<option selected="selected" value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             			<c:if test="${domainVO.id ne requestForm.updateFreq}">
		             				<option value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             		</c:forEach>
		             	</select>
		             </span>
		         </p>
		         <p>
		             <span class="headerLabel">Query : </span>
		             <span class="headerValue">
		                 <textarea name="query" id="query" disabled="disabled" readonly="readonly" 
		                 rows="5" cols="30">${requestForm.query}</textarea>
		             </span>
		         </p>
		
		     </fieldset>
		
		     <fieldset id="dBConnection">
		         <legend>Connection Information</legend>
		         <p>
		             <span class="headerLabel">Database Vendor: </span>
		             <span class="headerValue">
		             	<select disabled="disabled" name="moduleVO.databaseVendorId" id="moduleVO.databaseVendorId">
		             		<option value="">Select One</option>
		             		<c:forEach items="${requestScope.requestForm.dbTypes}" var="domainVO">
			             		<c:if test="${domainVO.id eq requestForm.moduleVO.databaseVendorId}">
			             			<option selected="selected" value="${domainVO.id}">${domainVO.key}</option>
		             			</c:if>
		             			<c:if test="${domainVO.id ne requestForm.moduleVO.databaseVendorId}">
			             			<option value="${domainVO.id}">${domainVO.key}</option>
		             			</c:if>
		             			
		             		</c:forEach>
		             	</select>                      
		             </span>
		         </p>
		         <p>
		             <span class="headerLabel">Server (IP/ Name): </span>
		             <span class="headerValue">                    	
		                 <input name="moduleVO.dbServerName" id="moduleVO.dbServerName" 
		                 value="${requestForm.moduleVO.dbServerName}" disabled="disabled" readonly="readonly" />
		             </span>
		         </p>
		         <p>
		             <span class="headerLabel">Port Number : </span>
		             <span class="headerValue">
		                 <input name="moduleVO.dbPortNumber" id="moduleVO.dbPortNumber" 
		                 value="${requestForm.moduleVO.dbPortNumber}" disabled="disabled" readonly="readonly"/>
		             </span>
		         </p>
		         <p>
		             <span class="headerLabel">Database Name : </span>
		             <span class="headerValue">
		                 <input name="moduleVO.dataBaseName" id="moduleVO.dataBaseName" 
		                 value="${requestForm.moduleVO.dataBaseName}" disabled="disabled" readonly="readonly"/>
		             </span>
		         </p>
		         <p>
		             <span class="headerLabel">User Name : </span>
		             <span class="headerValue">
		                 <input name="moduleVO.dbUserName" id="moduleVO.dbUserName" 
		                 value="${requestForm.moduleVO.dbUserName}" disabled="disabled" readonly="readonly"/>
		             </span>
		         </p>
		         <p>
		             <span class="headerLabel">Password : </span>
		             <span class="headerValue">
		                 <input type="password" name="moduleVO.dbPassword" id="moduleVO.dbPassword" 
		                 value="${requestForm.moduleVO.dbPassword}" disabled="disabled" readonly="readonly"/>
		             </span>
		         </p>	
		     </fieldset>
		     <div >
                <button class="button" type="submit">Home</button>
            </div>
		 </div>
	 </form>
</div>