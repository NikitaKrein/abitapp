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
    <title><fmt:message key="label.personalInfo"/> </title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/view/assets/css/main.css"/>

    <style>
        a {
            text-decoration: none; /* Отменяем подчеркивание у ссылки */
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
                <c:if test="${empty sessionScope.user}">
                    <li><a href="${pageContext.request.contextPath}/signIn"><fmt:message key="label.signIn"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/signUp"><fmt:message key="label.signUp"/></a></li>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <li class="current"><a href="${pageContext.request.contextPath}/profile">${sessionScope.user.name}</a></li>
                    <li><a href="${pageContext.request.contextPath}/signOutButton"><fmt:message key="label.signOut"/></a></li>
                </c:if>
            </ul>
            <p></p>
            <ul>
                <li class="current"><a href="${pageContext.request.contextPath}/profile/personalInfo"><fmt:message key="label.personalInfo"/> </a></li>
                <li><a href="${pageContext.request.contextPath}/profile/requests"><fmt:message key="label.request"/></a></li>
                <c:if test="${sessionScope.user.specialty != null}">
                    <li><a href="${pageContext.request.contextPath}/profile/rating"><fmt:message
                            key="label.rating"/></a></li>
                </c:if>
                <li><a href="${pageContext.request.contextPath}/profile/edit"><fmt:message key="label.edit"/></a></li>
            </ul>
        </nav>
        <p></p>
        <header class="major">
            <h2>${sessionScope.user.name} ${sessionScope.user.surname}</h2>
        </header>

        <header class="major">
            <h2>${sessionScope.user.email}</h2>
        </header>

        <header class="major">
            <h2><fmt:message key="label.examMark"/> </h2>
        </header>
        <div style="text-align: center;">
            <span>
                <c:set var="sumOfMarks" scope="page" value="${0}"/>
                <c:forEach var="exam" items="${sessionScope.user.examMarks}">
                    <c:set var="sumOfMarks" value="${sumOfMarks + exam.value}"/>
                    <header class="button alt">
                        <p><fmt:message key="label.${exam.key}"/> - ${exam.value} </p>
                    </header>
                </c:forEach>
                <header class="button">
                    <p><fmt:message key="label.sum"/> ${sumOfMarks}</p>
                </header>
            </span>
        </div>
    </section>

    <!-- Main -->
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-12">

                    <!-- Portfolio -->


                    <header class="major">
                        <h2>
                            <c:choose>
                                <c:when test="${sessionScope.user.requestSpecialty != null}">
                                    Ваша заявка на ${sessionScope.user.requestSpecialty.name} ожидает проверки
                                </c:when>
                                <c:when test="${sessionScope.user.specialty != null}">
                                    Вы подали заявку на ${sessionScope.user.requestSpecialty.name} (одобрена)
                                </c:when>
                                <c:otherwise>Вы пока не подали заявку на поступление!</c:otherwise>
                            </c:choose>
                            <c:choose>
                                <c:when test="${sessionScope.user.formOfTraining == 1}">
                                    (бюджет!)
                                </c:when>
                                <c:when test="${sessionScope.user.formOfTraining == 2}">
                                    (платку!)
                                </c:when>
                                <c:when test="${sessionScope.user.formOfTraining == 3}">
                                    (заочку!)
                                </c:when>
                            </c:choose></h2>
                    </header>


                    <div class="row">
                        <div class="col-4 col-6-medium col-12-small">
                            <section class="box">
                                <a href="#" class="image featured"><img src="images/pic02.jpg" alt=""/></a>
                                <header>
                                    <h3>Ipsum feugiat et dolor</h3>
                                </header>
                                <p>Lorem ipsum dolor sit amet sit veroeros sed amet blandit consequat veroeros lorem
                                    blandit adipiscing et feugiat phasellus tempus dolore ipsum lorem dolore.</p>
                                <footer>
                                    <ul class="actions">
                                        <li><a href="#" class="button alt">Find out more</a></li>
                                    </ul>
                                </footer>
                            </section>
                        </div>
                        <div class="col-4 col-6-medium col-12-small">
                            <section class="box">
                                <a href="#" class="image featured"><img src="images/pic03.jpg" alt=""/></a>
                                <header>
                                    <h3>Sed etiam lorem nulla</h3>
                                </header>
                                <p>Lorem ipsum dolor sit amet sit veroeros sed amet blandit consequat veroeros lorem
                                    blandit adipiscing et feugiat phasellus tempus dolore ipsum lorem dolore.</p>
                                <footer>
                                    <ul class="actions">
                                        <li><a href="#" class="button alt">Find out more</a></li>
                                    </ul>
                                </footer>
                            </section>
                        </div>
                        <div class="col-4 col-6-medium col-12-small">
                            <section class="box">
                                <a href="#" class="image featured"><img src="images/pic04.jpg" alt=""/></a>
                                <header>
                                    <h3>Consequat et tempus</h3>
                                </header>
                                <p>Lorem ipsum dolor sit amet sit veroeros sed amet blandit consequat veroeros lorem
                                    blandit adipiscing et feugiat phasellus tempus dolore ipsum lorem dolore.</p>
                                <footer>
                                    <ul class="actions">
                                        <li><a href="#" class="button alt">Find out more</a></li>
                                    </ul>
                                </footer>
                            </section>
                        </div>
                        <div class="col-4 col-6-medium col-12-small">
                            <section class="box">
                                <a href="#" class="image featured"><img src="images/pic05.jpg" alt=""/></a>
                                <header>
                                    <h3>Blandit sed adipiscing</h3>
                                </header>
                                <p>Lorem ipsum dolor sit amet sit veroeros sed amet blandit consequat veroeros lorem
                                    blandit adipiscing et feugiat phasellus tempus dolore ipsum lorem dolore.</p>
                                <footer>
                                    <ul class="actions">
                                        <li><a href="#" class="button alt">Find out more</a></li>
                                    </ul>
                                </footer>
                            </section>
                        </div>
                        <div class="col-4 col-6-medium col-12-small">
                            <section class="box">
                                <a href="#" class="image featured"><img src="images/pic06.jpg" alt=""/></a>
                                <header>
                                    <h3>Etiam nisl consequat</h3>
                                </header>
                                <p>Lorem ipsum dolor sit amet sit veroeros sed amet blandit consequat veroeros lorem
                                    blandit adipiscing et feugiat phasellus tempus dolore ipsum lorem dolore.</p>
                                <footer>
                                    <ul class="actions">
                                        <li><a href="#" class="button alt">Find out more</a></li>
                                    </ul>
                                </footer>
                            </section>
                        </div>
                        <div class="col-4 col-6-medium col-12-small">
                            <section class="box">
                                <a href="#" class="image featured"><img src="images/pic07.jpg" alt=""/></a>
                                <header>
                                    <h3>Dolore nisl feugiat</h3>
                                </header>
                                <p>Lorem ipsum dolor sit amet sit veroeros sed amet blandit consequat veroeros lorem
                                    blandit adipiscing et feugiat phasellus tempus dolore ipsum lorem dolore.</p>
                                <footer>
                                    <ul class="actions">
                                        <li><a href="#" class="button alt">Find out more</a></li>
                                    </ul>
                                </footer>
                            </section>
                        </div>
                    </div>
    </section>

</div>
<div class="col-12">

    <!-- Blog -->
    <section>
        <header class="major">
            <h2>The Blog</h2>
        </header>
        <div class="row">
            <div class="col-6 col-12-small">
                <section class="box">
                    <a href="#" class="image featured"><img src="images/pic08.jpg" alt=""/></a>
                    <header>
                        <h3>Magna tempus consequat</h3>
                        <p>Posted 45 minutes ago</p>
                    </header>
                    <p>Lorem ipsum dolor sit amet sit veroeros sed et blandit consequat sed veroeros
                        lorem et blandit adipiscing feugiat phasellus tempus hendrerit, tortor vitae
                        mattis tempor, sapien sem feugiat sapien, id suscipit magna felis nec elit.
                        Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos
                        lorem ipsum dolor sit amet.</p>
                    <footer>
                        <ul class="actions">
                            <li><a href="#" class="button icon solid fa-file-alt">Continue Reading</a>
                            </li>
                            <li><a href="#" class="button alt icon solid fa-comment">33 comments</a>
                            </li>
                        </ul>
                    </footer>
                </section>
            </div>
            <div class="col-6 col-12-small">
                <section class="box">
                    <a href="#" class="image featured"><img src="images/pic09.jpg" alt=""/></a>
                    <header>
                        <h3>Aptent veroeros aliquam</h3>
                        <p>Posted 45 minutes ago</p>
                    </header>
                    <p>Lorem ipsum dolor sit amet sit veroeros sed et blandit consequat sed veroeros
                        lorem et blandit adipiscing feugiat phasellus tempus hendrerit, tortor vitae
                        mattis tempor, sapien sem feugiat sapien, id suscipit magna felis nec elit.
                        Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos
                        lorem ipsum dolor sit amet.</p>
                    <footer>
                        <ul class="actions">
                            <li><a href="#" class="button icon solid fa-file-alt">Continue Reading</a>
                            </li>
                            <li><a href="#" class="button alt icon solid fa-comment">33 comments</a>
                            </li>
                        </ul>
                    </footer>
                </section>
            </div>
        </div>
    </section>

</div>
</div>
</div>
</section>


<!-- Footer -->
<section id="footer">
    <div class="container">
        <div class="row">
            <div class="col-8 col-12-medium">
                <section>
                    <header>
                        <h2>Blandit nisl adipiscing</h2>
                    </header>
                    <ul class="dates">
                        <li>
                            <span class="date">Jan <strong>27</strong></span>
                            <h3><a href="#">Lorem dolor sit amet veroeros</a></h3>
                            <p>Ipsum dolor sit amet veroeros consequat blandit ipsum phasellus lorem consequat
                                etiam.</p>
                        </li>
                        <li>
                            <span class="date">Jan <strong>23</strong></span>
                            <h3><a href="#">Ipsum sed blandit nisl consequat</a></h3>
                            <p>Blandit phasellus lorem ipsum dolor tempor sapien tortor hendrerit adipiscing feugiat
                                lorem.</p>
                        </li>
                        <li>
                            <span class="date">Jan <strong>15</strong></span>
                            <h3><a href="#">Magna tempus lorem feugiat</a></h3>
                            <p>Dolore consequat sed phasellus lorem sed etiam nullam dolor etiam sed amet sit
                                consequat.</p>
                        </li>
                        <li>
                            <span class="date">Jan <strong>12</strong></span>
                            <h3><a href="#">Dolore tempus ipsum feugiat nulla</a></h3>
                            <p>Feugiat lorem dolor sed nullam tempus lorem ipsum dolor sit amet nullam
                                consequat.</p>
                        </li>
                        <li>
                            <span class="date">Jan <strong>10</strong></span>
                            <h3><a href="#">Blandit tempus aliquam?</a></h3>
                            <p>Feugiat sed tempus blandit tempus adipiscing nisl lorem ipsum dolor sit amet
                                dolore.</p>
                        </li>
                    </ul>
                </section>
            </div>
            <div class="col-4 col-12-medium">
                <section>
                    <header>
                        <h2>What's this all about?</h2>
                    </header>
                    <a href="#" class="image featured"><img src="images/pic10.jpg" alt=""/></a>
                    <p>
                        This is <strong>Dopetrope</strong> a free, fully responsive HTML5 site template by
                        <a href="http://twitter.com/ajlkn">AJ</a> for <a href="http://html5up.net/">HTML5 UP</a>
                        It's released for free under
                        the <a href="http://html5up.net/license/">Creative Commons Attribution</a> license so feel
                        free to use it for any personal or commercial project &ndash; just don't forget to credit
                        us!
                    </p>
                    <footer>
                        <ul class="actions">
                            <li><a href="#" class="button">Find out more</a></li>
                        </ul>
                    </footer>
                </section>
            </div>
            <div class="col-4 col-6-medium col-12-small">
                <section>
                    <header>
                        <h2>Tempus consequat</h2>
                    </header>
                    <ul class="divided">
                        <li><a href="#">Lorem ipsum dolor sit amet sit veroeros</a></li>
                        <li><a href="#">Sed et blandit consequat sed tlorem blandit</a></li>
                        <li><a href="#">Adipiscing feugiat phasellus sed tempus</a></li>
                        <li><a href="#">Hendrerit tortor vitae mattis tempor sapien</a></li>
                        <li><a href="#">Sem feugiat sapien id suscipit magna felis nec</a></li>
                        <li><a href="#">Elit class aptent taciti sociosqu ad litora</a></li>
                    </ul>
                </section>
            </div>
            <div class="col-4 col-6-medium col-12-small">
                <section>
                    <header>
                        <h2>Ipsum et phasellus</h2>
                    </header>
                    <ul class="divided">
                        <li><a href="#">Lorem ipsum dolor sit amet sit veroeros</a></li>
                        <li><a href="#">Sed et blandit consequat sed tlorem blandit</a></li>
                        <li><a href="#">Adipiscing feugiat phasellus sed tempus</a></li>
                        <li><a href="#">Hendrerit tortor vitae mattis tempor sapien</a></li>
                        <li><a href="#">Sem feugiat sapien id suscipit magna felis nec</a></li>
                        <li><a href="#">Elit class aptent taciti sociosqu ad litora</a></li>
                    </ul>
                </section>
            </div>
            <div class="col-4 col-12-medium">
                <section>
                    <header>
                        <h2>Vitae tempor lorem</h2>
                    </header>
                    <ul class="social">
                        <li><a class="icon brands fa-facebook-f" href="#"><span class="label">Facebook</span></a>
                        </li>
                        <li><a class="icon brands fa-twitter" href="#"><span class="label">Twitter</span></a></li>
                        <li><a class="icon brands fa-dribbble" href="#"><span class="label">Dribbble</span></a></li>
                        <li><a class="icon brands fa-tumblr" href="#"><span class="label">Tumblr</span></a></li>
                        <li><a class="icon brands fa-linkedin-in" href="#"><span class="label">LinkedIn</span></a>
                        </li>
                    </ul>
                    <ul class="contact">
                        <li>
                            <h3>Address</h3>
                            <p>
                                Untitled Incorporated<br/>
                                1234 Somewhere Road Suite<br/>
                                Nashville, TN 00000-0000
                            </p>
                        </li>
                        <li>
                            <h3>Mail</h3>
                            <p><a href="#">someone@untitled.tld</a></p>
                        </li>
                        <li>
                            <h3>Phone</h3>
                            <p>(800) 000-0000</p>
                        </li>
                    </ul>
                </section>
            </div>
            <div class="col-12">

                <!-- Copyright -->
                <div id="copyright">
                    <ul class="links">
                        <li>&copy; Untitled. All rights reserved.</li>
                        <li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
                    </ul>
                </div>

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