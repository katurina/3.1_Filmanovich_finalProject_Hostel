<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <fmt:message bundle="${loc}" key="local.admin.hostels" var="hostel"/>
    <fmt:message bundle="${loc}" key="local.index.perfect" var="perfect"/>
    <fmt:message bundle="${loc}" key="local.search.book.suit" var="book_suit"/>
    <title>${hostel}</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <c:set scope="session" var="url" value="index.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/menu.css">
</head>
<body>
<c:import url="menu.jsp"/>
<div id="page-content-wrapper">
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
            <li data-target="#myCarousel" data-slide-to="3"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox" style="height: 70%">
            <div class="item active">
                <img src="img/city/city.jpg" alt="City">
                <div class="carousel-caption">
                    <h3>${perfect}</h3>
                    <p><a href="search.jsp">${book_suit}</a></p>
                </div>
            </div>

            <div class="item">
                <img src="img/city/moscow.jpg" alt="Moscow">
                <div class="carousel-caption">
                    <h3>${perfect}</h3>
                    <p><a href="search.jsp">${book_suit}</a></p>
                </div>
            </div>

            <div class="item">
                <img src="img/city/paris1.jpg" alt="Paris">
                <div class="carousel-caption">
                    <h3>${perfect}</h3>
                    <p><a href="search.jsp">${book_suit}</a></p>
                </div>
            </div>
            <div class="item">
                <img src="img/city/moscow3.jpg" alt="Moscow">
                <div class="carousel-caption">
                    <h3>${perfect}</h3>
                    <p><a href="search.jsp">${book_suit}</a></p>
                </div>
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>


</div>
</body>
</html>
