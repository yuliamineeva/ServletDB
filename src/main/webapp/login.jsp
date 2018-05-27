<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head align="center">
    <meta charset="utf-8"/>
    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script><![endif]-->
    <title>Логин/пароль</title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="../resources/style.css" rel="stylesheet">
</head>
<body align="center" style=" padding-top: 30px;">
<%=request.getAttribute("message") + "<br><br>"%>
<%
    Long currentTime = System.currentTimeMillis();
    String timeMessage = "Current Time: " + currentTime;
%>
<%=timeMessage%>
<%=("authErr".equals(request.getParameter("errorMsg"))) ? "Неверное имя пользователя или пароль" : ""%><BR>
<%=("noAuth".equals(request.getParameter("errorMsg"))) ? "Необходимо пройти авторизацию" : ""%><BR>
<form action="${pageContext.request.contextPath}/login" method="post">
    <%--<div align="center">--%>
    <label>Введите логин </label>
    <input type="text" name="userName" value="1"
           style="width: 204px; height: 36px; font-size: 16px;"><BR>
    <label>Введите пароль </label>
    <input type="password" maxlength="25" size="40" name="userPassword" value="1"
           style="width: 204px; height: 36px; font-size: 16px;"><BR>
    <%--</div>--%>
    <input type="submit" value="OK"
           style="width: 46px; height: 26px; margin-top: 20px;">
</form>
</body>
</html>
