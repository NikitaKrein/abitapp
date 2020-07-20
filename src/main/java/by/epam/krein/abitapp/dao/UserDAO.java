package by.epam.krein.abitapp.dao;

import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.User;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDAO {

    public List<Pair<Exam, Integer>> findUserExamScore(int id) throws SQLException;
    void createUser(User user) throws SQLException;
    void createUserExamMark(User user) throws SQLException;
    void createUserWithExamMark(User user) throws SQLException;
    List<User> findAll() throws SQLException;
    Map<Exam, Integer> findUserExamMark(User user) throws SQLException;
    User findUserByEmail(String email) throws SQLException;
    public void updateUserRequest(int requestSpecialtyId, int formOfTraining, int id) throws SQLException;
    void update(User user) throws SQLException;
    void delete(User user) throws SQLException;
    public User findUserById(int id) throws SQLException;
    public int findRatingApproved(User user) throws SQLException;
    public int findRatingApprovedPattern(User user, String pattern) throws SQLException;
    public int findRatingAll(User user) throws SQLException;
    public int findRatingAllPattern(User user, String pattern) throws SQLException;
    public void deleteAndCreateViewRating(User user) throws SQLException;
    public int findSameRatingApproved(User user) throws SQLException;
    public int findSameRatingAll(User user) throws SQLException;
    public User setUser(ResultSet resultSet) throws SQLException;
    public void acceptedUpdateUserSpecialty(int id, int specialtyId) throws SQLException;
    public void rejectUpdateUserSpecialty(int id, String message) throws SQLException;
}
