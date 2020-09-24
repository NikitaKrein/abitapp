package by.epam.krein.abitapp.dao;

import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.DAOException;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public interface UserDAO {

    public List<Pair<Exam, Integer>> findUserExamScore(int id) throws DAOException;
    public void createUser(User user) throws DAOException;
    public void createUserExamMark(User user) throws DAOException;
    public void createUserWithExamMark(User user) throws DAOException;
    List<User> findAll() throws DAOException;
    Map<Exam, Integer> findUserExamMark(User user) throws DAOException;
    User findUserByEmail(String email) throws DAOException;
    public void updateUserRequest(int requestSpecialtyId, int formOfTraining, int id) throws DAOException;
    void update(User user) throws DAOException;
    void delete(User user) throws DAOException;
    public User findUserById(int id) throws DAOException;
    public User setUser(ResultSet resultSet) throws DAOException;
    public void acceptedUpdateUserSpecialty(int id, int specialtyId) throws DAOException;
    public void rejectUpdateUserSpecialty(int id, String message) throws DAOException;
    public void updateUserPassword(int id, char[] password) throws DAOException;
}
