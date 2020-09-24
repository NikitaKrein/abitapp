package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Exam;
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
import java.util.HashMap;
import java.util.Map;

public class SignUpButton implements Command {

    private final Logger logger = LoggerFactory.getLogger(SignUpButton.class);

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    SecurityService securityService = serviceFactory.getSecurityService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        if (HttpUtils.isMethodGet(req)) {
            logger.info("Failed, call method get in signUp page.");
            return CommandName.ERROR.getCommand().callCommandMethod(req);
        }
        if(!PasswordValidator.validatePassword(req)){
            logger.info("Failed signUp, passwords didn't match. (Email: " + req.getParameter("email") + ")");
            req.setAttribute("wrongInformation", true);
            return CommandName.SIGN_UP_BUTTON;
        }
        User user = createUser(req);
        try {
            userService.signUp(user);
            HttpUtils.updateSession(req, "user", userService.findUserById(user.getId()));
        } catch (RuntimeException exception) {
            throw new CommandException("Sign up failed ", exception);
        }
        logger.info(user.getEmail() + " sign up");
        return CommandName.PROFILE;
    }

    private User createUser(HttpServletRequest req) {
        User user = new User();
        user.setSurname(req.getParameter("surname"));
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        char[] password = securityService.createPassword(req.getParameter("password"));
        user.setPassword(password);
        Map<Exam, Integer> examMark = new HashMap<>();
        examMark.put(Exam.valueOf(req.getParameter("certificate").replace(" ", "_").toUpperCase()), Integer.parseInt(req.getParameter("firstMark")));
        examMark.put(Exam.valueOf(req.getParameter("secondExam").replace(" ", "_").toUpperCase()), Integer.parseInt(req.getParameter("secondMark")));
        examMark.put(Exam.valueOf(req.getParameter("thirdExam").replace(" ", "_").toUpperCase()), Integer.parseInt(req.getParameter("thirdMark")));
        examMark.put(Exam.valueOf(req.getParameter("fourthExam").replace(" ", "_").toUpperCase()), Integer.parseInt(req.getParameter("fourthMark")));
        examMark.put(Exam.valueOf(req.getParameter("fifthExam").replace(" ", "_").toUpperCase()), Integer.parseInt(req.getParameter("fifthMark")));

        user.setExamMarks(examMark);
        return user;
    }
}
