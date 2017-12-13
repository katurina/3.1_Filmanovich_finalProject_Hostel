<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.admin.users" var="users"/>
    <title>${users}</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user eq null}">
        <c:import url="sign_in.jsp"/>
    </c:when>
    <c:otherwise>
        <c:import url="header.jsp"/>
    </c:otherwise>
</c:choose>
</body>
</html>
