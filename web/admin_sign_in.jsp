<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.user.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.signIn" var="signIn"/>
    <fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="login-admin-command"/>
    <table>
        <tr>
            <td>${login}:</td>
            <td><input name="login"/></td>
        </tr>
        <tr>
            <td>${password}:</td>
            <td><input name="password"/></td>
        </tr>
    </table>
    <input type="submit" value="${signIn}"/><br>
</form>
<div style="float:right;">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="current-page" value="admin_sign_in.jsp">
        <input type="hidden" name="command" value="change-local-command">
        <input type="hidden" name="local" value="ru"/>
        <input type="submit" value="${ru_button}"/>
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="current-page" value="admin_sign_in.jsp">
        <input type="hidden" name="command" value="change-local-command">
        <input type="hidden" name="local" value="en"/>
        <input type="submit" value="${en_button}">
    </form>
</div>
</body>
</html>
