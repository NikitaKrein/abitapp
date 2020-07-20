package by.epam.krein.abitapp.service;

import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.SQLException;
import java.util.List;

public interface SpecialtyService {
    public Specialty findById(int id) throws SQLException;
    public List<Pair<User, Integer>> getSpecialtyRating(int specialtyId, int formOfTraining) throws SQLException;
    public Specialty findFacultyByUniversityId(int id) throws SQLException;
    public List<Specialty> findSpecialtiesByFacultyId(int facultyId) throws SQLException;
    public void updateSpecialty(int id, String name, String information, int admissionPlanForFree, int admissionPlanForPaid, int admissionPlanForCorrespondenceCourseForFree, int admissionPlanForCorrespondenceCourseForPaid) throws SQLException;
    public void updateSpecialtyExam(int facultyId, String exam, String newExam) throws SQLException;
    public void deleteSpecialtyExam(int facultyId, String exam) throws SQLException;
    public void createSpecialtyExam(int facultyId, String exam) throws SQLException;
    public List<Pair<User, Integer> > findUsersWithRequest(int specialtyId) throws SQLException;
    public List<User> findFacultyUsers(int facultyId) throws SQLException;
}
