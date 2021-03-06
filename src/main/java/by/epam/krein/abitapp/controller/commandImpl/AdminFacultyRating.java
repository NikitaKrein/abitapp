package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.UniversityService;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


public class AdminFacultyRating implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        Admin admin = (Admin) req.getSession().getAttribute("admin");

        List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> rating;
        List<Specialty> specialties = (List<Specialty>) req.getSession().getAttribute("specialties");
        
        try {
            rating = getFacultyRating(specialties);
        } catch (RuntimeException exception) {
            throw new CommandException("Admin faculty rating failed ", exception);
        }

        req.setAttribute("rating", rating);
        return CommandName.ADMIN_RATING;
    }

    private List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> getFacultyRating(List<Specialty> specialties) {
        List<Pair<Integer, Pair<Integer, List<Pair<User, Integer>>>>> rating = new ArrayList<>();
        for (Specialty specialty : specialties) {
            for (int i = 1; i < 5; i++) {
                rating.add(Pair.of(specialty.getId(), Pair.of(i, specialtyService.getSpecialtyRating(specialty.getId(), i))));
            }
        }
        return rating;
    }
}
