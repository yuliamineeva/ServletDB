<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="asideMain.jsp" %>
<%@ include file="aside.jsp" %>
<div class="container">
    <main class="content">
        <H2>Список преподавателей</H2>
        <br>
        <div class="lecturersList">
            <table align="center" width="70%" border="1" style="border-color:blue;">
                <tr>
                    <th align="center">ID преподавателя</th>
                    <th align="center">Фамилия Имя Отчество</th>
                    <%if ((Integer) request.getSession().getAttribute("role") == 1) {%>
                    <th align="center">login</th>
                    <% } %>
                </tr>
                <c:if test="${lecturers != null}">
                    <c:forEach items="${lecturers}" var="lecturer">
                        <tr>
                            <td align="center">${lecturer.getId()}</td>
                            <td align="left">${lecturer.getName()}</td>
                            <%if ((Integer) request.getSession().getAttribute("role") == 1) {%>
                            <td align="center">${lecturer.getLogin()}</td>
                            <% } %>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <c:if test="${lecturers == null}">
                <p>The table is not found</p>
                <p>Список преподавателей не найден </p>
            </c:if>

        </div>

    </main><!-- .content -->
</div>
<!-- .container-->

<%@ include file="footer.jsp" %>

