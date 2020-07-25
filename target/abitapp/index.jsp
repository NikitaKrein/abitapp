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
    <title>ABITAPP</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
<%--    <link rel="stylesheet" href="../view/assets/css/main.css"/>--%>
    <style><%@include file="view/assets/css/main.css"%></style>
</head>

<body class="homepage is-preload">

<div id="page-wrapper">

    <!-- Header -->
    <section id="header">

        <!-- Logo -->
        <h6><a href="${pageContext.request.contextPath}/university?language=by" style="color: red">by</a>
            <a href="${pageContext.request.contextPath}/university?language=ru">ru</a>
            <a href="${pageContext.request.contextPath}/university?language=en">en</a></h6>
        <h1><a href="${pageContext.request.contextPath}/">ABITAPP</a></h1>
        <!-- Nav -->
        <nav id="nav">
            <ul>
                <li class="current"><a href="${pageContext.request.contextPath}/"><fmt:message key="label.home"/> </a>
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
        </nav>

        <!-- Banner -->
        <section id="banner">
            <header>
                <h2>Scientia potentia est</h2>
                <p>Francis Bacon</p>
            </header>
        </section>

    </section>


    <!-- Copyright -->
    <div id="copyright">
        <ul class="links">
            <li>&copy; Untitled. All rights reserved.</li>
            <li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
        </ul>
    </div>

</div>


</body>
</html>