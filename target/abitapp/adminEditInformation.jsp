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
    <title><fmt:message key="label.edit"/></title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/assets/css/main.css"/>
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
                <li><a href="${pageContext.request.contextPath}/admin/profile">${sessionScope.admin.email}</a></li>
                <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message key="label.signOut"/></a>
                </li>
            </ul>
            <p></p>
            <ul>
                <li><a href="${pageContext.request.contextPath}/admin/profile"><fmt:message
                        key="label.information"/> </a></li>
                <li class="current"><a href="${pageContext.request.contextPath}/admin/edit"><fmt:message
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

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <form method="post"
                          action="${pageContext.request.contextPath}/editAdminInformationButton?id=${sessionScope.admin.university.id}"
                          accept-charset="UTF-8">
                        <section>
                            <header class="major">
                                <h2><fmt:message key="label.edit"/></h2>
                            </header>
                            <div>
                                <label>
                                    <input type="text" value="${sessionScope.admin.university.name}" name="name">
                                </label>
                                <label>
                                    <input type="text" value="${sessionScope.admin.university.information}"
                                           name="information">
                                </label>
                                <button class="button" value="university" name="button">
                                    <fmt:message key="label.edit"/>
                                </button>
                            </div>
                        </section>
                    </form>
                </div>
            </div>
        </div>
        <section id="main">
            <div class="container">
                <div class="row">
                    <div class="col-12">

                        <c:forEach var="specialtyRequests" items="${sessionScope.specialties}">
                            <section>
                                <div class="col-6-medium col-12-small">
                                    <section class="box">
                                        <header>
                                            <h3>${specialtyRequests.name}</h3>
                                        </header>
                                        <p>${specialtyRequests.information}</p>
                                        <footer>
                                            <ul class="actions">
                                                <li>
                                                    <a href="${pageContext.request.contextPath}/admin/edit/specialty?id=${specialtyRequests.id}"
                                                       class="button aln"><fmt:message key="label.edit"/> </a></li>
                                            </ul>
                                        </footer>
                                    </section>
                                </div>
                            </section>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </section>
    </section>
</div>
</body>
<!-- шобы стрелок не было -->
<style>
    input[type='number'] {
        -moz-appearance: textfield;
    }

    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
</style>
</html>