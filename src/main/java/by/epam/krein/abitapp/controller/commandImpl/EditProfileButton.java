package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SecurityService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UserService;
import by.epam.krein.abitapp.util.HttpUtils;
import by.epam.krein.abitapp.util.PasswordValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class EditProfileButton implements Command {

    private final Logger logger = LoggerFactory.getLogger(EditProfileButton.class);

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    SecurityService securityService = serviceFactory.getSecurityService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        if (HttpUtils.isMethodGet(req)) {
            logger.info("Failed, call method get in edit profile page.");
            return CommandName.ERROR.getCommand().callCommandMethod(req);
        }
        User user = (User) req.getSession().getAttribute("user");
        if (!req.getParameter("email").equals("")) {
            user.setEmail(req.getParameter("email"));
        }
        if (!req.getParameter("password").equals("")) {
            if(!PasswordValidator.validatePassword(req)){
                logger.info("Failed edit password, passwords didn't match. (Email: " + req.getParameter("email") + ")");
            }else {
                char[] password = securityService.createPassword(req.getParameter("password"));
                user.setPassword(password);
            }
        }
        try {
            userService.updateUser(user);
        } catch (RuntimeException exception) {
            throw new CommandException("Edit profile failed ", exception);
        }
        HttpUtils.updateSession(req, "user", user);
        return CommandName.PROFILE;
    }
}
