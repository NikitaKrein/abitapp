package by.epam.krein.abitapp.dao.impl;

import by.epam.krein.abitapp.connectionPool.Connector;
import by.epam.krein.abitapp.connection.ConnectionDB;
import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.SpecialtyDAO;
import by.epam.krein.abitapp.dao.UniversityDAO;
import by.epam.krein.abitapp.dao.UserDAO;
import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.DAOException;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLSpecialtyDAO implements SpecialtyDAO {

    //private final Connection connection = Connector.getConnection();
    //private final Connection connection = ConnectionDB.getConnection();


    /**
     * SQL queries
     */
    private static final String CREATE_FACULTY = "INSERT INTO faculty (admissionPlanForFree, admissionPlanForPaid, admissionPlanForCorrespondenceCourseForFree, admissionPlanForCorrespondenceCourseForPaid, university_id) VALUES(?, ?, ?, ?, ?)";
    private static final String CREATE_SPECIALTY_EXAM = "INSERT INTO specialty_exam (specialty_id, examName) VALUES(?, ?)";
    private static final String UPDATE_SPECIALTY_EXAM = "UPDATE specialty_exam SET examName = ? WHERE id = ?";
    private static final String UPDATE_SPECIALTY = "UPDATE specialty SET name = ?, information = ?, admissionPlanForFree = ?, admissionPlanForPaid = ?, admissionPlanForCorrespondenceCourseForFree = ?, admissionPlanForCorrespondenceCourseForPaid = ? WHERE id = ?";
    private static final String DELETE_SPECIALTY_EXAM = "DELETE FROM specialty_exam WHERE id = ?";
    private static final String FIND_SPECIALTY_EXAM = "SELECT id FROM specialty_exam WHERE specialty_id = ? AND examName = ?";
    private static final String FIND_UNIVERSITY = "SELECT * FROM university WHERE name = ? AND parent_id = ?";
    private static final String FIND_BY_UNIVERSITY_ID = "SELECT * FROM faculty WHERE university_id = ?";
    private static final String FIND_BY_ID = "SELECT * FROM specialty WHERE id = ?";
    private static final String FIND_BY_FACULTY_ID = "SELECT * FROM specialty WHERE faculty_id = ?";
    private static final String FIND_EXAMS = "SELECT * FROM specialty_exam WHERE specialty_id = ?";
    private static final String FIND_USERS_WITH_REQUEST = "SELECT * FROM user WHERE request_specialty_id = ? AND adminMessage is null";
    private static final String FIND_SPECIALTY_RATING = "WITH rating as (SELECT user_id, SUM(user_exam_mark.mark) as sumMark FROM user_exam_mark INNER JOIN specialty_exam ON specialty_exam.examName = user_exam_mark.examName AND specialty_id = ? GROUP BY user_id) SELECT * FROM rating INNER JOIN user ON user_id=user.id && specialty_id=? && formOfTraining=? ORDER BY sumMark DESC";


    @Override
    public Specialty findById(int id) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return setSpecialty(resultSet);
            }
            return null;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }

    }

    private Specialty setSpecialty(ResultSet resultSet) throws DAOException {
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UniversityDAO universityDAO = daoFactory.getUniversityDAO();

            Specialty specialty = new Specialty();
            specialty.setId(resultSet.getInt("id"));
            specialty.setName(resultSet.getString("name"));
            specialty.setInformation(resultSet.getString("information"));
            specialty.setAdmissionPlanForFree(resultSet.getInt("admissionPlanForFree"));
            specialty.setAdmissionPlanForPaid(resultSet.getInt("admissionPlanForPaid"));
            specialty.setAdmissionPlanForCorrespondenceCourseForFree(resultSet.getInt("admissionPlanForCorrespondenceCourseForFree"));
            specialty.setAdmissionPlanForCorrespondenceCourseForPaid(resultSet.getInt("admissionPlanForCorrespondenceCourseForPaid"));
            specialty.setUniversity(universityDAO.findUniversity(resultSet.getInt("faculty_id")));
            specialty.setNameOfExams(findSpecialtyExams(specialty.getId()));
            return specialty;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        }
    }

    @Override
    public List<Specialty> findByFacultyId(int facultyId) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            List<Specialty> specialties = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FACULTY_ID);
            preparedStatement.setInt(1, facultyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                specialties.add(setSpecialty(resultSet));
            }
            return specialties;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }


    @Override
    public List<Exam> findSpecialtyExams(int id) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            List<Exam> exams = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_EXAMS);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                exams.add(Exam.valueOf(resultSet.getString("examName")));
            }
            return exams;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public List<Pair<User, Integer>> getSpecialtyRating(int specialtyId, int formOfTraining) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            List<Pair<User, Integer>> usersRating = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_SPECIALTY_RATING);
            preparedStatement.setInt(1, specialtyId);
            preparedStatement.setInt(2, specialtyId);
            preparedStatement.setInt(3, formOfTraining);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersRating.add(Pair.of(userDAO.setUser(resultSet), resultSet.getInt("sumMark")));
            }
            return usersRating;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void updateSpecialtyInformation(int id, String name, String information, int admissionPlanForFree, int admissionPlanForPaid, int admissionPlanForCorrespondenceCourseForFree, int admissionPlanForCorrespondenceCourseForPaid) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SPECIALTY);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, information);
            preparedStatement.setInt(3, admissionPlanForFree);
            preparedStatement.setInt(4, admissionPlanForPaid);
            preparedStatement.setInt(5, admissionPlanForCorrespondenceCourseForFree);
            preparedStatement.setInt(6, admissionPlanForCorrespondenceCourseForPaid);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    //не интерфейс метода
    public int findIdSpecialtyExam(int specialtyId, String exam) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_SPECIALTY_EXAM);
            preparedStatement.setInt(1, specialtyId);
            preparedStatement.setString(2, exam);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return -1;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void updateSpecialtyExam(int specialtyId, String exam, String newExam) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            int id = findIdSpecialtyExam(specialtyId, exam);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SPECIALTY_EXAM);
            preparedStatement.setString(1, newExam);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void deleteFacultyExam(int specialtyId, String exam) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            int id = findIdSpecialtyExam(specialtyId, exam);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SPECIALTY_EXAM);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void createFacultyExam(int specialtyId, String exam) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SPECIALTY_EXAM);
            preparedStatement.setInt(1, specialtyId);
            preparedStatement.setString(2, exam);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }


    @Override
    public void createFacultyWithExam(Specialty specialty) throws DAOException {
        createFaculty(specialty);
        //createFacultyExam(specialty.getId(), );

    }

    @Override
    public void createFaculty(Specialty specialty) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_FACULTY);
            DAOFactory daoFactory = DAOFactory.getInstance();
            UniversityDAO universityDAO = daoFactory.getUniversityDAO();

            universityDAO.create(specialty);
            preparedStatement.setInt(1, specialty.getAdmissionPlanForFree());
            preparedStatement.setInt(2, specialty.getAdmissionPlanForPaid());
            preparedStatement.setInt(3, specialty.getAdmissionPlanForCorrespondenceCourseForFree());
            preparedStatement.setInt(4, specialty.getAdmissionPlanForCorrespondenceCourseForPaid());
            preparedStatement.setInt(5, findUniversityId(specialty));
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public int findUniversityId(Specialty specialty) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_UNIVERSITY);
            preparedStatement.setString(1, specialty.getName());
            preparedStatement.setInt(2, specialty.getUniversity().getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }


    @Override
    public void delete(Specialty specialty) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UniversityDAO universityDAO = daoFactory.getUniversityDAO();
        universityDAO.delete(universityDAO.findUniversity(findUniversityId(specialty)));
    }

    @Override
    public Specialty findByUniversityId(int id) throws DAOException {
        Specialty specialty = new Specialty();
        return specialty;
    }
