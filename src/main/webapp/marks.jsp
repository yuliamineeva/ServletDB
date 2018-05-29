<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="asideMain.jsp" %>
<%@ include file="aside.jsp" %>
<div class="container">
    <main class="content">
        <H2>Успеваемость (оценки)</H2>
        <br>
        <div class="marks">
            <table align="center" width="100%" border="1" style="border-color:blue;">
                <tr>
                    <th align="center">ID</th>
                    <th align="center">Дата</th>
                    <th align="center">Курс</th>
                    <th align="center">Тема лекции</th>
                    <th align="center">Студент</th>
                    <th align="center">Оценка</th>
                </tr>
                <c:if test="${marks != null}">
                    <c:forEach items="${marks}" var="marks">
                        <tr>
                            <td align="center">${marks.getId()}</td>
                            <td align="center">${marks.getDate()}</td>
                            <td align="left">${marks.getStudyCourse().getName()}</td>
                            <td align="left">${marks.getLesson().getTopic()}</td>
                            <td align="left">${marks.getStudent().getName()}</td>
                            <td align="center">${marks.getMark().getIntValue()}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <c:if test="${marks == null}">
                <p>The table is not found</p>
                <p>Журнал оценок не найден </p>
            </c:if>

        </div>

    </main><!-- .content -->
</div>
<!-- .container-->

<%@ include file="footer.jsp" %>
