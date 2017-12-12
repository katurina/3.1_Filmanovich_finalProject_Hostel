<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:import url="header.jsp"/>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.admin.main" var="main"/>
    <fmt:message bundle="${loc}" key="local.user.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.password" var="password"/>
    <fmt:message bundle="${loc}" key="local.signIn" var="signIn"/>
    <title>${main}</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user eq null}">
        <form action="${pageContext.request.contextPath}/controller" method="get">
            <input type="hidden" name="command" value="login-command-admin"/>
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
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>
</body>
</html>
