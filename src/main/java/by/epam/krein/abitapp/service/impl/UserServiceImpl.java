package by.epam.krein.abitapp.service.impl;

import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.UserDAO;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDAO userDAO = daoFactory.getUserDAO();

    public UserServiceImpl(){}

    @Override
    public User signIn(String email) throws SQLException {
        return userDAO.findUserByEmail(email);
    }

    @Override
    public void signUp(User user) throws SQLException {
        userDAO.createUserWithExamMark(user);
    }

    @Override
    public User findUserById(int id) throws SQLException {
        return userDAO.findUserById(id);
    }

    @Override
    public void updateUserRequest(int requestSpecialtyId, int formOfTraining, int id) throws SQLException {
        userDAO.updateUserRequest(requestSpecialtyId, formOfTraining, id);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        userDAO.update(user);
    }

    @Override
    public int getUserRatingApproved(User user) throws SQLException {
        return userDAO.findRatingApproved(user);
    }

    @Override
    public int getUserRatingAll(User user) throws SQLException {
        return userDAO.findRatingAll(user);
    }

    @Override
    public int getCountOfUserWithSameRatingAll(User user) throws SQLException {
        return userDAO.findSameRatingAll(user);
    }

    @Override
    public int getCountOfUserWithSameRatingApproved(User user) throws SQLException {
        return userDAO.findSameRatingApproved(user);
    }

    @Override
    public void makeView(User user) throws SQLException {
        userDAO.deleteAndCreateViewRating(user);
    }

    @Override
    public void acceptedUpdateUserSpecialty(int id, int specialtyId) throws SQLException {
        userDAO.acceptedUpdateUserSpecialty(id, specialtyId);
    }

    @Override
    public void rejectUpdateUserSpecialty(int id, String message) throws SQLException {
        userDAO.rejectUpdateUserSpecialty(id, message);
    }

}
