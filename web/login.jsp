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
    <fmt:message bundle="${loc}" key="local.not.valid.password.login" var="not_valid"/>
    <fmt:message bundle="${loc}" key="local.registration" var="registration"/>
    <title>${login}</title>
    <c:set scope="session" var="url" value="login.jsp"/>
</head>
<body>
<div style="float: left">
    <c:if test="${sessionScope.validParam eq \"false\"}">${not_valid}</c:if>
    <form action="${pageContext.request.contextPath}/controller" method="get">
        <input type="hidden" name="command" value="login-command"/>
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
        <a href="registration.jsp">${registration}</a><br>
    </form>

</div>
<c:import url="ru_en_buttons.jsp"/>
</body>
</html>
