package by.epam.krein.abitapp.dao;

import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.SQLException;
import java.util.List;

public interface SpecialtyDAO {
    public void createFacultyWithExam(Specialty specialty) throws SQLException;
    public void createFaculty(Specialty specialty) throws SQLException;
    public void createFacultyExam(int facultyId, String exam) throws SQLException;
    public int findUniversityId (Specialty specialty) throws SQLException;
    public void updateSpecialtyInformation(int id, String name, String information, int admissionPlanForFree, int admissionPlanForPaid, int admissionPlanForCorrespondenceCourseForFree, int admissionPlanForCorrespondenceCourseForPaid) throws SQLException;
    public void delete(Specialty specialty) throws SQLException;
    public Specialty findByUniversityId(int id) throws SQLException;
    public Specialty findById(int id) throws SQLException;
    public List<Specialty> findByFacultyId(int facultyId) throws SQLException;
    public List<Exam> findSpecialtyExams(int id) throws SQLException;
    public void updateSpecialtyExam(int facultyId, String exam, String newExam) throws SQLException;
    public void deleteFacultyExam(int facultyId, String exam) throws SQLException;
    public List<Pair<User, Integer> > findUsersWithRequest(int specialtyId) throws SQLException;
    public List<User> findFacultyUsers(int facultyId) throws SQLException;
    public List<Pair<User, Integer>> getSpecialtyRating(int specialtyId, int formOfTraining) throws SQLException;
    }
