<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.user.my.bookings" var="bookings"/>
    <fmt:message bundle="${loc}" key="local.user.settings" var="settings"/>
    <fmt:message bundle="${loc}" key="local.admin.logout" var="logout"/>
</head>
<body>
<div style="margin-left:50px">
    <div style="width: 100%;">
        <li>
            <a href="${pageContext.request.contextPath}/bookings">${bookings}</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/settings">${settings}</a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/controller?command=logout">${logout}</a>
        </li>
    </div>
</div>

</body>
</html>
