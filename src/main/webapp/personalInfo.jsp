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
    <title><fmt:message key="label.personalInfo"/></title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/assets/css/main.css"/>

    <style>
        a {
            text-decoration: none; /* Отменяем подчеркивание у ссылки */
        }
    </style>

</head>
<body class="homepage is-preload">
<div id="page-wrapper">

    <!-- Header -->
    <section id="header">

        <!-- Logo -->
        <h1><a href="${pageContext.request.contextPath}/">ABITAPP</a></h1>

        <!-- Nav -->
        <nav id="nav">
            <ul>
                <li><a href="${pageContext.request.contextPath}/"><fmt:message key="label.home"/> </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/university"><fmt:message key="label.university"/></a>
                </li>
                <c:if test="${empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/signIn"><fmt:message key="label.signIn"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/signUp"><fmt:message key="label.signUp"/></a></li>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <li class="current"><a
                            href="${pageContext.request.contextPath}/profile">${sessionScope.user.name}</a></li>
                    <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message
                            key="label.signOut"/></a></li>
                </c:if>
            </ul>
            <p></p>
            <ul>
                <li class="current"><a href="${pageContext.request.contextPath}/profile/personalInfo"><fmt:message
                        key="label.personalInfo"/> </a></li>
                <li><a href="${pageContext.request.contextPath}/profile/requests"><fmt:message key="label.request"/></a>
                </li>
                <c:if test="${sessionScope.user.specialty != null}">
                    <li><a href="${pageContext.request.contextPath}/profile/rating"><fmt:message
                            key="label.rating"/></a></li>
                </c:if>
                <li><a href="${pageContext.request.contextPath}/profile/edit"><fmt:message key="label.edit"/></a></li>
            </ul>
        </nav>
        <p></p>
        <header class="major">
            <h2>${sessionScope.user.name} ${sessionScope.user.surname}</h2>
        </header>

        <header class="major">
            <h2>${sessionScope.user.email}</h2>
        </header>

        <header class="major">
            <h2><fmt:message key="label.examMark"/></h2>
        </header>
        <div style="text-align: center;">
            <span>
                <c:set var="sumOfMarks" scope="page" value="${0}"/>
                <c:forEach var="exam" items="${sessionScope.user.examMarks}">
                    <c:set var="sumOfMarks" value="${sumOfMarks + exam.value}"/>
                    <header class="button alt">
                        <p><fmt:message key="label.${exam.key}"/> - ${exam.value} </p>
                    </header>
                </c:forEach>
                <header class="button">
                    <p><fmt:message key="label.sum"/> ${sumOfMarks}</p>
                </header>
            </span>
        </div>
    </section>
</div>


<!-- Footer -->
<!-- Copyright -->
<div id="copyright">
    <ul class="links">
        <li>&copy; Untitled. All rights reserved.</li>
        <li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
    </ul>
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