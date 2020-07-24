package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AdminRequestsButton implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {

        String action  = req.getParameter("action");
        String[] subAction = action.split(" ");
        action = subAction[0];
        int userId = Integer.parseInt(subAction[1]);
        int facultyId = Integer.parseInt(subAction[2]);

        if (action.equals("accept")){
            acceptedUser(userId, facultyId);
        }
        if (action.equals("reject")){
            String message = req.getParameter("adminMessage");
            rejectUser(userId, message);
        }
        return CommandName.ADMIN_REQUESTS_BUTTON;
    }

    private void acceptedUser(int userId, int facultyId){
        try {
            userService.acceptedUpdateUserSpecialty(userId, facultyId);
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }
    }

    private void rejectUser(int userId, String message){
        try {
            userService.rejectUpdateUserSpecialty(userId, message);
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }
    }
}
