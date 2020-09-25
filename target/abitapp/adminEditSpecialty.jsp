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
                <li><a href="${pageContext.request.contextPath}/admin/profile">${sessionScope.admin.email}</a></li>
                <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message key="label.signOut"/></a>
                </li>
            </ul>
            <p></p>
            <ul>
                <li><a href="${pageContext.request.contextPath}/admin/profile"><fmt:message
                        key="label.personalInfo"/> </a></li>
                <li class="current"><a href="${pageContext.request.contextPath}/admin/edit"><fmt:message
                        key="label.edit"/></a></li>
                <li><a href="${pageContext.request.contextPath}/admin/requests"><fmt:message
                        key="label.adminRequests"/></a></li>
                <li><a href="${pageContext.request.contextPath}/admin/facultyRating"><fmt:message
                        key="label.rating"/></a></li>
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
                          action="${pageContext.request.contextPath}/editAdminInformationButton?id=${requestScope.specialty.id}"
                          accept-charset="UTF-8">
                        <section id="intro">
                            <header class="major">
                                <h2><fmt:message key="label.edit"/></h2>
                            </header>
                            <div>
                                <label>
                                    <input type="text" value="${requestScope.specialty.name}" name="name">
                                </label>
                                <label>
                                    <input type="text" value="${requestScope.specialty.information}"
                                           name="information">
                                </label>
                                <header class="major">
                                </header>
                                <div class="row">
                                    <div class="col-3 col-10-medium">
                                        <section class="first">
                                            <i class="icon solid featured alt fa-address-card"></i>
                                            <header>
                                                <label>
                                                    <input type="text"
                                                           value="${requestScope.specialty.admissionPlanForFree}"
                                                           name="admissionPlanForFree">
                                                </label>
                                            </header>
                                            <p><i><fmt:message key="label.formOfTraining1"/></i></p>
                                        </section>
                                    </div>
                                    <div class="col-3 col-12-medium">
                                        <section class="first">
                                            <i class="icon solid featured fa-cog"></i>
                                            <header>
                                                <label>
                                                    <input type="text"
                                                           value="${requestScope.specialty.admissionPlanForPaid}"
                                                           name="admissionPlanForPaid">
                                                </label>
                                            </header>
                                            <p><i><fmt:message key="label.formOfTraining2"/></i></p>
                                        </section>
                                    </div>
                                    <div class="col-3 col-12-medium">
                                        <section class="first">
                                            <i class="icon solid featured alt2 fa-star"></i>
                                            <header>
                                                <label>
                                                    <input type="text"
                                                           value="${requestScope.specialty.admissionPlanForCorrespondenceCourseForFree}"
                                                           name="admissionPlanForCorrespondenceCourseForFree">
                                                </label>
                                            </header>
                                            <p><i><fmt:message key="label.formOfTraining3"/></i></p>
                                        </section>
                                    </div>
                                    <div class="col-3 col-10-medium">
                                        <section class="first">
                                            <i class="icon solid featured alt2 fa-star"></i>
                                            <header>
                                                <label>
                                                    <input type="text"
                                                           value="${requestScope.specialty.admissionPlanForCorrespondenceCourseForPaid}"
                                                           name="admissionPlanForCorrespondenceCourseForPaid">
                                                </label>
                                            </header>
                                            <p><i><fmt:message key="label.formOfTraining4"/></i></p>
                                        </section>
                                    </div>
                                </div>
                                <button class="button" value="specialty" name="button">
                                    <fmt:message key="label.edit"/>
                                </button>
                            </div>
                        </section>
                    </form>
                    <p></p>
                    <form method="post" action="${pageContext.request.contextPath}/editSpecialtyExamButton?id=${requestScope.specialty.id}">
                        <section id="intro" class="container">
                            <header class="major">
                                <h2><fmt:message key="label.exams"/> </h2>
                            </header>
                            <div class="row">
                                <c:forEach var="exam" items="${requestScope.specialty.nameOfExams}">
                                    <div class="col-4 col-12-medium">
                                        <section class="first">
                                            <i class="icon solid featured fa-book-open"></i>
                                            <header>
                                                <label>
                                                    <select name="Exam${exam}">
                                                        <option><fmt:message key="label.${exam}"/></option>
                                                        <c:forEach var="newExam" items="${requestScope.exams}">
                                                            <option value="${newExam}"><fmt:message key="label.${newExam}"/></option>
                                                        </c:forEach>
                                                    </select>
                                                </label>
                                            </header>
                                            <button type="submit" name="action" value="update"><fmt:message
                                                    key="label.edit"/></button>
                                            <button type="submit" name="action" value="delete ${exam}"><fmt:message
                                                    key="label.delete"/></button>
                                        </section>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="col-4 col-12-medium" style="align-content: center">
                                <section class="first">
                                    <i class="icon solid featured fa-book-open"></i>
                                    <header>
                                        <label>
                                            <select name="newExam">
                                                <c:forEach var="newExam" items="${requestScope.exams}">
                                                    <option value="${newExam}"><fmt:message key="label.${newExam}"/></option>
                                                </c:forEach>
                                            </select>
                                        </label>
                                    </header>
                                    <button type="submit" name="action" value="add"><fmt:message
                                            key="label.add"/></button>
                                </section>
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
        -moz-appearance: textfield;
    }

    input::-webkit-outer-spin-button,
    input::-webkit-inner-spin-button {
        -webkit-appearance: none;
    }
</style>
</html>