<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.success" var="success"/>
    <fmt:message bundle="${loc}" key="local.operation.success" var="operationSuccess"/>
    <title>${success}</title>
    <c:set scope="session" var="url" value="/admin/success"/>
    <fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
</head>
<body>
<%@include file="/WEB-INF/admin/admin_header.jsp" %>
<div class="success">${operationSuccess}</div>
</body>
</html>
