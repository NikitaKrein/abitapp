package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import org.apache.commons.lang3.tuple.Pair;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AdminRequests implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        List<Specialty> specialties = (List<Specialty>) req.getSession().getAttribute("specialties");
        List<List<Pair<User, Integer>>> usersWithRequests;
        try {
            usersWithRequests = getSpecialtiesWithRequests(specialties);
        } catch (RuntimeException exception) {
            throw new CommandException("Admin requests failed ", exception);
        }
        req.setAttribute("usersWithRequests", usersWithRequests);
        return CommandName.ADMIN_REQUESTS;
    }


    private List<List<Pair<User, Integer>>> getSpecialtiesWithRequests(List<Specialty> specialties) {
        List<List<Pair<User, Integer>>> specialtiesWithRequests = new ArrayList<>();
        List<Pair<User, Integer>> specialtyUsersWithRequests;
        for (Specialty specialty : specialties) {
            specialtyUsersWithRequests = specialtyService.findUsersWithRequest(specialty.getId());
            specialtiesWithRequests.add(specialtyUsersWithRequests);
        }
        return specialtiesWithRequests;
    }
}
