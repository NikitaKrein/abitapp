package by.epam.krein.abitapp.service.impl;

import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.UniversityDAO;
import by.epam.krein.abitapp.dao.UserDAO;
import by.epam.krein.abitapp.entity.University;
import by.epam.krein.abitapp.service.UniversityService;

import java.sql.SQLException;
import java.util.List;

public class UniversityServiceImpl implements UniversityService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    UniversityDAO universityDAO = daoFactory.getUniversityDAO();

    @Override
    public List<University> findMain() throws SQLException {
        return universityDAO.findMain();
    }

    @Override
    public List<University> findByParentId(int parentId) throws SQLException {
        return universityDAO.findByParentId(parentId);
    }

    @Override
    public University findUniversityById(int id) throws SQLException{
        return universityDAO.findUniversity(id);
    }

//    @Override
//    public void updateUniversity(University university) throws SQLException {
//        universityDAO.update(university);
//    }

    @Override
    public void updateUniversity(String name, String information, int id) throws SQLException {
        universityDAO.updateUniversity(name, information, id);
    }
}
