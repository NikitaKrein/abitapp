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
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRequests implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();

    //private final SpecialtyService facultyService = new SpecialtyServiceImpl(); // fabrika potom

    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        List<Specialty> specialties = (List<Specialty>) req.getSession().getAttribute("specialties");
        List<Pair<User, Integer> > specialtyUsersWithRequests = null;
        List<List<Pair<User, Integer> > > usersWithRequests = new ArrayList<>();
        for(Specialty specialty : specialties){
            try {
                specialtyUsersWithRequests = specialtyService.findUsersWithRequest(specialty.getId());
                usersWithRequests.add(specialtyUsersWithRequests);
            } catch(RuntimeException exception){
                throw new CommandException("message", exception);
            }
        }

        req.setAttribute("usersWithRequests", usersWithRequests);
        return CommandName.ADMIN_REQUESTS;
    }

}
