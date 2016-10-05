<%--
  Created by IntelliJ IDEA.
  User: sudha
  Date: 04-Oct-16
  Time: 10:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<div id="homeContainer">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/requestUpdate.css">
    <spring:form commandName="requestForm">
        <div id="newRequestLabel">
            <fieldset id="QueryType">
                <legend>Query Information</legend>
                <p>
                    <span class="headerLabel">Query Name : </span>
                    <span class="headerValue">
                        <spring:input path="queryName" cssClass="mandatory"/>
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Project Name : </span>
                    <span class="headerValue">
                        <spring:select path="projectId" items="${requestScope.requestForm.availableProjects}" itemLabel="value"
                                   itemValue="id" cssClass="mandatory" ></spring:select>
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Query Type : </span>
                    <span class="headerValue">
                        <spring:select path="queryType" items="${requestScope.requestForm.queryTypes}" itemLabel="value"
                                   itemValue="id" cssClass="mandatory"></spring:select>
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Query : </span>
                    <span class="headerValue">
                        <spring:textarea path="query" />
                    </span>
                </p>

            </fieldset>

            <fieldset id="dBConnection">
                <legend>Connection Information</legend>
                <p>
                    <span class="headerLabel">Database Vendor: </span>
                    <span class="headerValue">
                        <spring:select path="module.databaseVendorId" items="${requestScope.requestForm.dbTypes}" itemLabel="value"
                                       itemValue="id" cssClass="mandatory"></spring:select>
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Server (IP/ Name): </span>
                    <span class="headerValue">
                        <spring:input path="module.dbServerName" />
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Port Number : </span>
                    <span class="headerValue">
                        <spring:input path="module.dbPortNumber" />
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Database Name : </span>
                    <span class="headerValue">
                        <spring:input path="module.dataBaseName" />
                    </span>
                </p>
                <p>
                    <span class="headerLabel">User Name : </span>
                    <span class="headerValue">
                        <spring:input path="module.dbUserName" />
                    </span>
                </p>
                <p>
                    <span class="headerLabel">Password : </span>
                    <span class="headerValue">
                        <spring:password path="module.dbPassword" />
                    </span>
                </p>
            </fieldset>


        </div>
    </spring:form>
</div>
