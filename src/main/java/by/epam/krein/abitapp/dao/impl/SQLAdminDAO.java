package by.epam.krein.abitapp.dao.impl;

import by.epam.krein.abitapp.ConnectionPool.Connector;
import by.epam.krein.abitapp.connection.ConnectionDB;
import by.epam.krein.abitapp.dao.AdminDAO;
import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.SpecialtyDAO;
import by.epam.krein.abitapp.dao.UniversityDAO;
import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLAdminDAO implements AdminDAO {

    // for TomCat
    // Connection connection = ConnectionPool.getInstance().getConnection();


    //private final Connection connection = Connector.getConnection();

//    private final Connection connection = ConnectionDB.getConnection();


    /**
     * SQL queries
     */
    private static final String CREATE_ADMIN = "INSERT INTO admin (email, password, adminLevel) VALUES(?, ?, ?)";
    private static final String DELETE_ADMIN = "DELETE FROM admin WHERE id = ?";
    private static final String FIND_ADMIN_BY_EMAIL = "SELECT * FROM admin WHERE email = ?";
    private static final String UPDATE_ADMIN_PASSWORD = "UPDATE admin SET password = ? WHERE id = ?";


    @Override
    public void updatePassword(int id, String password) throws DAOException {
        try {
            Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADMIN_PASSWORD);
            preparedStatement.setString(1, password);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            //connection.close();
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        }
    }


    @Override
    public void create(Admin admin) throws DAOException {
        try {
            Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ADMIN);
            preparedStatement.setString(1, admin.getEmail());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setInt(3, admin.getUniversity().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        }
    }

    @Override
    public void delete(Admin admin) throws SQLException {
        try {
            Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN);
            preparedStatement.setInt(1, admin.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        }
    }

    @Override
    public Admin findByEmail(String email) throws DAOException {
        try {
            Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ADMIN_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return setAdmin(resultSet);
            }
            return null;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        }
    }

    private Admin setAdmin(ResultSet resultSet) throws DAOException{
        try {
            Connection connection = Connector.getConnection();

            DAOFactory daoFactory = DAOFactory.getInstance();
            UniversityDAO sqlUniversityDAO = daoFactory.getUniversityDAO();
            SpecialtyDAO sqlSpecialtyDAO = daoFactory.getSpecialtyDAO();

            Admin admin = new Admin();
            admin.setId(resultSet.getInt("id"));
            admin.setEmail(resultSet.getString("email"));
            admin.setPassword(resultSet.getString("password"));
            admin.setUniversity(sqlUniversityDAO.findUniversity(resultSet.getInt("adminLevel")));
            return admin;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        }
    }
}
