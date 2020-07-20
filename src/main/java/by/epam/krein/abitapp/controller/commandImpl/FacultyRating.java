package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FacultyRating implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SpecialtyService facultyService = serviceFactory.getSpecialtyService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {
        Specialty specialty = (Specialty) req.getSession().getAttribute("faculty");
        List<Pair<User, Integer>> usersRating = new ArrayList<>();
        //Pair<User, Integer> w = Pair.of()
        //Map<User, Integer> usersRating = new HashMap<>();
        List<User> users = null;
        try {
            users = facultyService.findFacultyUsers(specialty.getId());
            for (User user : users){
                int sumOfMarks = 0;
                for (Exam facultyExam : specialty.getNameOfExams()){
                    for (Map.Entry<Exam, Integer> userExam : user.getExamMarks().entrySet()){
                        sumOfMarks += facultyExam == userExam.getKey() ? userExam.getValue() : 0;
                    }
                }
                usersRating.add(Pair.of(user, sumOfMarks));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        req.setAttribute("usersRating", usersRating);
        return null;
    }
}
