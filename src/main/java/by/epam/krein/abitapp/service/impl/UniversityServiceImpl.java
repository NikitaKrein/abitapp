package by.epam.krein.abitapp.service.impl;

import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.UniversityDAO;
import by.epam.krein.abitapp.dao.UserDAO;
import by.epam.krein.abitapp.entity.University;
import by.epam.krein.abitapp.exception.DAOException;
import by.epam.krein.abitapp.exception.ServiceException;
import by.epam.krein.abitapp.service.UniversityService;

import java.sql.SQLException;
import java.util.List;

public class UniversityServiceImpl implements UniversityService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    UniversityDAO universityDAO = daoFactory.getUniversityDAO();

    @Override
    public List<University> findMain() throws ServiceException {
        try {
            return universityDAO.findMain();
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public List<University> findByParentId(int parentId) throws ServiceException {
        try {
            return universityDAO.findByParentId(parentId);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public University findUniversityById(int id) throws ServiceException {
        try {
            return universityDAO.findUniversity(id);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

//    @Override
//    public void updateUniversity(University university) throws SQLException {
//        universityDAO.update(university);
//    }

    @Override
    public void updateUniversity(String name, String information, int id) throws ServiceException {
        try {
            universityDAO.updateUniversity(name, information, id);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }
}
