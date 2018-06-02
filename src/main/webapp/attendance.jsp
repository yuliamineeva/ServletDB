<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="asideMain.jsp" %>
<%@ include file="aside.jsp" %>
<div class="container">
    <main class="content">
        <H2>Посещение занятий</H2>
        <br>
        <%
            Integer role = (Integer) request.getSession().getAttribute("role");
        %>
        <form action="${pageContext.request.contextPath}/list" name="PostName" id="dynamic_selects">
            <select onChange="this.form.submit()" class="select-lessons" id="less" name="less_attendance"
                    style="width: 135px; margin-right: 20px;">
                <option default>Выбрать занятие</option>
                <c:forEach items="${lessons}" var="lesson">
                    <option value="${lesson.getId()}"><c:out value="${lesson.getTopic()}"/></option>
                </c:forEach>
            </select>
        </form>

        <select class="select-date" id="date" name="date" style="width: 135px; margin-right: 20px;">
            <option default>Выбрать дату</option>

            <c:forEach items="${dates}" var="date">
                <option value="${date}"><c:out value="${date}"/></option>
            </c:forEach>
        </select>

        <%if (role != 3) {%>
        <select class="select-student" id="student" name="student" style="width: 135px; margin-right: 20px;">
            <option default>Выбрать студента</option>

            <c:forEach items="${students}" var="student">
                <option value="${student.getId()}"><c:out value="${student.getName()}"/></option>
            </c:forEach>
        </select>
        <% } %>
        <br>
        <br>
        <br>

        <div class="attendance">
            <table align="center" width="100%" border="1" style="border-color:blue;">
                <tr>
                    <%if (role != 3) {%>
                    <th align="center">ID посещения</th>
                    <% } %>
                    <th align="center">Дата</th>
                    <th align="center">Тема лекции</th>
                    <%if (role != 3) {%>
                    <th align="center">Студент</th>
                    <% } %>
                    <th align="center">Посещение</th>
                </tr>
                <c:if test="${attendance != null}">
                    <c:forEach items="${attendance}" var="attendance">
                        <tr>
                            <%if (role != 3) {%>
                            <td align="center">${attendance.getId()}</td>
                            <% } %>
                            <td align="center">${attendance.getDate()}</td>
                            <td align="left">${attendance.getLesson().getTopic()}</td>
                            <%if (role != 3) {%>
                            <td align="left">${attendance.getStudent().getName()}</td>
                            <% } %>
                            <td align="center">${attendance.isBe_present()}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <c:if test="${attendance == null}">
                <p>The table is not found</p>
                <p>Таблица посещений не найдена </p>
            </c:if>

        </div>

    </main><!-- .content -->
</div>
<!-- .container-->

<%@ include file="footer.jsp" %>
