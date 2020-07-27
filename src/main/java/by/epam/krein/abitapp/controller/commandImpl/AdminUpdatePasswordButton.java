package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.AdminService;
import by.epam.krein.abitapp.service.SecurityService;
import by.epam.krein.abitapp.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class AdminUpdatePasswordButton implements Command {

    private final Logger logger = LoggerFactory.getLogger(AdminUpdatePasswordButton.class);

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    AdminService adminService = serviceFactory.getAdminService();
    SecurityService securityService =serviceFactory.getSecurityService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        int id = admin.getId();
        char[] password = securityService.createPassword(req.getParameter("password"));
        try {
            adminService.updatePassword(id, password);
        } catch(RuntimeException exception){
            throw new CommandException("Failed admin update password", exception);
        }
        logger.info(admin.getEmail() + " update password");
        return CommandName.ADMIN_UPDATE_PASSWORD_BUTTON;
    }

}
