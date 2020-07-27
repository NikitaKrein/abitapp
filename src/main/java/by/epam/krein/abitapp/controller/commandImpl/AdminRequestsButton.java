package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminRequestsButton implements Command {

    private final Logger logger = LoggerFactory.getLogger(AdminRequestsButton.class);

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        String action = req.getParameter("action");
        String[] subAction = action.split(" ");
        action = subAction[0];
        int userId = Integer.parseInt(subAction[1]);
        int facultyId = Integer.parseInt(subAction[2]);

        try {
            processRequest(req, action, userId, facultyId);
        } catch (RuntimeException exception) {
            throw new CommandException("Admin request button failed ", exception);
        }

        return CommandName.ADMIN_REQUESTS_BUTTON;
    }

    private void acceptedUser(int userId, int facultyId) {
        userService.acceptedUpdateUserSpecialty(userId, facultyId);
        logger.info("Accepted user " + userService.findUserById(userId).getEmail());
    }

    private void rejectUser(int userId, String message) {
        userService.rejectUpdateUserSpecialty(userId, message);
        logger.info("Reject user " + userService.findUserById(userId).getEmail() + ", message " + message);
    }

    private void processRequest(HttpServletRequest req, String action, int userId, int facultyId){
        if (action.equals("accept")) {
            acceptedUser(userId, facultyId);
        }
        if (action.equals("reject")) {
            String message = req.getParameter("adminMessage");
            rejectUser(userId, message);
        }
    }
}
