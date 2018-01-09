<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.menu.search" var="search"/>
    <fmt:message bundle="${loc}" key="local.search.criteria.search" var="searchers"/>
    <fmt:message bundle="${loc}" key="local.booking.night.price" var="price"/>
    <fmt:message bundle="${loc}" key="local.search.night.price.from" var="fromPrice"/>
    <fmt:message bundle="${loc}" key="local.search.night.price.to" var="toPrice"/>
    <fmt:message bundle="${loc}" key="local.search.date" var="dates"/>
    <fmt:message bundle="${loc}" key="local.search.date.from" var="dateFrom"/>
    <fmt:message bundle="${loc}" key="local.search.date.to" var="dateTo"/>
    <fmt:message bundle="${loc}" key="local.search.required.param" var="required"/>
    <fmt:message bundle="${loc}" key="local.search.shower" var="shower"/>
    <fmt:message bundle="${loc}" key="local.search.book" var="book"/>
    <title>${search}</title>
    <c:set scope="session" var="url" value="search.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/controller">
        <jsp:param name="command" value="get-required-guestrooms-command"/>
        <jsp:param name="page" value="${param.page}"/>
        <jsp:param name="sorting" value="${param.sorting}"/>
        <jsp:param name="price-from" value="${param.priceFrom}"/>
        <jsp:param name="price-to" value="${param.priceTo}"/>
        <jsp:param name="date-from" value="${param.dateFrom}"/>
        <jsp:param name="date-to" value="${param.dateTo}"/>
        <jsp:param name="wifi" value="${param.wifi}"/>
        <jsp:param name="tv" value="${param.tv}"/>
        <jsp:param name="shower" value="${param.shower}"/>
    </jsp:include>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="css/menu.css">
    <c:set scope="session" var="url"
           value="/search.jsp?page=${param.page}&sorting=${param.sorting}&price-from=${param.priceFrom}&price-to=${param.priceTo}&date-from=${param.dateFrom}&date-to=${param.dateTo}&wifi=${param.wifi}&tv=${param.tv}&shower=${param.shower}"/>
</head>
<body>
<c:import url="menu.jsp"/>
<div id="page-content-wrapper">
    <div class="main-window">
        <div class="criteria-search">
            <button class="hide-search-button" onclick="showDropdown()">
                ${searchers}
            </button>
            <div id="myDropdownSearch" class="search">
                <form action="search.jsp" method="get">
                    <div class="criteria">
                        <div>
                            ${price}
                        </div>
                        <div>
                            ${fromPrice}
                            <input type="text" name="price-from" pattern="[0-9]*"/>
                            ${toPrice}
                            <input type="text" name="price-to" pattern="[0-9]*"/>
                        </div>
                    </div>
                    <div class="criteria">
                        <div>
                            ${dates}
                        </div>
                        <div>
                            ${dateFrom}
                            <input type="date" name="date-from" pattern="[0-9]*"/>
                            ${dateTo}
                            <input type="date" name="date-to" pattern="[0-9]*"/>
                        </div>
                    </div>
                    <div class="criteria">
                        <div>${required}</div>
                        <div>
                            <div>wifi<input type="checkbox" name="wifi"/></div>
                            <div>TV<input type="checkbox" name="tv"/></div>
                            <div>${shower}<input type="checkbox" name="shower"/></div>
                        </div>
                    </div>
                    <input type="button" value="Search"/>
                    <input type="hidden" name="command" value="get-required-guestrooms-command">
                </form>
            </div>
        </div>
        <div class="room-hotel">
            <img src="">
            <div class="room-description">

            </div>
            <a href="/room1">${book}</a>
            <span class="clear"></span>
        </div>
    </div>
</div>

<script>
    function showDropdown() {
        var x = document.getElementById("myDropdownSearch");
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    };
</script>
</body>
</html>