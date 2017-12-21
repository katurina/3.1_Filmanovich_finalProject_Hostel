<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.admin.users" var="users"/>
    <fmt:message bundle="${loc}" key="local.user.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.user.name" var="name"/>
    <fmt:message bundle="${loc}" key="local.user.role" var="role"/>
    <fmt:message bundle="${loc}" key="local.user.surname" var="surname"/>
    <fmt:message bundle="${loc}" key="local.user.number" var="number"/>
    <fmt:message bundle="${loc}" key="local.user.email" var="email"/>
    <fmt:message bundle="${loc}" key="local.user.banned" var="banned"/>
    <fmt:message bundle="${loc}" key="local.admin.edit" var="edit"/>
    <fmt:message bundle="${loc}" key="local.button.name.en" var="en_button"/>
    <fmt:message bundle="${loc}" key="local.button.name.ru" var="ru_button"/>
    <title>${users}</title>
    <c:set scope="session" var="url" value="/admin/admin_users"/>
</head>
<body>
<%@include file="/WEB-INF/admin/admin_header.jsp" %>
<jsp:include page="${pageContext.request.contextPath}/controller">
    <jsp:param name="command" value="get-users-command"/>
</jsp:include>
<br>
<table>
    <thead>
    <tr>
        <td>ID</td>
        <th>${name}</th>
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
    <c:forEach var="user" items="${requestScope.page.entity}">
        <form action="${pageContext.request.contextPath}/controller" method="post">
        <input type="hidden" name="command" value="edit-user-command">
        <input type="hidden" name="id" value="${user.id}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.login}</td>
            <td>${user.email}</td>
            <td>${user.number}</td>
            <td>
                <c:choose>
                    <c:when test="${user.role eq 'ADMIN'}">
                        <input type="checkbox" name="role" value="ADMIN" checked/>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="role" value="ADMIN"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${user.banned}">
                        <input type="checkbox" name="banned" value="1" checked/>
                    </c:when>
                    <c:otherwise>
                        <input type="checkbox" name="banned" value="1"/>
                    </c:otherwise>
                </c:choose>
            </td>
            <td><input type="submit" value="${edit}"/></td>
        </tr>
        </form>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
