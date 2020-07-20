package by.epam.krein.abitapp.service;

import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.University;

import java.sql.SQLException;
import java.util.List;

public interface UniversityService {
    public University findUniversityById(int id) throws SQLException;
    public List<University> findMain() throws SQLException;
    public List<University> findByParentId(int id) throws SQLException;
    //public void updateUniversity(University university) throws SQLException;
    public void updateUniversity(String name, String information, int id) throws SQLException;
}
