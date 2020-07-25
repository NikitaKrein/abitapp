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

<html lang="${cookie['language'].value}">
<head>
    <title><fmt:message key="label.signIn"/> </title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
<%--    <link rel="stylesheet" href="view/assets/css/main.css"/>--%>
    <style><%@include file="view/assets/css/main.css"%></style>
</head>
<body class="homepage is-preload">
<div id="page-wrapper">
    <!-- Header -->
    <section id="header">

        <!-- Logo -->
        <h1><a href="${pageContext.request.contextPath}/">ABITAPP</a></h1>

        <!-- Nav -->
        <!-- Nav -->
        <nav id="nav">
            <ul>
                <li><a href="${pageContext.request.contextPath}/"><fmt:message key="label.home"/> </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/university"><fmt:message key="label.university"/></a>
                </li>
                <c:if test="${empty sessionScope.user}">
                    <li class="current"><a href="${pageContext.request.contextPath}/signIn"><fmt:message key="label.signIn"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/signUp"><fmt:message key="label.signUp"/></a></li>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/profile">${sessionScope.user.name}</a></li>
                    <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message key="label.signOut"/></a></li>
                </c:if>
            </ul>
        </nav>
    </section>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <form method="post" action="${pageContext.request.contextPath}/signInButton">
                        <section>
                            <header class="major">
                                <h2><fmt:message key="label.signIn"/> </h2>
                            </header>
                            <div>
                                <label>
                                    <input type="email" name="email" required placeholder="<fmt:message key="label.email"/>">
                                </label>
                                <label>
                                    <input type="password" name="password" required placeholder="<fmt:message key="label.password"/>">
                                </label>
                                <button class="button">
                                    <fmt:message key="label.signInButton"/>
                                </button>
                            </div>
                        </section>
                    </form>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>