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
    <title>${signUp}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="get">
    ${name}<input name="name"><br>
    ${surname}<input name="surname"><br>
    ${login}<input name="login"><br>
    ${password}<input name="password"><br>
    ${email}<input name="email">
    <input type="submit" value="${signUp}"/>
    <input type="hidden" name="command" value="registration-command">
</form>
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
</body>
</html>
