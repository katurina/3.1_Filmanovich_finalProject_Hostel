<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.admin.hostels" var="hostel"/>
    <fmt:message bundle="${loc}" key="local.admin.users" var="users"/>
    <fmt:message bundle="${loc}" key="local.admin.guestrooms" var="rooms"/>
    <fmt:message bundle="${loc}" key="local.admin.booking" var="bookings"/>
    <fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
</head>
<body>
<div style="float:right;">
    <ul>
        <li><a href="users.jsp">${users}</a></li>
        <li><a href="hostels.jsp">${hostel}</a></li>
        <li><a href="guestrooms.jsp">${rooms}</a></li>
        <li><a href="user_bookings.jsp">${bookings}</a></li>
    </ul>
</div>
<div style="float:right;">
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
</div>
</body>
</html>