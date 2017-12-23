<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.local}"/>
    <fmt:setBundle basename="localization.local" var="loc"/>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <style>
        <%@include file="/css/style.css"%>
    </style>
</head>
<body>

<div class="nav nav-tabs nav-stacked" style="z-index: 100">
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

        <c:choose>
            <c:when test="${sessionScope.user eq null}">
                <li>

                    <div style="margin: 5px">
                        <button onclick="document.getElementById('id01').style.display='block'">
                            <img src="img/toolbar/account.png" alt="account" width="40px"
                                 height="40px">
                        </button>
                        <!-- The Modal -->
                        <c:choose>
                            <c:when test="${requestScope.errorParam eq true}">
                                ${'<div id="id01" class="modal" style="z-index: 999999;display: block">'}
                            </c:when>
                            <c:otherwise>
                                ${'<div id="id01" class="modal" style="z-index: 999999;">'}
                            </c:otherwise>
                        </c:choose>
                        <span onclick=" document.getElementById('id01').style.display='none'" class="close"
                              title="Close Modal">&times;</span>

                        <!-- Modal Content -->
                        <form class="modal-content animate"
                              action="${pageContext.request.contextPath}/controller"
                              method="get">
                            <input type="hidden" name="command" value="login-command"/>

                            <div class="container">
                                <label><b>Login</b></label>
                                <input type="text" placeholder="Enter Username" name="login" required>

                                <label><b>Password</b></label>
                                <input type="password" placeholder="Enter Password" name="password" required>
                                <button type="submit">Login</button>

                            </div>

                            <div class="container" style="background-color:#f1f1f1">
                                <button type="button"
                                        onclick="document.getElementById('id01').style.display='none'"
                                        class="cancelbtn">
                                    Cancel
                                </button>
                                <span class="psw">Forgot <a href="#">password?</a></span>
                            </div>
                        </form>
                            ${'</div>'}
                    </div>

                </li>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${sessionScope.user.role =='ADMIN'}">
                    </c:when>
                    <c:otherwise>
                        <li>
                            <a href="account">
                                <div style="margin: 5px">
                                    <img src="img/toolbar/account.png" alt="account" width="40px" height="40px">
                                </div>
                            </a></li>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </div>
</div>
<script>
    // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>
</body>
</html>
