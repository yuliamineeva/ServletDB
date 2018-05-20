<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<%@ include file="aside.jsp" %>
<div class="container">
    <main class="content">
        <%if ((Integer) request.getSession().getAttribute("role") == 1) {%>
        Это контент для админа
        <%} %>
        Это контент общий
    </main><!-- .content -->
</div>
<!-- .container-->

<%@ include file="footer.jsp" %>



