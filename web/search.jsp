<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.menu.search" var="search"/>
    <title>${search}</title>
    <c:set scope="session" var="url" value="search.jsp"/>
    <jsp:include page="${pageContext.request.contextPath}/controller">
        <jsp:param name="command" value="get-desired-guestrooms-command"/>
        <jsp:param name="sort" value="777"/>
    </jsp:include>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/search.css">
    <link rel="stylesheet" href="css/menu.css">
    <script src="js/search.js"></script>
    <c:set scope="session" var="url" value="/search.jsp"/>
</head>
<body>
<c:import url="menu.jsp"/>
<div id="page-content-wrapper">
    <div class="main-window">
        <div class="criteria-search">
            <button class="hide-search-button" onclick="myFunction()">
                Criteria of search
            </button>
            <div id="myDropdownSearch" class="search">
                <div class="criteria">
                    <div>
                        Price of night
                    </div>
                    <div>
                        from
                        <input type="text" pattern="[0-9]*"/>
                        to
                        <input type="text" pattern="[0-9]*"/>
                    </div>
                </div>
                <div class="criteria">
                    <div>
                        Dates
                    </div>
                    <div>
                        from
                        <input type="date" pattern="[0-9]*"/>
                        to
                        <input type="date" pattern="[0-9]*"/>
                    </div>
                </div>
                <div class="criteria">
                    <div>Mandatory availability</div>
                    <div>
                        <div>wifi<input type="checkbox"/></div>
                        <div>TV<input type="checkbox"/></div>
                        <div>Shower<input type="checkbox"/></div>
                    </div>
                </div>
                <input type="button" value="Search"/>
            </div>
        </div>
        <div class="room-hotel">
            <img src="">
            <div class="room-description">

            </div>
            <a href="/room1">Book</a>
            <span class="clear"></span>
        </div>
    </div>
</div>
</body>
</html>