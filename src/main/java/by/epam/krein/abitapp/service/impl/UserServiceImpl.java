package by.epam.krein.abitapp.service.impl;

import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.UserDAO;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.DAOException;
import by.epam.krein.abitapp.exception.ServiceException;
import by.epam.krein.abitapp.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    UserDAO userDAO = daoFactory.getUserDAO();

    public UserServiceImpl() {
    }

    @Override
    public User signIn(String email) throws ServiceException {
        try {
            return userDAO.findUserByEmail(email);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public void signUp(User user) throws ServiceException {
        try {
            userDAO.createUserWithExamMark(user);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public User findUserById(int id) throws ServiceException {
        try {
            return userDAO.findUserById(id);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public void updateUserRequest(int requestSpecialtyId, int formOfTraining, int id) throws ServiceException {
        try {
            userDAO.updateUserRequest(requestSpecialtyId, formOfTraining, id);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public void updateUser(User user) throws ServiceException {
        try {
            userDAO.update(user);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public int getUserRatingApproved(User user) throws ServiceException {
        try {
            return userDAO.findRatingApproved(user);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public int getUserRatingAll(User user) throws ServiceException {
        try {
            return userDAO.findRatingAll(user);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public int getCountOfUserWithSameRatingAll(User user) throws ServiceException {
        try {
            return userDAO.findSameRatingAll(user);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public int getCountOfUserWithSameRatingApproved(User user) throws ServiceException {
        try {
            return userDAO.findSameRatingApproved(user);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public void makeView(User user) throws ServiceException {
        try {
            userDAO.deleteAndCreateViewRating(user);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public void acceptedUpdateUserSpecialty(int id, int specialtyId) throws ServiceException {
        try {
            userDAO.acceptedUpdateUserSpecialty(id, specialtyId);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public void rejectUpdateUserSpecialty(int id, String message) throws ServiceException {
        try {
            userDAO.rejectUpdateUserSpecialty(id, message);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

}
