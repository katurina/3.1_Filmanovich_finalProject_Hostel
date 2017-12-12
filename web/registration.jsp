<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.registration" var="signUp"/>
    <fmt:message bundle="${loc}" key="local.user.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.user.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.user.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.user.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.user.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.user.number" var="number"/>
    <title>${signUp}</title>
</head>
<body>
<div style="float: left">
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <table>
            <tr>
                <td>${name}</td>
                <td><input name="name"></td>
            </tr>
            <tr>
                <td>${surname}</td>
                <td><input name="surname"></td>
            </tr>
            <tr>
                <td>${login}</td>
                <td><input name="login"></td>
            </tr>
            <tr>
                <td>${password}</td>
                <td><input name="password"></td>
            </tr>
            <tr>
                <td>${email}</td>
                <td><input name="email"></td>
            </tr>
            <tr>
                <td>${number}</td>
                <td><input name="number"></td>
            </tr>
        </table>
        <br><input type="submit" value="${signUp}"/>
        <input type="hidden" name="command" value="registration-command">
    </form>
</div>
<div style="float:right;">
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="current-page" value="registration.jsp">
        <input type="hidden" name="command" value="change-local-command">
        <input type="hidden" name="local" value="ru"/>
        <input type="submit" value="${ru_button}"/>
    </form>
    <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="current-page" value="registration.jsp">
        <input type="hidden" name="command" value="change-local-command">
        <input type="hidden" name="local" value="en"/>
        <input type="submit" value="${en_button}">
    </form>
</div>
</body>
</html>
