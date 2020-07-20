package by.epam.krein.abitapp;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.epam.krein.abitapp.dao.AdminDAO;
import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.UserDAO;
import by.epam.krein.abitapp.entity.*;
import by.epam.krein.abitapp.service.impl.SecurityServiceImpl;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class runner {
    public static void main(String[] args) throws SQLException {

        String password = "hash";
        String script = BCrypt.withDefaults().hashToString(12,password.toCharArray());
        System.out.println(script);
//        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), script);
//        System.out.println(result.verified);
        SecurityServiceImpl securityService = new SecurityServiceImpl();
        char[] parol = securityService.createPassword(password);
        System.out.println(parol);
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), parol);
        System.out.println(result.verified);
//        DAOFactory daoFactory = DAOFactory.getInstance();
//        UserDAO userDAO = daoFactory.getUserDAO();
//        AdminDAO adminDAO = daoFactory.getAdminDAO();
//        Admin admin = adminDAO.findByEmail("nk@g.c");
        //System.out.println(admin.getId() + " " + admin.getEmail() + " " + admin.getPassword() + " " + admin.getFacultyAdminId() + " " + admin.getUniversity().getId() + " " + admin.getUniversity().getName() + admin.getUniversity().isSpecialtyFlag());
        /*Map<Exam, Integer> mp = new HashMap<>();
        mp.put(Exam.Physics, 22);
        mp.put(Exam.Math, 33);
        User user = new User();
        //user.setId(6);
        user.setName("Nik");
        user.setSurname("Krein");
        user.setEmail("Niiikkrein1@gmail.com");
        user.setPassword("hash");
        user.setExamMarks(mp);
        */

       /*
       <c:forEach var="rating" items="${requestScope.rating}">
                    <c:if test="${rating.key.key == specialty.key.id}">
                        <h2><fmt:message key="label.formOfTraining${rating.key.value}"/></h2>
                        <table border="1">
                            <tr>
                                <th>#</th>
                                <th><fmt:message key="label.name"/></th>
                                <th><fmt:message key="label.examMark"/></th>
                            </tr>
                            <c:forEach var="personRating" items="${rating.value}" varStatus="status">

                            </c:forEach>
                        </table>
                    </c:if>
                </c:forEach>
        */


        /*SQLUniversityDAO sqlUniversityDAO = new SQLUniversityDAO();
        SQLSpecialtyDAO sqlFacultyDAO = new SQLSpecialtyDAO();
        List<Exam> exams = new ArrayList<>();
        exams.add(Exam.RussianLanguage);
        exams.add(Exam.Physics);
        University university = new University();
        university.setName("RFCT");
        university.setUniversity(sqlUniversityDAO.findParent(2));
        sqlUniversityDAO.create(university);
        Specialty faculty = new Specialty();
        faculty.setName("PI");
        faculty.setUniversity(sqlUniversityDAO.findParent(32));
        faculty.setAdmissionPlanForFree(4);
        faculty.setAdmissionPlanForCorrespondenceCourse(15);
        faculty.setAdmissionPlanForPaid(55);
        faculty.setNameOfExams(exams);
        sqlFacultyDAO.createFacultyWithExam(faculty);

        */

        //System.out.println(sqlUserDAO.findUserByEmail("niikkrein1@gmail.com").next());
        //for (User user : sqlUserDAO.findAll()){
        //    System.out.println(user);
        //}
        //User user = (User) sqlUserDAO.findUser("Niiikkrein1@gmail.com","hash");

        /*

                    <c:set var="canSubmit" scope="page" value="${true}"/>
                    <c:forEach var="facultyExam" items="${requestScope.faculty.nameOfExams}">
                        <c:set var="localCanSubmit" scope="page" value="${false}"/>
                        <c:forEach var="userExam" items="${sessionScope.user.examMarks}">
                            <c:if test="${facultyExam == userExam.key}">
                                <c:set var="localCanSubmit" value="${true}"/>
                            </c:if>
                        </c:forEach>
                        <c:if test="${localCanSubmit == false}">
                            <c:set var="canSubmit" value="${false}"/>
                        </c:if>
                    </c:forEach>

                    <servlet>
    <servlet-name>languageButton</servlet-name>
    <servlet-class>by.epam.krein.abitapp.controller.ControllerServlet</servlet-class>
    <init-param>
      <param-name>command</param-name>
      <param-value>language_button</param-value>
    </init-param>
  </servlet>
  <servlet-mapping>
    <servlet-name>languageButton</servlet-name>
    <url-pattern>/languageButton</url-pattern>
  </servlet-mapping>

         */

        /*
         <c:forEach var="specialty" items="${sessionScope.specialties}">
                                    <section id="intro" class="container">
                                        <header class="major">
                                            <h2>${specialty.key.name}</h2>
                                        </header>
                                        <div class="row">
                                            <c:forEach var="exam" items="${specialty.key.nameOfExams}">
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
                                                        <h2>${specialty.key.admissionPlanForFree}</h2>
                                                    </header>
                                                    <p><i><fmt:message key="label.formOfTraining1"/></i></p>
                                                </section>
                                            </div>
                                            <div class="col-3 col-12-medium">
                                                <section class="first">
                                                    <i class="icon solid featured fa-cog"></i>
                                                    <header>
                                                        <h2>${specialty.key.admissionPlanForPaid}</h2>
                                                    </header>
                                                    <p><i><fmt:message key="label.formOfTraining2"/></i></p>
                                                </section>
                                            </div>
                                            <div class="col-3 col-12-medium">
                                                <section class="last">
                                                    <i class="icon solid featured alt2 fa-star"></i>
                                                    <header>
                                                        <h2>${specialty.key.admissionPlanForCorrespondenceCourseForFree}</h2>
                                                    </header>
                                                    <p><i><fmt:message key="label.formOfTraining3"/></i></p>
                                                </section>
                                            </div>
                                            <div class="col-3 col-12-medium">
                                                <section class="middle">
                                                    <i class="icon solid featured alt2 fa-star"></i>
                                                    <header>
                                                        <h2>${specialty.key.admissionPlanForCorrespondenceCourseForFree}</h2>
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
                                                    <c:when test="${not empty sessionScope.user && specialty.value == true}">
                                                        <form action="${pageContext.request.contextPath}/submitForFacultyButton?id=${specialty.key.id}"
                                                              method="post">
                                                            <label>
                                                                <select name="formOfTraining">
                                                                    <option value="1"><fmt:message key="label.formOfTraining1"/></option>
                                                                    <option value="2"><fmt:message key="label.formOfTraining2"/></option>
                                                                    <option value="3"><fmt:message key="label.formOfTraining3"/></option>
                                                                    <option value="4"><fmt:message key="label.formOfTraining4"/></option>
                                                                </select>
                                                            </label>
                                                            <button class="button">
                                                                <fmt:message key="label.submitDocuments"/>
                                                            </button>
                                                        </form>
                                                    </c:when>
                                                    <c:when test="${specialty.value == false}">
                                                        <li>
                                                            <a href="${pageContext.request.contextPath}/university"
                                                               class="button alt large"><fmt:message key="label.youCanNot"/> <fmt:message
                                                                    key="label.submitDocuments"/></a></li>
                                                    </c:when>
                                                </c:choose>
                                            </ul>
                                        </footer>

                                        <c:forEach var="rating" items="${requestScope.rating}">
                                            <c:if test="${rating.key == specialty.key.id}">
                                                <h2><fmt:message key="label.formOfTraining${rating.value.key}"/></h2>
                                                <table border="1">
                                                    <tr>
                                                        <th>#</th>
                                                        <th><fmt:message key="label.name"/></th>
                                                        <th><fmt:message key="label.examMark"/></th>
                                                    </tr>
                                                    <c:forEach var="rate" items="${rating.value.value}" varStatus="status">
                                                        <tr>
                                                            <th>${status.index + 1}</th>
                                                            <th>***</th>
                                                            <th>${rate.value}</th>
                                                        </tr>
                                                    </c:forEach>
                                                </table>
                                            </c:if>
                                        </c:forEach>
                                    </section>
                                </c:forEach>
                                <c:if test="${not empty sessionScope.specialties}">
                                    <section id="header">
                                        <section id="intro" class="container">
                                            <header class="major">
                                                <h2>План Набора на дневное</h2>
                                            </header>
                                            <div class="row">
                                                <div class="col-6 col-12-medium">
                                                    <section class="middle">
                                                        <i class="icon solid featured alt fa-address-card"></i>
                                                        <header>
                                                            <label>
                                                                <input type="number" required
                                                                       value="${sessionScope.specialty.admissionPlanForFree}"
                                                                       name="admissionPlanForFree">
                                                            </label>
                                                        </header>
                                                        <p>На БЮДЖЕТ</p>
                                                    </section>
                                                </div>
                                                <div class="col-6 col-12-medium">
                                                    <section class="first">
                                                        <i class="icon solid featured fa-cog"></i>
                                                        <header>
                                                            <label>
                                                                <input type="number" required
                                                                       value="${sessionScope.specialty.admissionPlanForPaid}"
                                                                       name="admissionPlanForPaid">
                                                            </label>
                                                        </header>
                                                        <p>На платку</p>
                                                    </section>
                                                </div>
                                            </div>
                                            <header class="major">
                                                <h2>План Набора на заочку</h2>
                                            </header>
                                            <div class="row">
                                                <div class="col-6 col-12-medium">
                                                    <section class="last">
                                                        <i class="icon solid featured alt2 fa-star"></i>
                                                        <header>
                                                            <label>
                                                                <input type="number" required
                                                                       value="${sessionScope.specialty.admissionPlanForCorrespondenceCourseForFree}"
                                                                       name="admissionPlanForCorrespondenceCourseForFree">
                                                            </label>
                                                        </header>
                                                        <p>На беспл</p>
                                                    </section>
                                                </div>
                                                <div class="col-6 col-12-medium">
                                                    <section class="middle">
                                                        <i class="icon solid featured alt2 fa-star"></i>
                                                        <header>
                                                            <label>
                                                                <input type="number" required
                                                                       value="${sessionScope.specialty.admissionPlanForCorrespondenceCourseForPaid}"
                                                                       name="admissionPlanForCorrespondenceCourseForPaid">
                                                            </label>
                                                        </header>
                                                        <p>На заочку</p>
                                                    </section>
                                                </div>
                                            </div>
                                        </section>
                                    </section>
                                </c:if>
                                <button class="button">
                                    <fmt:message key="label.edit"/>
                                </button>
         */

        /*
        <form method="post" action="${pageContext.request.contextPath}/editFacultyExamButton">
                <c:if test="${sessionScope.admin.university.specialtyFlag}">
                    <section id="intro" class="container">
                        <header class="major">
                            <h2>Экзы</h2>
                        </header>
                        <div class="row">
                            <c:forEach var="exam" items="${sessionScope.specialty.nameOfExams}">
                                <div class="col-4 col-12-medium">
                                    <section class="first">
                                        <i class="icon solid featured fa-book-open"></i>
                                        <header>
                                            <label>
                                                <select name="Exam${exam}">
                                                    <option>${exam}</option>
                                                    <c:forEach var="newExam" items="${requestScope.exams}">
                                                        <option value="${newExam}">${newExam}</option>
                                                    </c:forEach>
                                                </select>
                                            </label>
                                        </header>
                                        <button type="submit" name="action" value="update"><fmt:message key="label.edit"/></button>
                                        <button type="submit" name="action" value="delete ${exam}"> <fmt:message key="label.delete"/> </button>
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
                                                <option value="${newExam}">${newExam}</option>
                                            </c:forEach>
                                        </select>
                                    </label>
                                </header>
                                <button type="submit" name="action" value="add"><fmt:message key="label.add"/></button>
                            </section>
                        </div>
                    </section>
                </c:if>
            </form>
         */
        /*
        <div class="row">
                <div class="col-6-medium col-12-medium">
                    <section class="box">


                    </section>
                </div>
            </div>
        * */

        /*
        <header>
                                        <h3>${specialtyRequests.key.surname} ${specialtyRequests.key.name} ${specialtyRequests.value}</h3>
                                    </header>
                                    <footer>
                                        <button type="submit" name="action"
                                                value="accept ${specialtyRequests.key.id} ${specialtyRequests.key.requestSpecialty.id}">
                                            <fmt:message key="label.accept"/></button>
                                        <button type="submit" name="action"
                                                value="reject ${specialtyRequests.key.id} ${specialtyRequests.key.requestSpecialty.id}">
                                            <fmt:message key="label.reject"/></button>
                                    </footer>
                                    <label>
                                        <input type="text" placeholder="<fmt:message key="label.adminMessage"/>"
                                               name="message">
                                    </label>
         */
    }
}
