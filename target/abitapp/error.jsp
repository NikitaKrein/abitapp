<%@ page import="by.epam.krein.abitapp.entity.Exam" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<fmt:setLocale value="${cookie['language'].value}"/>
<fmt:setBundle basename="language"/>


<html>
<head>
    <title><fmt:message key="label.error"/></title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/assets/css/main.css"/>

    <style>
        a {
            text-decoration: none; /* Отменяем подчеркивание у ссылки */
            color: white;
        }
        #header {
            height: 100vh;
            display: flex;
            align-items: center;
            margin-bottom: 0;
        }
        .container {
            display: flex;
            flex-direction: column;
            height: 30vh;
            width: auto;
            justify-content: space-between;
        }
    </style>
</head>
<body>
<section id="header">
    <div class="container">
        <h1><a href="${pageContext.request.contextPath}/">ABITAPP</a></h1>
        <h1><fmt:message key="label.somethingWentWrong"/></h1>
        <button class="button" style="align-content: center">
            <a href="${pageContext.request.contextPath}/" style="color: white"><fmt:message key="label.homePage"/></a>
        </button>
    </div>
</section>
</body>
</html>
