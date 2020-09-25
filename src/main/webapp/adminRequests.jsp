<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<!--
Dopetrope by HTML5 UP
html5up.net | @ajlkn
Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->


<fmt:setLocale value="${cookie['language'].value}"/>
<fmt:setBundle basename="language"/>


<html>
<head>
    <title><fmt:message key="label.adminRequests"/></title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/assets/css/main.css"/>--%>
    <style>
        <%@include file="view/assets/css/main.css" %>
    </style>
</head>
<body class="homepage is-preload">
<div id="page-wrapper">

    <!-- Header -->
    <section id="header">

        <!-- Logo -->
        <h6><a href="${pageContext.request.contextPath}/languageButton?language=ru">ru</a>
            <a>|</a>
            <a href="${pageContext.request.contextPath}/languageButton?language=en">en</a></h6>
        <h1><a href="${pageContext.request.contextPath}/">ABITAPP</a></h1>

        <!-- Nav -->
        <!-- Nav -->
        <nav id="nav">
            <ul>
                <li><a href="${pageContext.request.contextPath}/"><fmt:message key="label.home"/> </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/university"><fmt:message key="label.university"/></a>
                </li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li><a href="${pageContext.request.contextPath}/profile">${sessionScope.user.name}</a></li>
                        <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message
                                key="label.signOut"/></a></li>
                    </c:when>
                    <c:when test="${not empty sessionScope.admin}">
                        <li><a href="${pageContext.request.contextPath}/admin/profile">${sessionScope.admin.email}</a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message
                                key="label.signOut"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/signIn"><fmt:message key="label.signIn"/></a>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/signUp"><fmt:message key="label.signUp"/></a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
            <p></p>
            <ul>
                <li><a href="${pageContext.request.contextPath}/admin/profile"><fmt:message
                        key="label.personalInfo"/> </a></li>
                <li><a href="${pageContext.request.contextPath}/admin/edit"><fmt:message
                        key="label.edit"/></a></li>
                <li class="current"><a href="${pageContext.request.contextPath}/admin/requests"><fmt:message
                        key="label.adminRequests"/></a></li>
                <li><a href="${pageContext.request.contextPath}/admin/rating"><fmt:message
                        key="label.rating"/></a></li>
                <li><a href="${pageContext.request.contextPath}/admin/password"><fmt:message
                        key="label.updatePassword"/> </a></li>
            </ul>
        </nav>
    </section>

    <!-- Main -->
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-12">

                    <!-- Portfolio -->
                    <section>
                        <header class="major">
                            <h2><fmt:message key="label.adminRequests"/></h2>
                        </header>
                        <form action="${pageContext.request.contextPath}/adminRequestsButton" method="post">
                            <div class="col-12-medium col-12-small">
                                <section class="box">
                                    <table border="1">
                                        <tr>
                                            <th><fmt:message key="label.name"/></th>
                                            <th><fmt:message key="label.examMark"/></th>
                                            <th><fmt:message key="label.specialty"/></th>
                                            <th><fmt:message key="label.edit"/></th>
                                            <th><fmt:message key="label.adminMessage"/></th>
                                        </tr>
                                        <c:forEach var="specialtyRequests" items="${requestScope.usersWithRequests}">
                                            <c:forEach var="user" items="${specialtyRequests}">
                                                <form method="post"
                                                      action="${pageContext.request.contextPath}/adminRequestsButton">
                                                    <tr>
                                                        <th>${user.key.name} ${user.key.surname}</th>
                                                        <th>${user.value}</th>
                                                        <th>${user.key.requestSpecialty.name}</th>
                                                        <th>
                                                            <button type="submit" name="action"
                                                                    value="accept ${user.key.id} ${user.key.requestSpecialty.id}">
                                                                <fmt:message key="label.accept"/></button>
                                                            <button type="submit" name="action"
                                                                    value="reject ${user.key.id} ${user.key.requestSpecialty.id}">
                                                                <fmt:message key="label.reject"/></button>
                                                        </th>
                                                        <th><input type="text" name="adminMessage"></th>
                                                    </tr>
                                                </form>
                                            </c:forEach>
                                        </c:forEach>
                                    </table>
                                </section>
                            </div>
                        </form>
                    </section>
                </div>
            </div>
        </div>
    </section>
</div>

<!-- Scripts -->
<script src="${pageContext.request.contextPath}/view/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/view/assets/js/jquery.dropotron.min.js"></script>
<script src="${pageContext.request.contextPath}/view/assets/js/browser.min.js"></script>
<script src="${pageContext.request.contextPath}/view/assets/js/breakpoints.min.js"></script>
<script src="${pageContext.request.contextPath}/view/assets/js/util.js"></script>
<script src="${pageContext.request.contextPath}/view/assets/js/main.js"></script>

</body>
</html>