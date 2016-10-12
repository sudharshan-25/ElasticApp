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
    
    <form name="updateRequestForm" action="${pageContext.request.contextPath}/saveRequest" method="POST">
        <input type="hidden" name="errorForm" id="errorForm" value="${updateRequestForm.errorForm}"/>
        <input type="hidden" id="errorMessages" name="errorMessages" value="${updateRequestForm.errorMessages}" />
        <input type="hidden" name="queryId" value="${updateRequestForm.queryId}"/>
        <div id="newRequestLabel">
            <fieldset id="QueryType">
                <legend>Query Information</legend>
                <p>
                    <span class="headerLabel">Query Name : </span>
                    <span class="headerValue">
                        <input name="queryName" id="queryName" class="mandatory" required="required" disabled="disabled" readonly="readonly" 
                        value="${updateRequestForm.queryName}"/>
                    </span>
                </p>
                <p>
                    <span class="headerLabel">App Token: </span>
                    <span class="headerValue">
                        <input name="appToken" id="appToken" class="mandatory" required="required" disabled="disabled" readonly="readonly" 
                        value="${updateRequestForm.appToken}"/>
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Project Name : </span>
                    <span class="headerValue">
                    	<select required="required" name="projectId" id="projectId">
                    		<option value="">Select One</option>
                    		<c:forEach items="${updateRequestForm.availableProjects}" var="domainVO">
		             			<c:if test="${domainVO.id eq updateRequestForm.projectId}">
		             				<option value="${domainVO.id}" selected="selected">${domainVO.value}</option>
		             			</c:if>
		             			<c:if test="${domainVO.id ne updateRequestForm.projectId}">
		             				<option value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             		</c:forEach>
                    	</select>
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Query Type : </span>
                    <span class="headerValue">
	                    <select required="required" name="queryType" id="queryType">
                    		<option value="">Select One</option>
                    		<c:forEach items="${updateRequestForm.queryTypes}" var="domainVO">
			             		<c:if test="${domainVO.id eq updateRequestForm.queryType}">
			             			<option selected="selected" value="${domainVO.id}">${domainVO.value}</option>
			             		</c:if>
			             		<c:if test="${domainVO.id ne updateRequestForm.queryType}">
			             			<option value="${domainVO.id}">${domainVO.value}</option>
			             		</c:if>
		             		</c:forEach>
                    	</select>                    
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Frequency : </span>
                    <span class="headerValue">
                    	<select required="required" name="updateFreq" id="updateFreq">
                    		<option value="">Select One</option>
                    		<c:forEach items="${updateRequestForm.updateFreqList}" var="domainVO">
		             			<c:if test="${domainVO.id eq updateRequestForm.updateFreq}">
		             				<option selected="selected" value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             			<c:if test="${domainVO.id ne updateRequestForm.updateFreq}">
		             				<option value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             		</c:forEach>
                    	</select>
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Status : </span>
                    <span class="headerValue">
                    	<select required="required" name="statusId" id="statusId">
                    		<option value="">Select One</option>
                    		<c:forEach items="${updateRequestForm.statusList}" var="domainVO">
		             			<c:if test="${domainVO.id eq updateRequestForm.statusId}">
		             				<option selected="selected" value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             			<c:if test="${domainVO.id ne updateRequestForm.statusId}">
		             				<option value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             		</c:forEach>
                    	</select>
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Query : </span>
                    <span class="headerValue">
                        <textarea name="query" id="query" required="required" rows="5"
                         cols="30">${updateRequestForm.query}</textarea>
                    </span>
                </p>

            </fieldset>

            <fieldset id="dBConnection">
                <legend>Connection Information</legend>
                <p>
                    <span class="headerLabel">Database Vendor: </span>
                    <span class="headerValue">
                    	<select required="required" name="moduleVO.databaseVendorId" id="moduleVO.databaseVendorId">
                    		<option value="">Select One</option>
                    		<c:forEach items="${updateRequestForm.dbTypes}" var="domainVO">
			             		<c:if test="${domainVO.id eq updateRequestForm.moduleVO.databaseVendorId}">
			             			<option selected="selected" value="${domainVO.id}">${domainVO.key}</option>
		             			</c:if>
		             			<c:if test="${domainVO.id ne updateRequestForm.moduleVO.databaseVendorId}">
			             			<option value="${domainVO.id}">${domainVO.key}</option>
		             			</c:if>
		             			
		             		</c:forEach>
                    	</select>                      
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Server (IP/ Name): </span>
                    <span class="headerValue">                    	
                        <input name="moduleVO.dbServerName" id="moduleVO.dbServerName" required="required" 
                        value="${updateRequestForm.moduleVO.dbServerName}" />
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Port Number : </span>
                    <span class="headerValue">
                        <input name="moduleVO.dbPortNumber" id="moduleVO.dbPortNumber" required="required"
                        value="${updateRequestForm.moduleVO.dbPortNumber}" />
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Database Name : </span>
                    <span class="headerValue">
                        <input name="moduleVO.dataBaseName" id="moduleVO.dataBaseName" required="required"
                        value="${updateRequestForm.moduleVO.dataBaseName}" />
                    </span>
                </p>
                <p>
                    <span class="headerLabel">User Name : </span>
                    <span class="headerValue">
                        <input name="moduleVO.dbUserName" id="moduleVO.dbUserName" required="required"
                        value="${updateRequestForm.moduleVO.dbUserName}" />
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Password : </span>
                    <span class="headerValue">
                        <input type="password" name="moduleVO.dbPassword" id="moduleVO.dbPassword" required="required"/>
                    </span>

                </p>
                <button class="button" type="button" onclick="testDBConnection()">Test Connection</button>

            </fieldset>
            <div >
                <button class="button" type="submit">Save</button>
                <button class="button" type="reset" >Clear</button>
            </div>
        </div>
    </form>
</div>
