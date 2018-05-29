<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="asideMain.jsp" %>
<%@ include file="aside.jsp" %>
<div class="container">
    <main class="content">
        <H2>Расписание занятий</H2>
        <br>
        <div class="lessons">
            <table align="center" width="70%" border="1" style="border-color:blue;">
                <tr>
                    <th align="center">ID лекции</th>
                    <th align="center">Дата</th>
                    <th align="center">Тема</th>
                    <th align="center">Курс</th>
                </tr>
                <c:if test="${lessons != null}">
                    <c:forEach items="${lessons}" var="lesson">
                        <tr>
                            <td align="center">${lesson.getId()}</td>
                            <td align="center">${lesson.getDate()}</td>
                            <td align="left">${lesson.getTopic()}</td>
                            <td align="left">${lesson.getStudyCourse().getName()}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <c:if test="${lessons == null}">
                <p>The table is not found</p>
                <p>Занятия не найдены </p>
            </c:if>

        </div>

    </main><!-- .content -->
</div>
<!-- .container-->

<%@ include file="footer.jsp" %>
