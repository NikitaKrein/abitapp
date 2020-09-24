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
    <title><fmt:message key="label.edit"/> </title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/assets/css/main.css"/>--%>
    <style><%@include file="view/assets/css/main.css"%></style>
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
                <c:if test="${empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/signIn"><fmt:message key="label.signIn"/></a></li>
                    <li class="current"><a href="${pageContext.request.contextPath}/signUp"><fmt:message key="label.signUp"/></a></li>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/profile">${sessionScope.user.name}</a></li>
                    <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message key="label.signOut"/></a></li>
                </c:if>
            </ul>
            <p></p>
            <ul>
                <li><a href="${pageContext.request.contextPath}/profile/personalInfo"><fmt:message key="label.personalInfo"/> </a></li>
                <li><a href="${pageContext.request.contextPath}/profile/requests"><fmt:message key="label.request"/></a></li>
                <c:if test="${sessionScope.user.specialty != null}">
                    <li><a href="${pageContext.request.contextPath}/profile/rating"><fmt:message
                            key="label.rating"/></a></li>
                </c:if>
                <li class="current"><a href="${pageContext.request.contextPath}/profile/edit"><fmt:message key="label.edit"/></a></li>
            </ul>
        </nav>
    </section>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <form method="post" action="${pageContext.request.contextPath}/editProfileButton" accept-charset="UTF-8">
                        <section>
                            <header class="major">
                                <h2><fmt:message key="label.edit"/> </h2>
                            </header>
                            <div>
                                <label>
                                    <input type="password" placeholder="*" name="password" minlength="6">
                                </label>
                                <label>
                                    <input type="password" placeholder="<fmt:message key="label.repeatPassword"/>" name="repeatPassword">
                                </label>

                                <button class="button">
                                    <fmt:message key="label.edit"/>
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
<!-- шобы стрелок не было -->
<style>
    input[type='number'] {
        -moz-appearance:textfield;
    }

    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
</style>
</html>