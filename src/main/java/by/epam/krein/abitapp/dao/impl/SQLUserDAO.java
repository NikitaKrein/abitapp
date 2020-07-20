package by.epam.krein.abitapp.dao.impl;

import by.epam.krein.abitapp.ConnectionPool.Connector;
import by.epam.krein.abitapp.connection.ConnectionDB;
import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.SpecialtyDAO;
import by.epam.krein.abitapp.dao.UserDAO;
import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLUserDAO implements UserDAO {

    // for TomCat
    //private final Connection connection = ConnectionPool.getInstance().getConnection();
    //private final Connection connection = ConnectionDB.getConnection();
    private final Connection connection = Connector.getConnection();

    /**
     * SQL queries
     */

    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    private static final String FIND_USER_EXAM_MARK = "SELECT * FROM user_exam_mark WHERE user_id = ?";
    private static final String CREATE_USER = "INSERT INTO user (name, surname, email, password) VALUES(?, ?, ?, ?)";
    private static final String CREATE_USER_EXAM_MARK = "INSERT INTO user_exam_mark (user_id, examName, mark) VALUES(?, ?, ?)";
    private static final String UPDATE_USER_REQUEST = "UPDATE user SET request_specialty_id = ?, specialty_id = null, formOfTraining=?, adminMessage=null WHERE id = ?";

    private static final String UPDATE_USER = "UPDATE user SET name = ?, surname = ?, email = ?, password = ?, request_faculty_id = ?, faculty_id = ?, formOfTraining=?, adminMessage=?  WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM user WHERE id = ?";

    private static final String FIND_USER_BY_EMAIL_PASSWORD = "SELECT * FROM user WHERE user.email = ? AND user.password = ?";

    private static final String FIND_ALL = "SELECT * FROM user";
    private static final String FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String DELETE_VIEW_WITH_SUM_MARK = "DROP VIEW userMark";
    private static final String CREATE_VIEW_WITH_SUM_MARK = "CREATE VIEW userMark as SELECT user_id, SUM(user_exam_mark.mark) as sumMark FROM user_exam_mark INNER JOIN faculty_exam ON faculty_exam.examName = user_exam_mark.examName AND faculty_id = ? GROUP BY user_id";
    private static final String FIND_RATING_APPROVED = "SELECT COUNT(*) FROM userMark INNER JOIN user ON user_id=user.id AND faculty_id=? AND formOfTraining = ? WHERE sumMark > ?";
    private static final String FIND_RATING_ALL = "SELECT COUNT(*) FROM userMark INNER JOIN user ON user_id=user.id AND (faculty_id=? OR request_faculty_id = ?) AND formOfTraining = ? WHERE sumMark > ?";
    private static final String COUNT_WITH_SAME_RATING_APPROVED = "SELECT COUNT(*) FROM userMark INNER JOIN user ON user_id=user.id AND faculty_id=? AND formOfTraining = ? WHERE sumMark = ?";
    private static final String COUNT_WITH_SAME_RATING_ALL = "SELECT COUNT(*) FROM userMark INNER JOIN user ON user_id=user.id AND (faculty_id=? OR request_faculty_id = ?) AND formOfTraining = ? WHERE sumMark = ?";
    private static final String SORT_RATING = "SELECT * FROM userMark INNER JOIN user ON user_id=user.id AND (faculty_id=? OR request_faculty_id = ?) AND formOfTraining = ? WHERE sumMark = ?";
    private static final String FIND_USER_SUM_MARK = "SELECT * FROM userMark WHERE user_id = ?";
    private static final String ACCEPT_UPDATE_USER_SPECIALTY = "UPDATE user SET request_specialty_id = null, specialty_id = ? WHERE id = ?";
    private static final String REJECT_UPDATE_USER_SPECIALTY = "UPDATE user SET adminMessage = ? WHERE id = ?";
    private static final String FIND_USER_SCORE = "SELECT * FROM user_exam_mark WHERE user_id = ?";

    @Override
    public User findUserByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return setUser(resultSet);
        }
        return null;
    }

    @Override
    public User setUser(ResultSet resultSet) throws SQLException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        SpecialtyDAO sqlSpecialtyDAO = daoFactory.getSpecialtyDAO();

        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password").toCharArray());

        Specialty specialty = null;
        if (resultSet.getInt("request_specialty_id") != 0) {
            specialty = sqlSpecialtyDAO.findById(resultSet.getInt("request_specialty_id"));
            user.setRequestSpecialty(specialty);
        }
        if (resultSet.getInt("specialty_id") != 0) {
            specialty = sqlSpecialtyDAO.findById(resultSet.getInt("specialty_id"));
            user.setSpecialty(specialty);
        }

        user.setFormOfTraining(resultSet.getInt("formOfTraining"));
        user.setAdminMessage(resultSet.getString("adminMessage"));

        user.setExamMarks(findUserExamMark(user));

        return user;
    }

    @Override
    public Map<Exam, Integer> findUserExamMark(User user) throws SQLException {
        Map<Exam, Integer> examMark = new HashMap<>();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_EXAM_MARK);
        preparedStatement.setInt(1, user.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            examMark.put(Exam.valueOf(resultSet.getString("examName")), resultSet.getInt("mark"));
        }
        return examMark;
    }

    @Override
    public void createUserWithExamMark(User user) throws SQLException {
        if(findUserByEmail(user.getEmail()) != null){
            return;
        }
        createUser(user);
        createUserExamMark(user);
    }

    @Override
    public void createUser(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, String.valueOf(user.getPassword()));
        preparedStatement.executeUpdate();
    }

    @Override
    public void createUserExamMark(User user) throws SQLException {
        int id = findUserIdByEmail(user.getEmail());
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_EXAM_MARK);
        for (Map.Entry<Exam, Integer> entry : user.getExamMarks().entrySet()) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, entry.getKey().toString());
            preparedStatement.setInt(3, entry.getValue());
            preparedStatement.executeUpdate();
        }
    }

    private int findUserIdByEmail(String email) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("id");
        }
        return -1;
    }

    @Override
    public void updateUserRequest(int requestSpecialtyId, int formOfTraining, int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_REQUEST);
        preparedStatement.setInt(1, requestSpecialtyId);
        preparedStatement.setInt(2, formOfTraining);
        preparedStatement.setInt(3, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Pair<Exam, Integer> > findUserExamScore(int id) throws SQLException {
        List<Pair<Exam, Integer> > userExamScore = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_SCORE);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Exam exam = Exam.valueOf(resultSet.getString("examName"));
            int mark = resultSet.getInt("mark");
            userExamScore.add(Pair.of(exam, mark));
        }
        return userExamScore;
    }

    @Override
    public void acceptedUpdateUserSpecialty(int id, int specialtyId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ACCEPT_UPDATE_USER_SPECIALTY);
        //preparedStatement.setObject(1, null);
        preparedStatement.setInt(1, specialtyId);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void rejectUpdateUserSpecialty(int id, String message) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(REJECT_UPDATE_USER_SPECIALTY);
        preparedStatement.setString(1, message);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
    }











    public SQLUserDAO() {
    }

    public void deleteView() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VIEW_WITH_SUM_MARK);
        preparedStatement.executeUpdate();

    }

    public void createView(int facultyId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_VIEW_WITH_SUM_MARK);
        preparedStatement.setInt(1, facultyId);
        preparedStatement.executeUpdate();
    }

    public int findUserSumMark(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_SUM_MARK);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("sumMark");
        }
        return 0;
    }

    @Override
    public int findRatingApproved(User user) throws SQLException {
        return findRatingApprovedPattern(user, FIND_RATING_APPROVED);
    }

    @Override
    public int findSameRatingApproved(User user) throws SQLException {
        return findRatingApprovedPattern(user, COUNT_WITH_SAME_RATING_APPROVED);
    }

    @Override
    public int findRatingApprovedPattern(User user, String pattern) throws SQLException {
        int facultyId;
        if (user.getSpecialty() == null) {
            facultyId = user.getRequestSpecialty().getId();
        } else {
            facultyId = user.getSpecialty().getId();
        }
        PreparedStatement preparedStatement = connection.prepareStatement(pattern);
        preparedStatement.setInt(1, facultyId);
        preparedStatement.setInt(2, user.getFormOfTraining());
        preparedStatement.setInt(3, findUserSumMark(user.getId()));
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return -1;
    }

    @Override
    public int findRatingAll(User user) throws SQLException {
        return findRatingAllPattern(user, FIND_RATING_ALL);
    }

    @Override
    public int findSameRatingAll(User user) throws SQLException {
        return findRatingAllPattern(user, COUNT_WITH_SAME_RATING_ALL);
    }

    @Override
    public int findRatingAllPattern(User user, String pattern) throws SQLException {
        int facultyId;
        if (user.getSpecialty() == null) {
            facultyId = user.getRequestSpecialty().getId();
        } else {
            facultyId = user.getSpecialty().getId();
        }
        PreparedStatement preparedStatement = connection.prepareStatement(pattern);
        preparedStatement.setInt(1, facultyId);
        preparedStatement.setInt(2, facultyId);
        preparedStatement.setInt(3, user.getFormOfTraining());
        preparedStatement.setInt(4, findUserSumMark(user.getId()));
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }
        return -1;
    }

    @Override
    public void deleteAndCreateViewRating(User user) throws SQLException {
        int facultyId;
        if (user.getSpecialty() == null) {
            facultyId = user.getRequestSpecialty().getId();
        } else {
            facultyId = user.getSpecialty().getId();
        }
        deleteView();
        createView(facultyId);
    }


    @Override
    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, String.valueOf(user.getPassword()));
        if (user.getRequestSpecialty() == null) {
            preparedStatement.setObject(5, null);
        } else {
            preparedStatement.setInt(5, user.getRequestSpecialty().getId());
        }
        if (user.getSpecialty() == null) {
            preparedStatement.setObject(6, null);
        } else {
            preparedStatement.setInt(6, user.getSpecialty().getId());
        }
        if (user.getFormOfTraining() == 0 || (user.getSpecialty() == null && user.getRequestSpecialty() == null)) {
            preparedStatement.setObject(7, null);
        } else {
            preparedStatement.setInt(7, user.getFormOfTraining());
        }
        if (user.getAdminMessage() == null) {
            preparedStatement.setObject(8, null);
        } else {
            preparedStatement.setString(8, user.getAdminMessage());
        }
        preparedStatement.setInt(9, user.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(User user) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = setUser(resultSet);
            users.add(user);
        }
        return users;
    }


    @Override
    public User findUserById(int id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return setUser(resultSet);
        }
        return null;
    }


}
