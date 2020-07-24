package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.AdminService;
import by.epam.krein.abitapp.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminUpdatePasswordButton implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    AdminService adminService = serviceFactory.getAdminService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        try {
            String password = req.getParameter("password");
            int id = Integer.parseInt(req.getParameter("id"));
            adminService.updatePassword(id, password);
            return CommandName.ADMIN_UPDATE_PASSWORD_BUTTON;
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }
    }
}
