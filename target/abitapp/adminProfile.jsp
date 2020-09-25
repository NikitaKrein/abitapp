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
    <title><fmt:message key="label.profile"/></title>
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
        <h6><a href="${pageContext.request.contextPath}/languageButton?language=ru">ru</a>
            <a>|</a>
            <a href="${pageContext.request.contextPath}/languageButton?language=en">en</a></h6>
        <h1><a href="${pageContext.request.contextPath}/">ABITAPP</a></h1>

        <!-- Nav -->
        <nav id="nav">
            <ul>
                <li><a href="${pageContext.request.contextPath}/"><fmt:message key="label.home"/> </a>
                </li>
                <li><a href="${pageContext.request.contextPath}/university"><fmt:message key="label.university"/></a>
                </li>
                <li><a href="${pageContext.request.contextPath}/admin/profile">${sessionScope.admin.email}</a></li>
                <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message key="label.signOut"/></a>
                </li>
            </ul>
            <p></p>
            <ul>
                <li class="current"><a href="${pageContext.request.contextPath}/admin/profile"><fmt:message
                        key="label.information"/> </a></li>
                <li><a href="${pageContext.request.contextPath}/admin/edit"><fmt:message
                        key="label.edit"/></a></li>
                <c:if test="${sessionScope.admin.university.faculty}">
                    <li><a href="${pageContext.request.contextPath}/admin/requests"><fmt:message
                            key="label.adminRequests"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/rating"><fmt:message
                            key="label.rating"/></a></li>
                </c:if>
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
                        <div class="col-6-medium col-12-small">
                            <section class="box">
                                <header>
                                    <h3>${sessionScope.admin.university.name}</h3>
                                </header>
                                <p>${sessionScope.admin.university.information}</p>
                                <c:if test="${sessionScope.admin.university.faculty}">
                                    <header>
                                        <h4><fmt:message key="label.specialties"/>:
                                            <c:forEach var="specialtyRequests" items="${sessionScope.specialties}"
                                                       varStatus="firstId">
                                                <div>
                                                    <section>
                                                        <input type="checkbox" class="read-more-state"
                                                               id="post${firstId.index}"/>
                                                        <label for="post${firstId.index}" class="read-more-trigger">
                                                            <h2>${specialtyRequests.name}</h2>
                                                        </label>
                                                        <div class="read-more-wrap">
                                                            <div class="read-more-target">
                                                                <div id="intro" class="container">
                                                                    <p>${specialtyRequests.information}</p>
                                                                    <header class="major">
                                                                        <p></p>
                                                                        <h2><fmt:message key="label.exams"/></h2>
                                                                    </header>
                                                                    <div class="row">
                                                                        <c:forEach var="exam"
                                                                                   items="${specialtyRequests.nameOfExams}">
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
                                                                        <h2><fmt:message
                                                                                key="label.admissionPlan"/></h2>
                                                                    </header>
                                                                    <div class="row">
                                                                        <div class="col-3 col-12-medium">
                                                                            <section class="middle">
                                                                                <i class="icon solid featured alt fa-address-card"></i>
                                                                                <header>
                                                                                    <h2>${specialtyRequests.admissionPlanForFree}</h2>
                                                                                </header>
                                                                                <p><i><fmt:message
                                                                                        key="label.formOfTraining1"/></i>
                                                                                </p>
                                                                            </section>
                                                                        </div>
                                                                        <div class="col-3 col-12-medium">
                                                                            <section class="first">
                                                                                <i class="icon solid featured fa-cog"></i>
                                                                                <header>
                                                                                    <h2>${specialtyRequests.admissionPlanForPaid}</h2>
                                                                                </header>
                                                                                <p><i><fmt:message
                                                                                        key="label.formOfTraining2"/></i>
                                                                                </p>
                                                                            </section>
                                                                        </div>
                                                                        <div class="col-3 col-12-medium">
                                                                            <section class="last">
                                                                                <i class="icon solid featured alt2 fa-star"></i>
                                                                                <header>
                                                                                    <h2>${specialtyRequests.admissionPlanForCorrespondenceCourseForFree}</h2>
                                                                                </header>
                                                                                <p><i><fmt:message
                                                                                        key="label.formOfTraining3"/></i>
                                                                                </p>
                                                                            </section>
                                                                        </div>
                                                                        <div class="col-3 col-12-medium">
                                                                            <section class="middle">
                                                                                <i class="icon solid featured alt2 fa-star"></i>
                                                                                <header>
                                                                                    <h2>${specialtyRequests.admissionPlanForCorrespondenceCourseForPaid}</h2>
                                                                                </header>
                                                                                <p><i><fmt:message
                                                                                        key="label.formOfTraining4"/></i>
                                                                                </p>
                                                                            </section>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </section>
                                                </div>
                                            </c:forEach>
                                        </h4>
                                    </header>
                                </c:if>
                                <footer>
                                    <ul class="actions">
                                        <li><a href="${pageContext.request.contextPath}/admin/edit"
                                               class="button aln"><fmt:message key="label.edit"/> </a></li>
                                        <c:if test="${sessionScope.admin.university.faculty}">
                                            <li><a href="${pageContext.request.contextPath}/admin/rating"
                                                   class="button aln"><fmt:message key="label.rating"/> </a></li>
                                        </c:if>
                                    </ul>
                                </footer>
                            </section>
                        </div>
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
<script src="${pageContext.request.contextPath}//assets/js/main.js"></script>

</body>
</html>