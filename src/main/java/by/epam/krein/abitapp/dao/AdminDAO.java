package by.epam.krein.abitapp.dao;

import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.exception.DAOException;

import java.sql.SQLException;

public interface AdminDAO {
    public void updatePassword(int id, char[] password) throws DAOException;
    public void create(Admin admin) throws DAOException;
    public void delete(Admin admin) throws DAOException;
    public Admin findByEmail(String email) throws DAOException;
}
