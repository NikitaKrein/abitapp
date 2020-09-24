package by.epam.krein.abitapp.service;

import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.ServiceException;

import java.sql.SQLException;

public interface UserService {
    public User signIn(String email) throws ServiceException;
    public void signUp(User user) throws ServiceException;
    public User findUserById(int id) throws ServiceException;
    public void updateUserRequest(int requestSpecialtyId, int formOfTraining, int id) throws ServiceException;
    public void updateUser(User user) throws ServiceException;
    public void acceptedUpdateUserSpecialty(int id, int specialtyId) throws ServiceException;
    public void rejectUpdateUserSpecialty(int id, String message) throws ServiceException;
    public void updateUserPassword(int id, char[] password) throws ServiceException;

}
