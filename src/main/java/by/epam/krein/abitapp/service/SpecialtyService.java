package by.epam.krein.abitapp.service;

import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.ServiceException;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.SQLException;
import java.util.List;

public interface SpecialtyService {
    public Specialty findById(int id) throws ServiceException;
    public List<Pair<User, Integer>> getSpecialtyRating(int specialtyId, int formOfTraining) throws ServiceException;
    public Specialty findFacultyByUniversityId(int id) throws ServiceException;
    public List<Specialty> findSpecialtiesByFacultyId(int facultyId) throws ServiceException;
    public void updateSpecialty(int id, String name, String information, int admissionPlanForFree, int admissionPlanForPaid, int admissionPlanForCorrespondenceCourseForFree, int admissionPlanForCorrespondenceCourseForPaid) throws ServiceException;
    public void updateSpecialtyExam(int facultyId, String exam, String newExam) throws ServiceException;
    public void deleteSpecialtyExam(int facultyId, String exam) throws ServiceException;
    public void createSpecialtyExam(int facultyId, String exam) throws ServiceException;
    public List<Pair<User, Integer> > findUsersWithRequest(int specialtyId) throws ServiceException;
    public List<User> findFacultyUsers(int facultyId) throws ServiceException;
}
