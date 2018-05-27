<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script><![endif]-->
    <title></title>
    <meta name="keywords" content=""/>
    <meta name="description" content=""/>
    <link href="../resources/style.css" rel="stylesheet">
</head>

<body>

<div class="wrapper">
    <header class="header" align="center">
        <H1> База данных успеваемости студентов</H1>
        <H1> Database of student performance</H1>
        <div class="logout" align="right">
            <c:out value="Ваш логин: "/>
            <c:out value="${sessionScope.login}"/>
            <a href="${pageContext.request.contextPath}/login?action=logout">Выход</a>
        </div>
    </header>
    <!-- .header-->
    <!-- </div> закрывается footer-->
