<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.title.hotel" var="hotel"/>
    <title>${hotel}</title>
    <c:set scope="session" var="url" value="hostel.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/controller">
        <jsp:param name="command" value="view-hostel"/>
        <jsp:param name="id" value="${param.id}"/>
    </jsp:include>
    <c:set scope="session" var="url" value="/hostel.jsp?id=${param.id}"/>
</head>
<body>
<c:import url="menu.jsp"/>
<h1 style="
    text-align: center;
    text-transform: uppercase;
    margin-top: 0;
    color: #9eaeee;">${requestScope.hostel.name} </h1>
    <img src="${requestScope.hostel.}">
</body>
</html>
