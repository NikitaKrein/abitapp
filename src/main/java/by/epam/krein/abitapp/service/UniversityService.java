package by.epam.krein.abitapp.service;

import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.University;
import by.epam.krein.abitapp.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface UniversityService {
    public University findUniversityById(int id) throws ServiceException;
    public List<University> findMain() throws ServiceException;
    public List<University> findByParentId(int id) throws ServiceException;
    //public void updateUniversity(University university) throws ServiceException;
    public void updateUniversity(String name, String information, int id) throws ServiceException;
}
