<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.admin.booking" var="bookings"/>
    <fmt:message bundle="${loc}" key="local.booking.id" var="idRoom"/>
    <fmt:message bundle="${loc}" key="local.booking.last.date" var="lastDay"/>
    <fmt:message bundle="${loc}" key="local.booking.start.date" var="startDay"/>
    <fmt:message bundle="${loc}" key="local.booking.night.price" var="nightPrice"/>
    <fmt:message bundle="${loc}" key="local.booking.status.payed" var="status"/>
    <fmt:message bundle="${loc}" key="local.booking.day.of.booking" var="bookDay"/>
    <fmt:message bundle="${loc}" key="local.booking.final.cost" var="finalCost"/>
    <fmt:message bundle="${loc}" key="local.booking.pay.booking" var="pay"/>
    <fmt:message bundle="${loc}" key="local.booking.payed.true" var="isPayed"/>
    <fmt:message bundle="${loc}" key="local.booking.payed.false" var="notPayed"/>
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
<jsp:include page="${pageContext.request.contextPath}/controller">
    <jsp:param name="command" value="get-bookings-command"/>
</jsp:include>
<c:import url="/menu.jsp"/>
<div id="page-content-wrapper">
    <c:import url="/WEB-INF/user/user_head_account.jsp"/>
    <table>
        <thead>
        <tr>
            <th>${idRoom}</th>
            <th>${nightPrice}</th>
            <th>${startDay}</th>
            <th>${lastDay}</th>
            <th>${status}</th>
            <th>${bookDay}</th>
            <th>${finalCost}</th>
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
                    <td><c:choose>
                        <c:when test="${booking.payed eq false}">
                            <input type="submit" value="${pay}"/>
                        </c:when>
                        <c:otherwise>
                            ${isPayed}</c:otherwise>
                    </c:choose></td>
                    <td>${booking.bookDay}</td>
                    <td>${booking.finalCost}</td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
