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
    <title>Faculty</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/assets/css/main.css"/>--%>
    <style><%@include file="view/assets/css/main.css"%></style>
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
        <h1><a href="${pageContext.request.contextPath}/">ABITAPP</a></h1>

        <!-- Nav -->
        <nav id="nav">
            <ul>
                <li><a href="${pageContext.request.contextPath}/"><fmt:message key="label.home"/> </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/university"><fmt:message key="label.university"/></a>
                </li>
                <li class="current"><a
                        href="${pageContext.request.contextPath}/faculty/${requestScope.faculty.id}">${requestScope.faculty.name}</a>
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

        <p></p>

        <section id="main">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <section class="box">
                            <p>${requestScope.faculty.information}</p>
                        </section>
                    </div>
                </div>
            </div>
        </section>
        <!-- Intro -->
        <section id="intro" class="container">
            <header class="major">
                <h2><fmt:message key="label.specialties"/></h2>
            </header>
        </section>
        <c:forEach var="specialtyRequests" items="${requestScope.specialties}" varStatus="firstId">
            <section id="intro" class="container">
                <div>
                    <input type="checkbox" class="read-more-state" id="post${firstId.index}"/>
                    <header class="major">
                        <label for="post${firstId.index}" class="read-more-trigger">
                            <h2>${specialtyRequests.key.name}</h2>
                        </label>
                    </header>
                    <div class="read-more-wrap">
                        <div class="read-more-target">
                            <header class="major">
                                <h2><fmt:message key="label.exams"/></h2>
                            </header>
                            <div class="row">
                                <c:forEach var="exam" items="${specialtyRequests.key.nameOfExams}">
                                    <div class="col-4 col-12-medium">
                                        <section class="first">
                                            <i class="icon solid featured fa-book-open"></i>
                                            <header>
                                                <h2>${exam}</h2>
                                            </header>
                                        </section>
                                    </div>
                                </c:forEach>
                            </div>
                            <header class="major">
                                <h2><fmt:message key="label.admissionPlan"/></h2>
                            </header>
                            <div class="row">
                                <div class="col-3 col-12-medium">
                                    <section class="middle">
                                        <i class="icon solid featured alt fa-address-card"></i>
                                        <header>
                                            <h2>${specialtyRequests.key.admissionPlanForFree}</h2>
                                        </header>
                                        <p><i><fmt:message key="label.formOfTraining1"/></i></p>
                                    </section>
                                </div>
                                <div class="col-3 col-12-medium">
                                    <section class="first">
                                        <i class="icon solid featured fa-cog"></i>
                                        <header>
                                            <h2>${specialtyRequests.key.admissionPlanForPaid}</h2>
                                        </header>
                                        <p><i><fmt:message key="label.formOfTraining2"/></i></p>
                                    </section>
                                </div>
                                <div class="col-3 col-12-medium">
                                    <section class="last">
                                        <i class="icon solid featured alt2 fa-star"></i>
                                        <header>
                                            <h2>${specialtyRequests.key.admissionPlanForCorrespondenceCourseForFree}</h2>
                                        </header>
                                        <p><i><fmt:message key="label.formOfTraining3"/></i></p>
                                    </section>
                                </div>
                                <div class="col-3 col-12-medium">
                                    <section class="middle">
                                        <i class="icon solid featured alt2 fa-star"></i>
                                        <header>
                                            <h2>${specialtyRequests.key.admissionPlanForCorrespondenceCourseForPaid}</h2>
                                        </header>
                                        <p><i><fmt:message key="label.formOfTraining4"/></i></p>
                                    </section>
                                </div>
                            </div>
                            <footer>
                                <ul class="actions">
                                    <c:choose>
                                        <c:when test="${empty sessionScope.user}">
                                            <li><a href="${pageContext.request.contextPath}/signIn"
                                                   class="button alt large"><fmt:message key="label.signInButton"/> </a>
                                            </li>
                                        </c:when>
                                        <c:when test="${not empty sessionScope.user && specialtyRequests.value == true}">
                                            <form action="${pageContext.request.contextPath}/submitForFacultyButton?id=${specialtyRequests.key.id}"
                                                  method="post">
                                                <label>
                                                    <select name="formOfTraining">
                                                        <option value="1"><fmt:message
                                                                key="label.formOfTraining1"/></option>
                                                        <option value="2"><fmt:message
                                                                key="label.formOfTraining2"/></option>
                                                        <option value="3"><fmt:message
                                                                key="label.formOfTraining3"/></option>
                                                        <option value="4"><fmt:message
                                                                key="label.formOfTraining4"/></option>
                                                    </select>
                                                </label>
                                                <button class="button">
                                                    <fmt:message key="label.submitDocuments"/>
                                                </button>
                                            </form>
                                        </c:when>
                                        <c:when test="${specialtyRequests.value == false}">
                                            <li>
                                                <a href="${pageContext.request.contextPath}/university"
                                                   class="button alt large"><fmt:message key="label.youCanNot"/>
                                                    <fmt:message
                                                            key="label.submitDocuments"/></a></li>
                                        </c:when>
                                    </c:choose>
                                </ul>
                            </footer>

                            <div align="justify">
                                <button><fmt:message key="label.rating"/> </button>
                                <c:forEach var="rating" items="${requestScope.rating}" varStatus="secondId">
                                    <c:if test="${rating.key == specialtyRequests.key.id}">
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
                                                        </tr>
                                                        <c:forEach var="rate" items="${rating.value.value}"
                                                                   varStatus="status">
                                                            <tr>
                                                                <th>${status.index + 1}</th>
                                                                <c:if test="${rate.key.id == sessionScope.user.id}">
                                                                    <th>${rate.key.name} ${rate.key.surname}</th>
                                                                </c:if>
                                                                <c:if test="${rate.key.id != sessionScope.user.id}">
                                                                    <th>***</th>
                                                                </c:if>
                                                                <th>${rate.value}</th>
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

<script src="${pageContext.request.contextPath}/view/assets/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/view/assets/js/jquery.dropotron.min.js"></script>
<script src="${pageContext.request.contextPath}/view/assets/js/browser.min.js"></script>
<script src="${pageContext.request.contextPath}/view/assets/js/breakpoints.min.js"></script>
<script src="${pageContext.request.contextPath}/view/assets/js/util.js"></script>
<script src="${pageContext.request.contextPath}/view/assets/js/main.js"></script>

</body>
</html>