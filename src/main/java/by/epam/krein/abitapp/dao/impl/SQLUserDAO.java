package by.epam.krein.abitapp.dao.impl;

import by.epam.krein.abitapp.connectionPool.Connector;
import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.SpecialtyDAO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLUserDAO implements UserDAO {

    // for TomCat
    //private final Connection connection = ConnectionPool.getInstance().getConnection();
    //private final Connection connection = ConnectionDB.getConnection();
    //private final Connection connection = Connector.getConnection();

    /**
     * SQL queries
     */

    private static final String CREATE_USER = "INSERT INTO user (name, surname, email, password) VALUES(?, ?, ?, ?)";
    private static final String CREATE_USER_EXAM_MARK = "INSERT INTO user_exam_mark (user_id, examName, mark) VALUES(?, ?, ?)";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";
    private static final String FIND_USER_EXAM_MARK = "SELECT * FROM user_exam_mark WHERE user_id = ?";
    private static final String FIND_ALL = "SELECT * FROM user";
    private static final String FIND_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String FIND_USER_SCORE = "SELECT * FROM user_exam_mark WHERE user_id = ?";
    private static final String UPDATE_USER_PASSWORD = "UPDATE user SET password = ? WHERE id = ?";

    private static final String UPDATE_USER_REQUEST = "UPDATE user SET request_specialty_id = ?, specialty_id = null, formOfTraining=?, adminMessage=null WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE user SET name = ?, surname = ?, email = ?, password = ?, request_faculty_id = ?, faculty_id = ?, formOfTraining=?, adminMessage=?  WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
    private static final String ACCEPT_UPDATE_USER_SPECIALTY = "UPDATE user SET request_specialty_id = null, specialty_id = ? WHERE id = ?";
    private static final String REJECT_UPDATE_USER_SPECIALTY = "UPDATE user SET adminMessage = ? WHERE id = ?";


    @Override
    public User findUserByEmail(String email) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return setUser(resultSet);
            }
            return null;
        } catch (SQLException exception) {
            throw new DAOException("Field findUserByEmail", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public User setUser(ResultSet resultSet) throws DAOException {
        try {
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
        } catch (SQLException exception) {
            throw new DAOException("Field setUser", exception);
        }
    }

    @Override
    public Map<Exam, Integer> findUserExamMark(User user) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_EXAM_MARK);
            Map<Exam, Integer> examMark = new HashMap<>();
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                examMark.put(Exam.valueOf(resultSet.getString("examName")), resultSet.getInt("mark"));
            }
            return examMark;
        } catch (SQLException exception) {
            throw new DAOException("Field findUserExamMark", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void createUserWithExamMark(User user) throws DAOException {
        if (findUserByEmail(user.getEmail()) != null) {
            return;
        }
        createUser(user);
        createUserExamMark(user);
    }

    @Override
    public void createUser(User user) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, String.valueOf(user.getPassword()));
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw new DAOException("Field createUser", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void createUserExamMark(User user) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER_EXAM_MARK);
            int id = findUserIdByEmail(user.getEmail());
            for (Map.Entry<Exam, Integer> entry : user.getExamMarks().entrySet()) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, entry.getKey().toString());
                preparedStatement.setInt(3, entry.getValue());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException exception) {
            throw new DAOException("Field createUserExamMark", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    private int findUserIdByEmail(String email) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            return -1;
        } catch (SQLException exception) {
            throw new DAOException("Field findUserIdByEmail", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void updateUserRequest(int requestSpecialtyId, int formOfTraining, int id) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_REQUEST);
            preparedStatement.setInt(1, requestSpecialtyId);
            preparedStatement.setInt(2, formOfTraining);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("Field updateUserRequest", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public List<Pair<Exam, Integer>> findUserExamScore(int id) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            List<Pair<Exam, Integer>> userExamScore = new ArrayList<>();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_SCORE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Exam exam = Exam.valueOf(resultSet.getString("examName"));
                int mark = resultSet.getInt("mark");
                userExamScore.add(Pair.of(exam, mark));
            }
            return userExamScore;
        } catch (SQLException exception) {
            throw new DAOException("Field findUserExamScore", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void acceptedUpdateUserSpecialty(int id, int specialtyId) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ACCEPT_UPDATE_USER_SPECIALTY);
            //preparedStatement.setObject(1, null);
            preparedStatement.setInt(1, specialtyId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("Field acceptedUpdateUserSpecialty", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void rejectUpdateUserSpecialty(int id, String message) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REJECT_UPDATE_USER_SPECIALTY);
            preparedStatement.setString(1, message);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("Field rejectUpdateUserSpecialty", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
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
        } catch (SQLException exception) {
            throw new DAOException("Field update", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void delete(User user) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("Field delete", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public List<User> findAll() throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            List<User> users = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = setUser(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException exception) {
            throw new DAOException("Field findAll", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }


    @Override
    public User findUserById(int id) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return setUser(resultSet);
            }
            return null;
        } catch (SQLException exception) {
            throw new DAOException("Field findUserById", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void updateUserPassword(int id, char[] password) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD);
            preparedStatement.setString(1, String.valueOf(password));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("Field updateUserPassword", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }
}
