<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="asideMain.jsp" %>
<%@ include file="aside.jsp" %>
<div class="container">
    <main class="content">
        <H2>Курсы</H2>
        <br>
        <div class="courses">
            <table align="center" width="70%" border="1" style="border-color:blue;">
                <tr>
                    <th align="center">Название курса</th>
                    <th align="center">Преподаватель</th>
                </tr>
                <c:if test="${studyCourses != null}">
                    <c:forEach items="${studyCourses}" var="studyCourse">
                        <tr>
                            <td align="center">${studyCourse.getName()}</td>
                            <td align="left">${studyCourse.getLecturer().getName()}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <c:if test="${studyCourses == null}">
                <p>The table is not found</p>
                <p>Список курсов не найден </p>
            </c:if>

        </div>

    </main><!-- .content -->
</div>
<!-- .container-->

<%@ include file="footer.jsp" %>
