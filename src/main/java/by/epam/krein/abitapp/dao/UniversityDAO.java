package by.epam.krein.abitapp.dao;

import by.epam.krein.abitapp.entity.University;
import by.epam.krein.abitapp.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface UniversityDAO {
    public void create(University university) throws DAOException;
    public List<University> findAll() throws DAOException;
    public List<University> findMain() throws DAOException;
    public List<University> findByParentId(int id) throws DAOException;
    public University findUniversity(int id) throws DAOException;
    public void delete(University university) throws DAOException;
    public void updateUniversity(String name, String information, int id) throws DAOException;
}
