package by.epam.krein.abitapp.service.impl;

import by.epam.krein.abitapp.dao.DAOFactory;
import by.epam.krein.abitapp.dao.SpecialtyDAO;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.service.SpecialtyService;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.SQLException;
import java.util.List;

public class SpecialtyServiceImpl implements SpecialtyService {

    DAOFactory daoFactory = DAOFactory.getInstance();
    SpecialtyDAO specialtyDAO = daoFactory.getSpecialtyDAO();

    @Override
    public Specialty findById(int id) throws SQLException {
        return specialtyDAO.findById(id);
    }


    @Override
    public Specialty findFacultyByUniversityId(int id) throws SQLException {
        return specialtyDAO.findByUniversityId(id);
    }

    @Override
    public List<Specialty> findSpecialtiesByFacultyId(int facultyId) throws SQLException {
        return specialtyDAO.findByFacultyId(facultyId);
    }

    @Override
    public List<Pair<User, Integer>> getSpecialtyRating(int specialtyId, int formOfTraining) throws SQLException{
        return specialtyDAO.getSpecialtyRating(specialtyId, formOfTraining);
    }

    @Override
    public void updateSpecialty(int id, String name, String information, int admissionPlanForFree, int admissionPlanForPaid, int admissionPlanForCorrespondenceCourseForFree, int admissionPlanForCorrespondenceCourseForPaid) throws SQLException {
        specialtyDAO.updateSpecialtyInformation(id, name, information, admissionPlanForFree, admissionPlanForPaid, admissionPlanForCorrespondenceCourseForFree, admissionPlanForCorrespondenceCourseForPaid);
    }

    @Override
    public void updateSpecialtyExam(int facultyId, String exam, String newExam) throws SQLException {
        specialtyDAO.updateSpecialtyExam(facultyId, exam, newExam);
    }

    @Override
    public void deleteSpecialtyExam(int facultyId, String exam) throws SQLException {
        specialtyDAO.deleteFacultyExam(facultyId, exam);
    }

    @Override
    public void createSpecialtyExam(int facultyId, String exam) throws SQLException {
        specialtyDAO.createFacultyExam(facultyId, exam);
    }

    @Override
    public List<Pair<User, Integer> > findUsersWithRequest(int specialtyId) throws SQLException {
        return specialtyDAO.findUsersWithRequest(specialtyId);
    }

    @Override
    public List<User> findFacultyUsers(int facultyId) throws SQLException {
        return specialtyDAO.findFacultyUsers(facultyId);
    }

}
