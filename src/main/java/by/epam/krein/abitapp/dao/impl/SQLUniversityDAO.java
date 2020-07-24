package by.epam.krein.abitapp.dao.impl;

import by.epam.krein.abitapp.connectionPool.Connector;
import by.epam.krein.abitapp.connection.ConnectionDB;
import by.epam.krein.abitapp.dao.UniversityDAO;
import by.epam.krein.abitapp.entity.University;
import by.epam.krein.abitapp.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUniversityDAO implements UniversityDAO {

    // for TomCat
    // Connection connection = ConnectionPool.getInstance().getConnection();
    //private final Connection connection = ConnectionDB.getConnection();

    //private final Connection connection = Connector.getConnection();

    /**
     * SQL queries
     */

    private static final String FIND_BY_ID = "SELECT * FROM university WHERE id = ?";
    private static final String UPDATE_UNIVERSITY = "UPDATE university SET name = ?, information = ?  WHERE id = ?";


    private static final String CREATE_UNIVERSITY = "INSERT INTO university (name, parent_id, information) VALUES(?, ?, ?)";
    //private static final String UPDATE_UNIVERSITY = "UPDATE university SET name = ?, parent_id = ?, information = ?  WHERE id = ?";
    private static final String DELETE_UNIVERSITY = "DELETE FROM university WHERE id = ?";
    private static final String FIND_BY_PARENT_ID = "SELECT * FROM university WHERE parent_id = ?";
    private static final String FIND_MAIN_UNIVERSITY = "SELECT * FROM university WHERE parent_id is null";
    private static final String FIND_ALL = "SELECT * FROM university";


    @Override
    public University findUniversity(int parentId) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, parentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return setUniversity(resultSet);
            } else {
                return null;
            }
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    private University setUniversity(ResultSet resultSet) throws DAOException {
        try {
            University university = new University();
            university.setId(resultSet.getInt("id"));
            university.setName(resultSet.getString("name"));
            university.setInformation(resultSet.getString("information"));
            if (resultSet.getInt("parent_id") != 0) {
                university.setUniversity(findUniversity(resultSet.getInt("parent_id")));
            }
            university.setFaculty(resultSet.getBoolean("isFaculty"));
            return university;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        }
    }

    @Override
    public List<University> findMain() throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_MAIN_UNIVERSITY);
            List<University> universities = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                University university = setUniversity(resultSet);
                universities.add(university);
            }
            return universities;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public List<University> findByParentId(int id) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PARENT_ID);
            List<University> universities = new ArrayList<>();
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                University university = setUniversity(resultSet);
                universities.add(university);
            }
            return universities;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void updateUniversity(String name, String information, int id) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_UNIVERSITY);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, information);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }


    @Override
    public void create(University university) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_UNIVERSITY);
            preparedStatement.setString(1, university.getName());
            if (university.getUniversity() == null) {
                preparedStatement.setObject(2, null);
            } else {
                preparedStatement.setInt(2, university.getUniversity().getId());
            }
            preparedStatement.setString(3, university.getInformation());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }


    @Override
    public List<University> findAll() throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            List<University> universities = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                University university = setUniversity(resultSet);
                universities.add(university);
            }
            return universities;
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

    @Override
    public void delete(University university) throws DAOException {
        Connection connection = Connector.getConnection();
        try {
            PreparedStatement firstPreparedStatement = connection.prepareStatement(DELETE_UNIVERSITY);
            PreparedStatement secondPreparedStatement = connection.prepareStatement(FIND_BY_PARENT_ID);

            firstPreparedStatement.setInt(1, university.getId());
            firstPreparedStatement.executeUpdate();
            secondPreparedStatement.setInt(1, university.getId());
            ResultSet resultSet = secondPreparedStatement.executeQuery();
            while (resultSet.next()) {
                University predUniversity = setUniversity(resultSet);
                delete(predUniversity);
            }
        } catch (SQLException exception) {
            throw new DAOException("message", exception);
        } finally {
            Connector.releaseConnection(connection);
        }
    }

}
