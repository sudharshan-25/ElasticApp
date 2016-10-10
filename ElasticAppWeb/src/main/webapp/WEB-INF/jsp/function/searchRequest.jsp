<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div id="homeContainer">
	<form action="${pageContext.request.contextPath}/search" method="post" name="searchRequestForm">
		<input type="hidden" name="errorForm" value="${searchRequestForm.errorForm}"/>
		<input type="hidden" id="errorMessages" value="${searchRequestForm.errorMessages}" />
		<div id="searchRequest">
			<fieldset>
				<legend>Search Criteria</legend>
				<p>
					<span class="headerLabel">Query Name : </span>
		            <span class="headerValue">
		                 <input name="queryName" id="queryName" class="mandatory" disabled="disabled" readonly="readonly"
		                 value="${searchRequestForm.queryName}"/>
		            </span>
				</p>
				<p>
					<span class="headerLabel">Query Type : </span>
		            <span class="headerValue">
		                 <select disabled="disabled" name="queryType" id="queryType">
		             		<option value="">Select One</option>
		             		<c:forEach items="${requestScope.searchRequestForm.queryTypes}" var="domainVO">
			             		<c:if test="${domainVO.id eq searchRequestForm.queryType}">
			             			<option selected="selected" value="${domainVO.id}">${domainVO.value}</option>
			             		</c:if>
			             		<c:if test="${domainVO.id ne searchRequestForm.queryType}">
			             			<option value="${domainVO.id}">${domainVO.value}</option>
			             		</c:if>
		             		</c:forEach>
		             	</select>  
		            </span>
				</p>
				<p>
					<span class="headerLabel">Project Name : </span>
		            <span class="headerValue">
		                 <select disabled="disabled" name="projectId" id="projectId">
		             		<option value="">Select One</option>
		             		<c:forEach items="${requestScope.searchRequestForm.availableProjects}" var="domainVO">
		             			<c:if test="${domainVO.id eq searchRequestForm.projectId}">
		             				<option value="${domainVO.id}" selected="selected">${domainVO.value}</option>
		             			</c:if>
		             			<c:if test="${domainVO.id ne searchRequestForm.projectId}">
		             				<option value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             		</c:forEach>
		             	</select>
		            </span>
				</p>
				<p>
					<span class="headerLabel">DB Type : </span>
		            <span class="headerValue">
		                 <select disabled="disabled" name="dbType" id="dbType">
		             		<option value="">Select One</option>
		             		<c:forEach items="${requestScope.searchRequestForm.dbTypes}" var="domainVO">
			             		<c:if test="${domainVO.id eq searchRequestForm.dbType}">
			             			<option selected="selected" value="${domainVO.id}">${domainVO.key}</option>
		             			</c:if>
		             			<c:if test="${domainVO.id ne searchRequestForm.dbType}">
			             			<option value="${domainVO.id}">${domainVO.key}</option>
		             			</c:if>
		             			
		             		</c:forEach>
		             	</select> 
		            </span>
				</p>
				<p>
					<span class="headerLabel">Update Frequency: </span>
		            <span class="headerValue">
		                 <select disabled="disabled" name="updateFreq" id="updateFreq">
		             		<option value="">Select One</option>
		             		<c:forEach items="${requestScope.searchRequestForm.updateFreqList}" var="domainVO">
		             			<c:if test="${domainVO.id eq searchRequestForm.updateFreq}">
		             				<option selected="selected" value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             			<c:if test="${domainVO.id ne searchRequestForm.updateFreq}">
		             				<option value="${domainVO.id}">${domainVO.value}</option>
		             			</c:if>
		             		</c:forEach>
		             	</select>
		            </span>
				</p>
				<p>
					<span class="headerLabel"><button class="button" type="submit">Search</button></span>
					<span class="headerLabel"><button class="button" type="reset">Reset</button></span>
				</p>			
			</fieldset>			
		</div>
		<c:if test="${fn:length(searchRequestForm.searchResults) gt 0}">
			<div id="searchResults">
				<fieldset>
					<legend>Search Results</legend>
					<table>
						<thead>
							<tr>
								<th>Query Name</th>
								<th>Application Token</th>
								<th>Application URL</th>
								<th>Application name</th>
								<th>Database type</th>
								<th>Frequency</th>
								<th>Status</th>								
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${searchRequestForm.searchResults}" var="searchResult">
							<tr>
								<td nowrap="nowrap">${searchResult.queryName}</td>
								<td nowrap="nowrap">${searchResult.appToken}</td>
								<td nowrap="nowrap">${searchResult.connectionURL}</td>
								<td nowrap="nowrap">${searchResult.queryApplication}</td>
								<td nowrap="nowrap">${searchResult.queryType}</td>
								<td nowrap="nowrap">${searchResult.queryFrequency}</td>
								<td nowrap="nowrap">${searchResult.queryStatus}</td>
							</tr>
						</c:forEach>					
						</tbody>
					</table>
				</fieldset>
			</div>
		</c:if>
	</form>
</div>