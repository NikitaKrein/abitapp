package by.epam.krein.abitapp.service.impl;

import by.epam.krein.abitapp.dao.AdminDAO;
import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.UserDAO;
import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.exception.DAOException;
import by.epam.krein.abitapp.exception.ServiceException;
import by.epam.krein.abitapp.service.AdminService;

import java.sql.SQLException;

public class AdminServiceImpl implements AdminService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    AdminDAO adminDAO = daoFactory.getAdminDAO();

    @Override
    public void updatePassword(int id, char[] password) throws ServiceException{
        try {
            adminDAO.updatePassword(id, password);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public Admin findByEmail(String email) throws ServiceException {
        try {
            return adminDAO.findByEmail(email);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }
}
