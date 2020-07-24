package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SecurityService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class SignUpButton implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    SecurityService securityService = serviceFactory.getSecurityService();

    //private final UserService userService = new UserServiceImpl(); // fabrika potom

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        String sessionAttribute = "user";
        try {
            if (req.getMethod().equalsIgnoreCase("POST")) {
                User user = new User();
                user.setSurname(req.getParameter("surname"));
                user.setName(req.getParameter("name"));
                user.setEmail(req.getParameter("email"));
                //user.setPassword(req.getParameter("password"));//sdelat' norm klass
                char[] password = securityService.createPassword(req.getParameter("password"));
                user.setPassword(password);
                Map<Exam, Integer> examMark = new HashMap<>();
                examMark.put(Exam.valueOf(req.getParameter("firstExam").replace(" ", "_").toUpperCase()), Integer.parseInt(req.getParameter("firstMark")));
                examMark.put(Exam.valueOf(req.getParameter("secondExam").replace(" ", "_").toUpperCase()), Integer.parseInt(req.getParameter("secondMark")));
                examMark.put(Exam.valueOf(req.getParameter("thirdExam").replace(" ", "_").toUpperCase()), Integer.parseInt(req.getParameter("thirdMark")));
                user.setExamMarks(examMark);
                userService.signUp(user);
                user = userService.signIn(user.getEmail());
                req.getSession().setAttribute("user", user);
                return CommandName.PROFILE;
            }
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }
        return CommandName.SIGN_UP_BUTTON;
    }
}
