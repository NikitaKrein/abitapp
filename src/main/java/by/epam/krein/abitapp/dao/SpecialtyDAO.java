package by.epam.krein.abitapp.dao;

import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.DAOException;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.SQLException;
import java.util.List;

public interface SpecialtyDAO {
    public void createFacultyWithExam(Specialty specialty) throws DAOException;
    public void createFaculty(Specialty specialty) throws DAOException;
    public void createFacultyExam(int facultyId, String exam) throws DAOException;
    public int findUniversityId (Specialty specialty) throws DAOException;
    public void updateSpecialtyInformation(int id, String name, String information, int admissionPlanForFree, int admissionPlanForPaid, int admissionPlanForCorrespondenceCourseForFree, int admissionPlanForCorrespondenceCourseForPaid) throws DAOException;
    public void delete(Specialty specialty) throws DAOException;
    public Specialty findByUniversityId(int id) throws DAOException;
    public Specialty findById(int id) throws DAOException;
    public List<Specialty> findByFacultyId(int facultyId) throws DAOException;
    public List<Exam> findSpecialtyExams(int id) throws DAOException;
    public void updateSpecialtyExam(int facultyId, String exam, String newExam) throws DAOException;
    public void deleteFacultyExam(int facultyId, String exam) throws DAOException;
    public List<Pair<User, Integer> > findUsersWithRequest(int specialtyId) throws DAOException;
    public List<User> findFacultyUsers(int facultyId) throws DAOException;
    public List<Pair<User, Integer>> getSpecialtyRating(int specialtyId, int formOfTraining) throws DAOException;
    }
