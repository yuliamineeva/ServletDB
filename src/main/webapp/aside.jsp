<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebar">
    <p><b>МЕНЮ:</b></p>
    <BR>
    <ul>
        <li><a href="${pageContext.request.contextPath}/Курсы">Курсы</a></li>
        <BR>
        <li><a href="${pageContext.request.contextPath}/Расписание">Расписание</a></li>
        <BR>
        <li><a href="${pageContext.request.contextPath}/Посещаемость">Посещаемость</a></li>
        <BR>
        <li><a href="${pageContext.request.contextPath}/Оценки">Оценки</a></li>
        <BR>
        <%if ((Integer) request.getSession().getAttribute("role") == 1) {%>
        <li><a href="${pageContext.request.contextPath}/list?listType=users">Пользователи</a></li>
        <BR>
        <%} else if ((Integer) request.getSession().getAttribute("role") == 2) { %>
        <li><a href="${pageContext.request.contextPath}/list?listType=students">Студенты</a></li>
        <BR>
        <%} else { %>
        <li><a href="${pageContext.request.contextPath}/list?listType=lecturers">Преподаватели</a></li>
        <BR>
        <% } %>
    </ul>
</div>
