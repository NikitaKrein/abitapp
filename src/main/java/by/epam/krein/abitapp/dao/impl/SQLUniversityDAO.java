package by.epam.krein.abitapp.dao.impl;

import by.epam.krein.abitapp.ConnectionPool.Connector;
import by.epam.krein.abitapp.connection.ConnectionDB;
import by.epam.krein.abitapp.dao.UniversityDAO;
import by.epam.krein.abitapp.entity.University;

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
    private final Connection connection = Connector.getConnection();

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
    public University findUniversity(int parentId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        preparedStatement.setInt(1, parentId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return setUniversity(resultSet);
        }
        else{
            return null;
        }
    }

    private University setUniversity(ResultSet resultSet) throws SQLException {
        University university = new University();
        university.setId(resultSet.getInt("id"));
        university.setName(resultSet.getString("name"));
        university.setInformation(resultSet.getString("information"));
        if (resultSet.getInt("parent_id") != 0){
            university.setUniversity(findUniversity(resultSet.getInt("parent_id")));
        }
        university.setFaculty(resultSet.getBoolean("isFaculty"));
        return university;
    }

    @Override
    public List<University> findMain() throws SQLException {
        List<University> universities = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_MAIN_UNIVERSITY);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            University university = setUniversity(resultSet);
            universities.add(university);
        }
        return universities;
    }

    @Override
    public List<University> findByParentId(int id) throws SQLException {
        List<University> universities = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PARENT_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            University university = setUniversity(resultSet);
            universities.add(university);
        }
        return universities;
    }

    @Override
    public void updateUniversity(String name, String information, int id) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_UNIVERSITY);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, information);
        preparedStatement.setInt(3, id);
        preparedStatement.executeUpdate();
    }







    @Override
    public void create(University university) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE_UNIVERSITY);
        preparedStatement.setString(1, university.getName());
        if (university.getUniversity() == null) {
            preparedStatement.setObject(2, null);
        } else {
            preparedStatement.setInt(2, university.getUniversity().getId());
        }
        preparedStatement.setString(3, university.getInformation());
        preparedStatement.executeUpdate();
    }



    @Override
    public List<University> findAll() throws SQLException {
        List<University> universities = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            University university = setUniversity(resultSet);
            universities.add(university);
        }
        return universities;
    }

    @Override
    public void update(University university) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_UNIVERSITY);
        preparedStatement.setString(1, university.getName());
        if (university.getUniversity() == null) {
            preparedStatement.setObject(2, null);
        } else {
            preparedStatement.setInt(2, university.getUniversity().getId());
        }
        preparedStatement.setString(3, university.getInformation());
        preparedStatement.setInt(4, university.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(University university) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_UNIVERSITY);
        preparedStatement.setInt(1, university.getId());
        preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(FIND_BY_PARENT_ID);
        preparedStatement.setInt(1, university.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            University predUniversity = setUniversity(resultSet);
            delete(predUniversity);
        }
    }


}
