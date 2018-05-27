<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="sidebarLec">
    <p><b>МЕНЮ:</b></p>
    <BR>
    <ul>
        <li><a href="${pageContext.request.contextPath}/user/dashboard">Главная</a></li>
        <BR>
        <li><a href="${pageContext.request.contextPath}/Элемент1">Элемент1</a></li>
        <BR>
        <li><a href="${pageContext.request.contextPath}/Элемент2">Элемент2</a></li>
        <BR>
        <li><a href="${pageContext.request.contextPath}/Элемент3">Элемент3</a></li>
        <BR>
        <%if ((Integer) request.getSession().getAttribute("role") == 1) {%>
        <li><a href="${pageContext.request.contextPath}/Элемент4">Элемент4</a></li>
        <BR>
        <%} else if ((Integer) request.getSession().getAttribute("role") == 2) { %>
        <li><a href="${pageContext.request.contextPath}/list?listType=students">Элемент4</a></li>
        <BR>
        <%} else { %>
        <li><a href="${pageContext.request.contextPath}/Элемент5">Элемент5</a></li>
        <BR>
        <% } %>
    </ul>
</div>
