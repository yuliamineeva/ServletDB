<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<div class="container">
    <main class="content">
        <%if ((Integer) request.getSession().getAttribute("role") == 1) {%>
        Это контент для админа
        <%} else {%>
        Это контент общий
        <%}%>
    </main><!-- .content -->
</div>
<!-- .container-->

<%@ include file="footer.jsp" %>



