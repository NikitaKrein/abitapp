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
    <title><fmt:message key="label.university"/> </title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <style><%@include file="view/assets/css/main.css"%></style>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/view/assets/css/main.css"/>--%>
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
                <li class="current"><a href="${pageContext.request.contextPath}/university"><fmt:message key="label.university"/></a>
                </li>
                <c:choose>
                    <c:when test="${not empty sessionScope.user}">
                        <li><a href="${pageContext.request.contextPath}/profile">${sessionScope.user.name}</a></li>
                        <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message key="label.signOut"/></a></li>
                    </c:when>
                    <c:when test="${not empty sessionScope.admin}">
                        <li><a href="${pageContext.request.contextPath}/admin/profile">${sessionScope.admin.email}</a></li>
                        <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message key="label.signOut"/></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/signIn"><fmt:message key="label.signIn"/></a></li>
                        <li><a href="${pageContext.request.contextPath}/signUp"><fmt:message key="label.signUp"/></a></li>
                    </c:otherwise>
                </c:choose>
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
                        <header class="major">
                            <h2><fmt:message key="label.universityCatalog"/></h2>
                        </header>
                        <div class="col-6-medium col-12-small">
                            <c:forEach var="university" items="${requestScope.universities}">
                                <section class="box">
                                    <header>
                                        <h3>${university.name}</h3>
                                    </header>
                                    <p>${university.information}</p>
                                    <footer>
                                        <ul class="actions">
                                            <c:choose>
                                                <c:when test="${university.faculty}">
                                                    <li>
                                                        <a href="${pageContext.request.contextPath}/faculty/${university.id}"
                                                           class="button aln">${university.name}</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${pageContext.request.contextPath}/university/${university.id}"
                                                       class="button aln">${university.name}</a></li>
                                                </c:otherwise>
                                            </c:choose>
                                        </ul>
                                    </footer>
                                </section>
                            </c:forEach>
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
<script src="${pageContext.request.contextPath}/view/assets/js/main.js"></script>

</body>
</html>