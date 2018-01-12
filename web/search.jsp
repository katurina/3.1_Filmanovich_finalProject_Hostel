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
    <fmt:message bundle="${loc}" key="local.search.date.from" var="from"/>
    <fmt:message bundle="${loc}" key="local.guestroom" var="guestroom"/>
    <fmt:message bundle="${loc}" key="local.search.capacity" var="capacity"/>
    <fmt:message bundle="${loc}" key="local.search.capacity.to" var="capacityTo"/>
    <fmt:message bundle="${loc}" key="local.search.capacity.from" var="capacityFrom"/>
    <fmt:message bundle="${loc}" key="local.search.city.london" var="london"/>
    <fmt:message bundle="${loc}" key="local.search.city.peking" var="peking"/>
    <fmt:message bundle="${loc}" key="local.search.city.moscow" var="moscow"/>
    <fmt:message bundle="${loc}" key="local.search.city.liverpool" var="liverpool"/>
    <fmt:message bundle="${loc}" key="local.search.city.shanghai" var="shanghai"/>
    <fmt:message bundle="${loc}" key="local.text.form" var="textFrom"/>
    <title>${search}</title>
    <c:set scope="session" var="url" value="search.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/controller">
        <jsp:param name="command" value="get-required-guestrooms-command"/>
        <jsp:param name="page" value="${param.page}"/>
        <jsp:param name="sort" value="${param.sorting}"/>
        <jsp:param name="priceFrom" value="${param.priceFrom}"/>
        <jsp:param name="priceTo" value="${param.priceTo}"/>
        <jsp:param name="dateFrom" value="${param.dateFrom}"/>
        <jsp:param name="dateTo" value="${param.dateTo}"/>
        <jsp:param name="wifi" value="${param.wifi}"/>
        <jsp:param name="tv" value="${param.tv}"/>
        <jsp:param name="shower" value="${param.shower}"/>
        <jsp:param name="capacityFrom" value="${param.capacityFrom}"/>
        <jsp:param name="capacityTo" value="${param.capacityTo}"/>
    </jsp:include>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="css/menu.css">
    <c:set scope="session" var="url"
           value="/search.jsp?page=${param.page}&sort=${param.sorting}&priceFrom=${param.priceFrom}&priceTo=${param.priceTo}&dateFrom=${param.dateFrom}&dateTo=${param.dateTo}&wifi=${param.wifi}&tv=${param.tv}&shower=${param.shower}&capacityFrom=${param.capacityFrom}&capacityTo=${param.capacityTo}&city=${param.city}"/>
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
                    <input type="hidden" name="command" value="get-required-guestrooms-command">
                    <div class="criteria">
                        <select name="city">
                            <option value="any">-</option>
                            <option value="${london}">${london}</option>
                            <option value="${moscow}">${moscow}</option>
                            <option value="${liverpool}">${liverpool}</option>
                            <option value="${peking}">${peking}</option>
                            <option value="${shanghai}">${shanghai}</option>
                        </select>
                    </div>
                    <div class="criteria">
                        <div>
                            ${price}
                        </div>
                        <div>
                            ${fromPrice}
                            <input type="text" name="priceFrom" pattern="[0-9]*"/>
                            ${toPrice}
                            <input type="text" name="priceTo" pattern="[0-9]*"/>
                        </div>
                    </div>
                    <div class="criteria">
                        <div>
                            ${dates}
                        </div>
                        <div>
                            ${dateFrom}
                            <input type="date" name="dateFrom" pattern="[0-9]*"/>
                            ${dateTo}
                            <input type="date" name="dateTo" pattern="[0-9]*"/>
                        </div>
                    </div>
                    <div class="criteria">
                        <div>
                            ${capacity}
                        </div>
                        <div>
                            ${capacityFrom}
                            <input type="text" name="capacityFrom" pattern="[0-9]*"/>
                            ${capacityTo}
                            <input type="text" name="capacityTo" pattern="[0-9]*"/>
                        </div>
                    </div>
                    <div class="criteria">
                        <div>${required}</div>
                        <div>
                            <div>wifi<input type="checkbox" value="true" name="wifi"/></div>
                            <div>TV<input type="checkbox" value="true" name="tv"/></div>
                            <div>${shower}<input type="checkbox" value="true" name="shower"/></div>
                        </div>
                    </div>
                    <input type="submit" value="${search}" class="search-button"/>
                </form>
            </div>
        </div>
        <c:forEach var="room" items="${requestScope.page.entity}">
            <div class="room-hotel">
                <div class="money">${room.nightPrice}</div>
                <div class="img-down"><img src="${room.imgPath[0]}"></div>
                <div class="room-description">
                        ${room.description}
                </div>
                <a href="">${book}</a>
                <a href="guestroom.jsp?id=${room.id}">${guestroom}</a>
                <span class="clear"></span>
            </div>
        </c:forEach>
    </div>
    <div class="down-pagination">
        <c:choose> <c:when test="${requestScope.page.currentPage gt 1}">
            <a href="${pageContext.request.contextPath}/search.jsp?page=1&sort=${param.sorting}&priceFrom=${param.priceFrom}&priceTo=${param.priceTo}&dateFrom=${param.dateFrom}&dateTo=${param.dateTo}&wifi=${param.wifi}&tv=${param.tv}&shower=${param.shower}&capacityFrom=${param.capacityFrom}&capacityTo=${param.capacityTo}&city=${param.city}"><<</a>&#8195;
            <a href="${pageContext.request.contextPath}/search.jsp?page=${requestScope.page.currentPage - 1}&sort=${param.sorting}&priceFrom=${param.priceFrom}&priceTo=${param.priceTo}&dateFrom=${param.dateFrom}&dateTo=${param.dateTo}&wifi=${param.wifi}&tv=${param.tv}&shower=${param.shower}&capacityFrom=${param.capacityFrom}&capacityTo=${param.capacityTo}&city=${param.city}"><</a>&#8195;
        </c:when>
            <c:otherwise>
                &#8195;<<&#8195;<&#8195;
            </c:otherwise>
        </c:choose>
        &#8195;${requestScope.page.currentPage} ${textFrom} ${requestScope.page.numberOfPages}&#8195;
        <c:choose><c:when test="${requestScope.page.currentPage lt requestScope.page.numberOfPages}">
            <a href="${pageContext.request.contextPath}/search.jsp?page=${requestScope.page.currentPage + 1}&sort=${param.sorting}&priceFrom=${param.priceFrom}&priceTo=${param.priceTo}&dateFrom=${param.dateFrom}&dateTo=${param.dateTo}&wifi=${param.wifi}&tv=${param.tv}&shower=${param.shower}&capacityFrom=${param.capacityFrom}&capacityTo=${param.capacityTo}&city=${param.city}">></a>&#8195;
            <a href="${pageContext.request.contextPath}/search.jsp?page=${requestScope.page.numberOfPages}&sort=${param.sorting}&priceFrom=${param.priceFrom}&priceTo=${param.priceTo}&dateFrom=${param.dateFrom}&dateTo=${param.dateTo}&wifi=${param.wifi}&tv=${param.tv}&shower=${param.shower}&capacityFrom=${param.capacityFrom}&capacityTo=${param.capacityTo}&city=${param.city}">>></a>
        </c:when>
            <c:otherwise>
                &#8195;>&#8195;>>&#8195;
            </c:otherwise>
        </c:choose>
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
    }
</script>
</body>
</html>