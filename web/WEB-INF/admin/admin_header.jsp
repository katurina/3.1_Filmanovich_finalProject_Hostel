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
    <fmt:message bundle="${loc}" key="local.admin.logout" var="logout"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">

</head>
<body>
<div style="float:left;">
    <ul>
        <li><a href="${pageContext.request.contextPath}/admin/admin_users">${users}</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/admin_hostels">${hostel}</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/admin_guestrooms">${rooms}</a></li>
        <li><a href="${pageContext.request.contextPath}/admin/admin_user_bookings">${bookings}</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=logout">${logout}</a></li>
    </ul>
</div>
<c:import url="/ru_en_buttons.jsp"/>
</body>
</html>