package by.epam.krein.abitapp.util;

import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.SpecialtyService;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class UniversityUtils {

    private static final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private static final SpecialtyService specialtyService = serviceFactory.getSpecialtyService();


    public static List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> getFacultyRating(List<Specialty> specialties) {
        List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> rating = new ArrayList<>();
        for (Specialty specialty : specialties) {
            for (int formOfTraining = 1; formOfTraining < 5; formOfTraining++) {
                rating.add(Pair.of(specialty.getId(), Pair.of(formOfTraining, specialtyService.getSpecialtyRating(specialty.getId(), formOfTraining))));
            }
        }
        return rating;
    }

    public static String getId(HttpServletRequest req) {
        if (req.getPathInfo() == null) {
            return "0";
        } else {
            StringBuilder prepareId = new StringBuilder(req.getPathInfo());
            prepareId.deleteCharAt(0);
            return prepareId.toString();
        }
    }

    public static boolean checkId(String prepareCategoryId){
        return !prepareCategoryId.toString().matches("\\d*");
    }

    public static void setSpecialities(HttpServletRequest req, int facultyId) {
        List<Specialty> specialties = specialtyService.findSpecialtiesByFacultyId(facultyId);
        HttpUtils.updateSession(req, "specialties", specialties);
    }
}
