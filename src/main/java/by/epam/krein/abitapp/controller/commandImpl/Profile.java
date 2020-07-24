package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UserService;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Profile<T> implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();

//    private final UserService userService = new UserServiceImpl();
//    private final SpecialtyService facultyService = new SpecialtyServiceImpl(); // fabrika potom

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {

        User user = null;
        try {
            user = updateSession(req);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (req.getPathInfo() == null) {
            return CommandName.PROFILE;
        }

        switch (req.getPathInfo()) {
            case ("/personalInfo"): {
                return CommandName.PERSONAL_INFO;
            }
            case ("/requests"): {
                return CommandName.REQUESTS;
            }
            case ("/rating"): {
                int userRating = -1;
                int facultyId = user.getSpecialty().getUniversity().getId();
                List<Specialty> specialties = null;
                List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> rating = new ArrayList<>();
                int sumOfMarks = 0;
                try {
                    specialties = specialtyService.findSpecialtiesByFacultyId(facultyId);
                    for (Specialty specialty : specialties) {
                        for (int formOfTraining = 1; formOfTraining < 5; formOfTraining++) {
                            rating.add(Pair.of(specialty.getId(), Pair.of(formOfTraining, specialtyService.getSpecialtyRating(specialty.getId(), formOfTraining))));
                            if (specialty.getId() == user.getSpecialty().getId() && formOfTraining == user.getFormOfTraining()) {
                                sumOfMarks = 0;
                                for (Exam facultyExam : specialty.getNameOfExams()) {
                                    for (Map.Entry<Exam, Integer> userExam : user.getExamMarks().entrySet()) {
                                        sumOfMarks += facultyExam == userExam.getKey() ? userExam.getValue() : 0;
                                    }
                                }
                                userRating = binarySearch(rating.get(rating.size() - 1).getValue().getValue(), sumOfMarks) + 1;
                            }
                        }
                    }
                } catch(RuntimeException exception){
                    throw new CommandException("message", exception);
                }

                req.setAttribute("sumOfMarks", sumOfMarks);
                req.setAttribute("userRating", userRating);
                req.setAttribute("rating", rating);
                req.setAttribute("specialties", specialties);
                return CommandName.RATING;
            }
            case ("/edit"): {
                return CommandName.EDIT_PROFILE;
            }
            default: {
                return CommandName.PROFILE;
            }
        }
    }

    private User updateSession(HttpServletRequest req) throws SQLException {
        User user = (User) req.getSession().getAttribute("user");
        user = userService.findUserById(user.getId());
        req.getSession().setAttribute("user", user);
        return user;
    }

    private int binarySearch(List<Pair<User, Integer>> collection, int value) {
        int left = 0, right = collection.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;

            if (collection.get(mid).getValue() > value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
