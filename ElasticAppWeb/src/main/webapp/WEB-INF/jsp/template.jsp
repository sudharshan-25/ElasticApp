<%--
  Created by IntelliJ IDEA.
  User: sudha
  Date: 02-Oct-16
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-16">
    <meta http-equiv="Cache-Control" >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/theme/style.css">
    <title>${param.title}</title>
</head>
<body>

<header>
    <div id="header">
        <span id="logo">
            <img src="#" alt="Logo">
        </span>
        <span id="appName">
            <h2>Elastic Middleware Application</h2>
        </span>
        <span id="user">
            ${sessionScope.userVO.userName}
        </span>
    </div>
</header>
<div id="pagecontent">
    <jsp:include page="${param.page}" flush="true"></jsp:include>
</div>

<footer>
    <div id="footer">
        <span id="copyright">
            copyright - 2016
        </span>
        <span id="credits">
            Thanks to Bits pilani
        </span>
    </div>
</footer>

</body>
</html>
