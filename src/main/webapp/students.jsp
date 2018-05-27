<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="asideMain.jsp" %>
<%@ include file="aside.jsp" %>
<div class="container">
    <main class="content">
        <H2>Список студентов</H2>
        <br>
        <div class="studentsList">
            <table align="center" width="70%" border="1" style="border-color:blue;">
                <tr>
                    <th align="center">ID студента</th>
                    <th align="center">ФИО</th>
                    <%if ((Integer) request.getSession().getAttribute("role") == 1) {%>
                    <th align="center">login</th>
                    <% } %>
                    <th align="center">Средняя оценка</th>
                </tr>
                <c:if test="${students != null}">
                    <c:forEach items="${students}" var="student">
                        <tr>
                            <td align="center">${student.getId()}</td>
                            <td align="left">${student.getName()}</td>
                            <%if ((Integer) request.getSession().getAttribute("role") == 1) {%>
                            <td align="center">${student.getLogin()}</td>
                            <% } %>
                            <td align="center">${student.getAverageMark()}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <c:if test="${students == null}">
                <p>The table is not found</p>
                <p>Список студентов не найден </p>
            </c:if>

        </div>

    </main><!-- .content -->
</div>
<!-- .container-->

<%@ include file="footer.jsp" %>

