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
    <style>
        .read-more-state,
        .read-more-state1 {
            display: none;
        }

        .read-more-target,
        .read-more-target1 {
            display: none;
            max-height: 0;
            font-size: 0;
            transition: .25s ease;
        }

        .read-more-state:checked ~ .read-more-wrap .read-more-target {
            display: block;
            font-size: inherit;
            max-height: 999em;
        }

        .read-more-state1:checked ~ .read-more-wrap1 .read-more-target1 {
            display: block;
            font-size: inherit;
            max-height: 999em;
        }

        .read-more-trigger,
        .read-more-trigger1 {
            cursor: pointer;
            display: inline-block;
            padding: 0 .5em;
            color: #666;
            font-size: .9em;
            line-height: 2;
            border-radius: .25em;

        }
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
                <li><a href="${pageContext.request.contextPath}/admin/requests"><fmt:message
                        key="label.adminRequests"/></a></li>
                <li class="current"><a href="${pageContext.request.contextPath}/admin/rating"><fmt:message
                        key="label.rating"/></a></li>
                <li><a href="${pageContext.request.contextPath}/admin/password"><fmt:message
                        key="label.updatePassword"/> </a></li>
            </ul>
        </nav>
        <p></p>
        <section id="intro" class="container">
            <header class="major">
                <h2><fmt:message key="label.specialties"/></h2>
            </header>
        </section>
        <c:forEach var="specialtyRequests" items="${sessionScope.specialties}" varStatus="firstId">
            <section id="intro" class="container">
                <div>
                    <input type="checkbox" class="read-more-state" id="post${firstId.index}"/>
                    <header class="major">
                        <label for="post${firstId.index}" class="read-more-trigger">
                            <h2>${specialtyRequests.name}</h2>
                        </label>
                    </header>
                    <div class="read-more-wrap">
                        <div class="read-more-target">
                            <div align="justify">
                                <c:forEach var="rating" items="${requestScope.rating}" varStatus="secondId">
                                    <c:if test="${rating.key == specialtyRequests.id}">
                                        <div>
                                            <input type="checkbox" class="read-more-state1"
                                                   id="post${firstId.index}${secondId.index}"/>
                                            <label for="post${firstId.index}${secondId.index}"
                                                   class="read-more-trigger1">
                                                <h2><fmt:message key="label.formOfTraining${rating.value.key}"/></h2>
                                            </label>
                                            <div class="read-more-wrap1">
                                                <div class="read-more-target1">
                                                    <table border="1">
                                                        <tr>
                                                            <th>#</th>
                                                            <th><fmt:message key="label.name"/></th>
                                                            <th><fmt:message key="label.examMark"/></th>
                                                            <th><fmt:message key="label.edit"/></th>
                                                        </tr>
                                                        <c:forEach var="rate" items="${rating.value.value}"
                                                                   varStatus="status">
                                                            <tr>
                                                                <th>${status.index + 1}</th>
                                                                <th>${rate.key.name} ${rate.key.surname}</th>
                                                                <th>${rate.value}</th>
                                                                <th>
                                                                    <button class="button"><fmt:message
                                                                            key="label.delete"/></button>
                                                                </th>
                                                            </tr>
                                                        </c:forEach>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </c:forEach>
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