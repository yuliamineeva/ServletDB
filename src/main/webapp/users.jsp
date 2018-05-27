<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="header.jsp" %>
<%@ include file="asideMain.jsp" %>
<%@ include file="aside.jsp" %>
<div class="container">
    <main class="content">
        <H2>Список всех пользователей</H2>
        <br>
        <div class="usersList">
            <table align="center" width="70%" border="1" style="border-color:blue;">
                <tr>
                    <th align="center">ID пользователя</th>
                    <th align="center">login</th>
                    <th align="center">Роль</th>
                </tr>
                <c:if test="${users != null}">
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td align="center">${user.getId()}</td>
                            <td align="left">${user.getLogin()}</td>
                            <td align="center">${user.getRole_number()}</td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <c:if test="${users == null}">
                <p>The table is not found</p>
                <p>Список пользователей не найден </p>
            </c:if>

        </div>

    </main><!-- .content -->
</div>
<!-- .container-->

<%@ include file="footer.jsp" %>
