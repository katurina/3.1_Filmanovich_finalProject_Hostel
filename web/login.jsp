<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.signIn" var="signIn"/>
    <fmt:message bundle="${loc}" key="local.home.page" var="home"/>
    <fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
    <title>${login}</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/controller" method="get">
    <input type="hidden" name="command" value="login-command"/>
    ${login}: <input name="login"/>
    ${password}: <input name="password"/><br>
    <input type="submit" value="${signIn}"/>
    <a href="index.jsp">${home}</a>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="current-page" value="login.jsp">
    <input type="hidden" name="command" value="change-local-command">
    <input type="hidden" name="local" value="ru"/>
    <input type="submit" value="${ru_button}"/>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="current-page" value="login.jsp">
    <input type="hidden" name="command" value="change-local-command">
    <input type="hidden" name="local" value="en"/>
    <input type="submit" value="${en_button}">
</form>
</body>
</html>
