package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class SubmitForFacultyButton implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();

    //private final UserService userService = new UserServiceImpl(); // fabrika potom
    //private final SpecialtyService facultyService = new SpecialtyServiceImpl(); // fa


    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        int id = Integer.parseInt(req.getParameter("id"));
        int formOfTraining = Integer.parseInt(req.getParameter("formOfTraining"));

        try {
            userService.updateUserRequest(id, formOfTraining, user.getId());
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }

        try {
            user = userService.findUserById(user.getId());
            req.getSession().setAttribute("user", user);
            return CommandName.PROFILE;
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }
    }
}
