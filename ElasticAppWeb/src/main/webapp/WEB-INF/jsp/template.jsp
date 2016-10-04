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
    <div>
        <div id="logo">
            <img src="#" alt="Logo">
        </div>
        <div id="appName">
            <h4>Elastic Middleware Application</h4>
        </div>
        <div id="user">
            ${sessionScope.userVO.userName}
        </div>
    </div>
</header>
<div id="pagecontent">

</div>

<footer>
    <div>
        <div id="copyright">
            copyright - 2016
        </div>
        <div id="credits">
            Thanks to Bits pilani
        </div>
    </div>
</footer>

</body>
</html>
