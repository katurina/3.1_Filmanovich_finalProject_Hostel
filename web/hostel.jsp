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

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/menu.css">
    <c:set scope="session" var="url" value="/hostel.jsp?id=${param.id}"/>
</head>
<body>
<c:import url="menu.jsp"/>
<div id="page-content-wrapper">
    <h1 style="
    text-align: center;
    text-transform: uppercase;
    margin-top: 0;
    color: #9eaeee;">${requestScope.hostel.name} </h1>
    <img src="${requestScope.hostel.imgPath}"/>
    <p>${requestScope.hostel.description}</p>
</div>
</body>
</html>
