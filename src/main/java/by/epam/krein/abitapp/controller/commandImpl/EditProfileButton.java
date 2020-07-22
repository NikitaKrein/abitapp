package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UserService;
import by.epam.krein.abitapp.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class EditProfileButton implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();

    //private final UserService userService = new UserServiceImpl(); // fabrika potom

    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getMethod().equalsIgnoreCase("POST")) {
                User user = (User) req.getSession().getAttribute("user");
                if (!req.getParameter("surname").equals("")) {
                    user.setSurname(req.getParameter("surname"));
                }
                if (!req.getParameter("name").equals("")) {
                    user.setName(req.getParameter("name"));
                }
                if (!req.getParameter("email").equals("")) {
                    user.setEmail(req.getParameter("email"));
                }
                if (!req.getParameter("password").equals("")) {
                    user.setPassword(req.getParameter("password").toCharArray());//sdelat' norm klass
                }

                userService.updateUser(user);
                HttpSession userSession = req.getSession();
                userSession.setAttribute("user", user);
                return CommandName.PROFILE;
            }
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }
        return CommandName.PROFILE;
    }
}
