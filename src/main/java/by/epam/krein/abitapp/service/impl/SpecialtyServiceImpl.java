package by.epam.krein.abitapp.service.impl;

import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.SpecialtyDAO;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.DAOException;
import by.epam.krein.abitapp.exception.ServiceException;
import by.epam.krein.abitapp.service.SpecialtyService;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.SQLException;
import java.util.List;

public class SpecialtyServiceImpl implements SpecialtyService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    SpecialtyDAO specialtyDAO = daoFactory.getSpecialtyDAO();

    @Override
    public Specialty findById(int id) throws ServiceException {
        try {
            return specialtyDAO.findById(id);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public Specialty findFacultyByUniversityId(int id) throws ServiceException {
        try {
            return specialtyDAO.findByUniversityId(id);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public List<Specialty> findSpecialtiesByFacultyId(int facultyId) throws ServiceException {
        try {
            return specialtyDAO.findByFacultyId(facultyId);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public List<Pair<User, Integer>> getSpecialtyRating(int specialtyId, int formOfTraining) throws ServiceException {
        try {
            return specialtyDAO.getSpecialtyRating(specialtyId, formOfTraining);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public void updateSpecialty(int id, String name, String information, int admissionPlanForFree, int admissionPlanForPaid, int admissionPlanForCorrespondenceCourseForFree, int admissionPlanForCorrespondenceCourseForPaid) throws ServiceException {
        try {
            specialtyDAO.updateSpecialtyInformation(id, name, information, admissionPlanForFree, admissionPlanForPaid, admissionPlanForCorrespondenceCourseForFree, admissionPlanForCorrespondenceCourseForPaid);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public void updateSpecialtyExam(int facultyId, String exam, String newExam) throws ServiceException {
        try {
            specialtyDAO.updateSpecialtyExam(facultyId, exam, newExam);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public void deleteSpecialtyExam(int facultyId, String exam) throws ServiceException {
        try {
            specialtyDAO.deleteFacultyExam(facultyId, exam);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public void createSpecialtyExam(int facultyId, String exam) throws ServiceException {
        try {
            specialtyDAO.createFacultyExam(facultyId, exam);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public List<Pair<User, Integer>> findUsersWithRequest(int specialtyId) throws ServiceException {
        try {
            return specialtyDAO.findUsersWithRequest(specialtyId);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }

    @Override
    public List<User> findFacultyUsers(int facultyId) throws ServiceException {
        try {
            return specialtyDAO.findFacultyUsers(facultyId);
        } catch (DAOException daoException) {
            throw new ServiceException("message", daoException);
        }
    }
}
