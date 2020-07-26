package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.University;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UniversityService;
import by.epam.krein.abitapp.util.UniversityUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FacultyPage implements Command {

    private final Logger logger = LoggerFactory.getLogger(FacultyPage.class);


    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();
    UniversityService universityService = serviceFactory.getUniversityService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        University faculty;
        List<Specialty> prepareSpecialties;
        List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> rating;
        List<Pair<Specialty, Boolean>> specialties;
        String prepareId = UniversityUtils.getId(req);

        if(UniversityUtils.checkId(prepareId)){
            logger.info("Failed, incorrect id in path.");
            return CommandName.ERROR.getCommand().callCommandMethod(req);
        }

        int id = Integer.parseInt(prepareId);

        try {
            faculty = universityService.findUniversityById(id);
            prepareSpecialties = specialtyService.findSpecialtiesByFacultyId(id);
            rating = UniversityUtils.getFacultyRating(prepareSpecialties);
        } catch (RuntimeException exception) {
            throw new CommandException("Faculty Page failed", exception);
        }

        specialties = getSpecialtiesWithSubmitVerdict(req, prepareSpecialties);
        req.setAttribute("rating", rating);
        req.setAttribute("specialties", specialties);
        req.setAttribute("faculty", faculty);
        return CommandName.FACULTY_PAGE;
    }


    private List<Pair<Specialty, Boolean>> getSpecialtiesWithSubmitVerdict(HttpServletRequest req, List<Specialty> prepareSpecialties){
        List<Pair<Specialty, Boolean>> specialties = new ArrayList<>();
        for (Specialty specialty : prepareSpecialties) {
            if (canSubmitFaculty(req, specialty)) {
                specialties.add(Pair.of(specialty, true));
            } else {
                specialties.add(Pair.of(specialty, false));
            }
        }
        return specialties;
    }

    private boolean canSubmitFaculty(HttpServletRequest req, Specialty specialty) {
        User user = checkUser(req);
        if (user != null) {
            for (Exam facultyExam : specialty.getNameOfExams()) {
                boolean canSubmit = false;
                for (Map.Entry<Exam, Integer> userExam : user.getExamMarks().entrySet()) {
                    if (userExam.getKey() == facultyExam) {
                        canSubmit = true;
                        break;
                    }
                }
                if (!canSubmit) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private User checkUser(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        if (req.getSession().getAttribute("user") != null) {
            return user;
        }
        return null;
    }
}