//        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_UNIVERSITY_ID);
//        preparedStatement.setInt(1, id);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        if (resultSet.next()){
//            return setFaculty(resultSet);
//        }
//        return null;
//    }

    @Override
    public List<Pair<User, Integer>> findUsersWithRequest(int specialtyId) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERS_WITH_REQUEST);
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();

            List<Pair<User, Integer>> users = new ArrayList<>();
            preparedStatement.setInt(1, specialtyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = userDAO.setUser(resultSet);
                int score = calculateUserSpecialtyScore(user.getId(), user.getRequestSpecialty().getId());
                users.add(Pair.of(user, score));
            }
            return users;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    public int calculateUserSpecialtyScore(int userId, int specialtyId) throws DAOException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        int score = 0;
        List<Exam> specialtyExams = findSpecialtyExams(specialtyId);
        List<Pair<Exam, Integer>> userExamScore = userDAO.findUserExamScore(userId);
        for (Exam specialtyExam : specialtyExams) {
            for (Pair<Exam, Integer> userExam : userExamScore) {
                if (specialtyExam == userExam.getKey()) {
                    score += userExam.getValue();
                    break;
                }
            }
        }
        return score;
    }


    @Override
    public List<User> findFacultyUsers(int facultyId) throws DAOException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO userDAO = daoFactory.getUserDAO();
        List<User> users = new ArrayList<>();
//        PreparedStatement preparedStatement = connection.prepareStatement(FIND_FACULTY_RATING);
//        preparedStatement.setInt(1, facultyId);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while(resultSet.next()){
//            users.add(userDAO.setUser(resultSet));
//        }
        return users;
    }


}
