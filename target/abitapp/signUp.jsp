<%@ page import="by.epam.krein.abitapp.entity.Exam" %>
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
    <title><fmt:message key="label.signUp"/> </title>
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
        </nav>
    </section>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <c:if test="${requestScope.wrongInformation}">
                        <h1>BAD PASSWORD</h1>
                    </c:if>
                    <form method="post" action="${pageContext.request.contextPath}/signUpButton">
                        <section>
                            <header class="major">
                                <h2><fmt:message key="label.signUp"/> </h2>
                            </header>
                            <div>
                                <label>
                                    <input type="text" required placeholder="<fmt:message key="label.surname"/>" name="surname">
                                </label>
                                <label>
                                    <input type="text" required placeholder="<fmt:message key="label.name"/>" name="name">
                                </label>
                                <label>
                                    <input type="text" required placeholder="<fmt:message key="label.email"/>" name="email">
                                </label>
                                <label>
                                    <input type="password" required placeholder="<fmt:message key="label.password"/>" name="password" minlength="6">
                                </label>
                                <label>
                                    <input type="password" required placeholder="<fmt:message key="label.repeatPassword"/>" name="repeatPassword" minlength="6">
                                </label>
                                <label>
                                    <select name="certificate">
                                        <option value="${exam}" disabled selected ><fmt:message key="label.CERTIFICATE"/>
                                        </option>
                                        <c:set var="exams" value="<%=Exam.values()%>"/>
                                        <c:forEach var="exam" items="${exams}">
                                            <option value="${exam}"><fmt:message key="label.${exam}"/></option>
                                        </c:forEach>
                                        <input type="number" required placeholder="<fmt:message key="label.mark"/>" name="firstMark" class="form-control" min="1" max="100">
                                    </select>
                                </label>
                                <label>
                                    <select name="secondExam">
                                        <option value="" disabled selected><fmt:message key="label.firstSubject"/></option>
                                        <c:set var="exams" value="<%=Exam.values()%>" />
                                        <c:forEach var="exam" items="${exams}">
                                            <option value="${exam}"><fmt:message key="label.${exam}"/></option>
                                        </c:forEach>
                                    </select>
                                    <input type="number" required placeholder="<fmt:message key="label.mark"/>" name="secondMark" class="form-control" min="1" max="100">
                                </label>
                                <label>
                                    <select name="thirdExam">
                                        <option value="" disabled selected><fmt:message key="label.secondSubject"/></option>
                                        <c:set var="exams" value="<%=Exam.values()%>" />
                                        <c:forEach var="exam" items="${exams}">
                                            <option value="${exam}"><fmt:message key="label.${exam}"/></option>
                                        </c:forEach>
                                    </select>
                                    <input type="number" required placeholder="<fmt:message key="label.mark"/>" name="thirdMark" class="form-control" min="1" max="100">
                                </label>
                                <label>
                                    <select name="fourthExam">
                                        <option value="" disabled selected><fmt:message key="label.thirdSubject"/></option>
                                        <c:set var="exams" value="<%=Exam.values()%>" />
                                        <c:forEach var="exam" items="${exams}">
                                            <option value="${exam}"><fmt:message key="label.${exam}"/></option>
                                        </c:forEach>
                                    </select>
                                    <input type="number" required placeholder="<fmt:message key="label.mark"/>" name="fourthMark" class="form-control" min="1" max="100">
                                </label>
                                <label>
                                    <select name="fifthExam">
                                        <option value="" disabled selected><fmt:message key="label.fourthSubject"/></option>
                                        <c:set var="exams" value="<%=Exam.values()%>" />
                                        <c:forEach var="exam" items="${exams}">
                                            <option value="${exam}"><fmt:message key="label.${exam}"/></option>
                                        </c:forEach>
                                    </select>
                                    <input type="number" required placeholder="<fmt:message key="label.mark"/>" name="fifthMark" class="form-control" min="1" max="100">
                                </label>
                                <button class="button">
                                    <fmt:message key="label.signUpButton"/>
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