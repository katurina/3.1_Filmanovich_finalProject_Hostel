<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.account" var="account"/>
    <fmt:message bundle="${loc}" key="local.user.my.bookings" var="myBookings"/>
    <title>${account}</title>
    <c:set scope="session" var="url" value="account"/>
</head>
<body>
<c:import url="/menu.jsp"/>
<c:import url="/WEB-INF/user_account/user_head_account.jsp"/>


</body>
</html>
