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
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FacultyPage implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();
    UniversityService universityService = serviceFactory.getUniversityService();

   // private final SpecialtyService facultyService = new SpecialtyServiceImpl(); // fabrika potom

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        University faculty = null;
        List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> rating = new ArrayList<>();
        List<Pair<Specialty, Boolean> > specialties = new ArrayList<>();
        List<Specialty> prepareSpecialties = null;
        int id;
        StringBuilder prepareId = new StringBuilder(req.getPathInfo());
        prepareId.deleteCharAt(0);
        //prepareId.toString().matches("^/+\\d*")
        id = Integer.parseInt(prepareId.toString());
        try {
            faculty = universityService.findUniversityById(id);
            prepareSpecialties = specialtyService.findSpecialtiesByFacultyId(id);
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }
        for (Specialty specialty : prepareSpecialties){
            for (int i = 1; i < 5; i++){
                try {
                    rating.add(Pair.of(specialty.getId(), Pair.of(i, specialtyService.getSpecialtyRating(specialty.getId(), i))));
                } catch(RuntimeException exception){
                    throw new CommandException("message", exception);
                }
            }
            if(canSubmitFaculty(req, specialty)){
                specialties.add(Pair.of(specialty, true));
            }
            else{
                specialties.add(Pair.of(specialty, false));
            }
        }
        req.setAttribute("rating", rating);
        req.setAttribute("specialties", specialties);
        req.setAttribute("faculty", faculty);
        return CommandName.FACULTY_PAGE;
    }
    private boolean canSubmitFaculty(HttpServletRequest req, Specialty specialty){
        User user = checkUser(req);
        if(user != null){
            for (Exam facultyExam: specialty.getNameOfExams()){
                boolean canSubmit = false;
                for (Map.Entry<Exam, Integer> userExam : user.getExamMarks().entrySet()){
                    if(userExam.getKey() == facultyExam){
                        canSubmit = true;
                    }
                }
                if(!canSubmit){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    private User checkUser(HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("user");
        if (req.getSession().getAttribute("user") != null) {
            return user;
        }
        return null;
    }
}
