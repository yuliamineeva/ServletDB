<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="container">
    <main class="content">
        <%if ((Integer) request.getSession().getAttribute("role") == 1) {%>
        Страница администратора
        <%} else { %>
        Страница пользователя:
        <%} %>
        <BR>
        <div class="user">
            <BR>
            <c:out value="${sessionScope.userInfo}"/>
        </div>
    </main><!-- .content -->
</div>
<!-- .container-->

<%@ include file="footer.jsp" %>



