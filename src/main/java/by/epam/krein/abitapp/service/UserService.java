package by.epam.krein.abitapp.service;

import by.epam.krein.abitapp.entity.User;

import java.sql.SQLException;

public interface UserService {
    public User signIn(String email) throws SQLException;
    public void signUp(User user) throws SQLException;
    public User findUserById(int id) throws SQLException;
    public void updateUserRequest(int requestSpecialtyId, int formOfTraining, int id) throws SQLException;
    public void updateUser(User user) throws SQLException;
    public int getUserRatingApproved(User user) throws SQLException;
    public int getUserRatingAll(User user) throws SQLException;
    public int getCountOfUserWithSameRatingAll(User user) throws SQLException;
    public int getCountOfUserWithSameRatingApproved(User user) throws SQLException;
    public void makeView(User user) throws SQLException;
    public void acceptedUpdateUserSpecialty(int id, int specialtyId) throws SQLException;
    public void rejectUpdateUserSpecialty(int id, String message) throws SQLException;

}
