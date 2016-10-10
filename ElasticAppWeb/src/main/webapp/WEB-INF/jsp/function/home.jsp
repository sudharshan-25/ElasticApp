<%--
  Created by IntelliJ IDEA.
  User: sudha
  Date: 02-Oct-16
  Time: 5:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/theme/home.css" rel="stylesheet">

<div id="homeContainer">
    <div id="newRequest">
        <span>
            <a href="${pageContext.request.contextPath}/newRequest">New Request</a>
        </span>
    </div>
    <div id="updateRequest">
        <span>
            <a href="${pageContext.request.contextPath}/searchRequests">Update Existing Request</a>
        </span>
    </div>

</div>