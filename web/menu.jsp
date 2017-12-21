<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="nav nav-tabs nav-stacked" style="background: #999999; width:50px; height: 100vh">
    <div style="height: calc(100vh - 100px);">
        <li><a href="index.jsp">
            <div style="margin: 5px"><img src="img/toolbar/home.png" alt="home" width="40px" height="40px"></div>
        </a></li>
        <li><a href="#">
            <div style="margin: 5px"><img src="img/toolbar/search.png" alt="home" width="40px" height="40px"></div>
        </a></li>
        <li>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="change-local-command">
                <input type="hidden" name="local" value="ru"/>
                <div style="margin: 8px">
                    <input type="image" style="border: solid 0 #000000; width: 34px;height: 30px"
                           src="${pageContext.request.contextPath}/img/toolbar/ru.png"/>
                </div>
            </form>
        </li>
        <li>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="command" value="change-local-command">
                <input type="hidden" name="local" value="en"/>
                <div style="margin: 8px">
                    <input type="image" style="border: solid 0 #000000; width: 34px;height: 30px"
                           src="${pageContext.request.contextPath}/img/toolbar/en.jpg"/>
                </div>
            </form>
        </li>
    </div>
    <div style="height: 100px">
        <li><a href="">
            <div style="margin: 5px"><img src="img/toolbar/question.png" alt="question" width="40px" height="40px">
            </div>
        </a></li>
        <li><a href="">
            <div style="margin: 5px">
                <img src="img/toolbar/account.png" alt="account" width="40px" height="40px">
            </div>
        </a></li>
    </div>
</div>
</body>
</html>
