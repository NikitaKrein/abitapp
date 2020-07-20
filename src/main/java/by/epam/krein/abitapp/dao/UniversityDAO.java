package by.epam.krein.abitapp.dao;

import by.epam.krein.abitapp.entity.University;

import java.sql.SQLException;
import java.util.List;

public interface UniversityDAO {
    public void create(University university) throws SQLException;
    public List<University> findAll() throws SQLException;
    public List<University> findMain() throws SQLException;
    public List<University> findByParentId(int id) throws SQLException;
    public University findUniversity(int id) throws SQLException;
    public void update(University university) throws SQLException;
    public void delete(University university) throws SQLException;
    public void updateUniversity(String name, String information, int id) throws SQLException;
}
