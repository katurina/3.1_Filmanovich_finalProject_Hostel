<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.motivation.text" var="motivation"/>
    <fmt:message bundle="${loc}" key="local.signIn" var="signIn"/>
    <fmt:message bundle="${loc}" key="local.signUp" var="signUp"/>
    <fmt:message bundle="${loc}" key="local.or" var="oror"/>
    <fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
    <fmt:message bundle="${loc}" key="local.title.hotel" var="hostel"/>
    <fmt:message bundle="${loc}" key="local.welcome.text" var="hi"/>
    <title>${hostel}</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user eq null}">
        ${motivation}<a href="login.jsp">${signIn}</a>
        ${oror}<a href="registration.jsp">${signUp}</a>
    </c:when>
    <c:otherwise>
        ${hi} ${sessionScope.user.name}
    </c:otherwise>
</c:choose>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="current-page" value="index.jsp">
    <input type="hidden" name="command" value="change-local-command">
    <input type="hidden" name="local" value="ru"/>
    <input type="submit" value="${ru_button}"/>
</form>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="current-page" value="index.jsp">
    <input type="hidden" name="command" value="change-local-command">
    <input type="hidden" name="local" value="en"/>
    <input type="submit" value="${en_button}">
</form>

</body>
</html>
