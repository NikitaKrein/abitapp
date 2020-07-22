package by.epam.krein.abitapp.service;

import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.exception.ServiceException;

import java.sql.SQLException;

public interface AdminService {
    public void updatePassword(int id, String password) throws ServiceException;
    public Admin findByEmail(String email) throws ServiceException;
}
