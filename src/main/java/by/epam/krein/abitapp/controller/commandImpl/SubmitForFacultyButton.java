package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UserService;
import by.epam.krein.abitapp.util.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SubmitForFacultyButton implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();


    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        int id = Integer.parseInt(req.getParameter("id"));
        int formOfTraining = Integer.parseInt(req.getParameter("formOfTraining"));

        try {
            userService.updateUserRequest(id, formOfTraining, user.getId());
            HttpUtils.updateSession(req, "user", userService.findUserById(user.getId()));
        } catch (RuntimeException exception) {
            throw new CommandException("Submit for faculty button failed ", exception);
        }

        return CommandName.PROFILE;
    }

}
