<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.admin.booking" var="bookings"/>
    <fmt:message bundle="${loc}" key="local.booking.id" var="idRoom"/>
    <title>${bookings}</title>
    <c:set scope="session" var="url" value="/bookings"/>
    <style>
        <%@include file="/css/style.css"%>
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
<c:import url="/menu.jsp"/>
<div id="page-content-wrapper">
    <c:import url="/WEB-INF/user/user_head_account.jsp"/>
    <table>
        <thead>
        <tr>
            <th>${idRoom}</th>
            <th>${surname}</th>
            <th>${login}</th>
            <th>${email}</th>
            <th>${number}</th>
            <th>${role}</th>
            <th>${banned}</th>
            <th>${edit}</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="booking" items="${requestScope.page.entity}">
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="get-bookings-command">
                <input type="hidden" name="id" value="${booking.id}">
                <tr>
                    <td>${booking.guestroomId}</td>
                    <td>${booking.nightPrice}</td>
                    <td>${booking.startDay}</td>
                    <td>${booking.lastDay}</td>
                    <td>${booking.payed}</td>
                    <td>${booking.bookDay}</td>
                    <td>${booking.finalCost}</td>
                    <td><input type="submit" value="${edit}"/></td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
