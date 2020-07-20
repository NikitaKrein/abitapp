package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class AdminRequestsButton implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void rejectUser(int userId, String message){
        try {
            userService.rejectUpdateUserSpecialty(userId, message);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
