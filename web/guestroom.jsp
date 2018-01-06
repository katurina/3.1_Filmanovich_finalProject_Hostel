<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.guestroom" var="room"/>
    <fmt:message bundle="${loc}" key="local.guestroom.properties" var="properties"/>
    <fmt:message bundle="${loc}" key="local.guestroom.capacity" var="capacity"/>
    <fmt:message bundle="${loc}" key="local.guestroom.own.bathroom" var="bathroom"/>
    <fmt:message bundle="${loc}" key="local.title.hotel" var="hotel"/>
    <fmt:message bundle="${loc}" key="local.guestroom.night.price" var="nightPrice"/>
    <title>${room}</title>
    <jsp:include page="${pageContext.request.contextPath}/controller">
        <jsp:param name="command" value="view-guestroom-command"/>
        <jsp:param name="id" value="${param.id}"/>
    </jsp:include>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/menu.css">
    <c:set scope="session" var="url" value="/guestroom.jsp?id=${param.id}"/>
</head>
<body>
<c:import url="menu.jsp"/>
<div id="page-content-wrapper">
    <div>
        <c:forEach var="img" items="${requestScope.guestroom.imgPath}">
            <img src="${img}"/>
        </c:forEach>
    </div>
    <div class="money"> ${nightPrice}
        ${requestScope.guestroom.nightPrice}</div>
    <div><p>${requestScope.guestroom.description}</p></div>
    <div class="whole-properties">
        <div>${properties}</div>
        <div class="properties">
            <li>WiFi <c:choose><c:when test="${requestScope.guestroom.wifi eq true}">
                <img src="img/toolbar/tick.svg">
            </c:when><c:otherwise>
                <img src="img/toolbar/cross.svg">
            </c:otherwise></c:choose></li>
            <li>TV <c:choose><c:when test="${requestScope.guestroom.tv eq true}">
                <img src="img/toolbar/tick.svg">
            </c:when><c:otherwise>
                <img src="img/toolbar/cross.svg">
            </c:otherwise></c:choose></li>
            <li>${bathroom}
                <c:choose>
                    <c:when test="${requestScope.guestroom.bath eq true}">
                        <img src="img/toolbar/tick.svg">
                    </c:when><c:otherwise>
                    <img src="img/toolbar/cross.svg">
                </c:otherwise>
                </c:choose>
            </li>
            <li>${capacity}
                ${requestScope.guestroom.capacity}
            </li>


        </div>
    </div>
    <div><a href="${pageContext.request.contextPath}/hostel.jsp?id=${requestScope.guestroom.hostelId}">${hotel}</a>
    </div>
    <div></div>
</div>

</body>
</html>
