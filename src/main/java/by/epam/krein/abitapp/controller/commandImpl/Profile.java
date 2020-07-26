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
import by.epam.krein.abitapp.util.HttpUtils;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Profile implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {

        User user = null;
        try {
            user = getUpdatedUser(req);
        } catch (RuntimeException exception) {
            throw new CommandException("Get updated user failed ", exception);
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
                List<Specialty> specialties = null;
                List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> rating = null;

                try {
                    specialties = specialtyService.findSpecialtiesByFacultyId(user.getSpecialty().getUniversity().getId());
                    rating = getFacultyRating(specialties);
                } catch(RuntimeException exception){
                    throw new CommandException("Profile rating failed", exception);
                }

                int userExamScore = getUserExamScore(user.getSpecialty(), user);
                int userRating = getUserRating(rating, user, userExamScore);

                req.setAttribute("userExamScore", userExamScore);
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

    private User getUpdatedUser(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        user = userService.findUserById(user.getId());
        HttpUtils.updateSession(req, "user", user);
        return user;
    }

    private int getUserExamScore(Specialty specialty, User user){
        int score = 0;
        for (Exam facultyExam : specialty.getNameOfExams()) {
            for (Map.Entry<Exam, Integer> userExam : user.getExamMarks().entrySet()) {
                score += facultyExam == userExam.getKey() ? userExam.getValue() : 0;
            }
        }
        return score;
    }

    private List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> getFacultyRating(List<Specialty> specialties){
        List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> rating = new ArrayList<>();
        for (Specialty specialty : specialties) {
            for (int formOfTraining = 1; formOfTraining < 5; formOfTraining++) {
                rating.add(Pair.of(specialty.getId(), Pair.of(formOfTraining, specialtyService.getSpecialtyRating(specialty.getId(), formOfTraining))));
            }
        }
        return rating;
    }

    private int getUserRating(List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> facultyRating, User user, int userExamScore){
        for (int index = 0; index < facultyRating.size(); index++) {
            if (facultyRating.get(index).getKey() == user.getSpecialty().getId() && facultyRating.get(index).getValue().getKey() == user.getFormOfTraining()){
                return getRatingUsingBinarySearch(facultyRating.get(facultyRating.size() - 1).getValue().getValue(), userExamScore) + 1;
            }
        }
        return -1;
    }

    private int getRatingUsingBinarySearch(List<Pair<User, Integer>> collection, int value) {
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
