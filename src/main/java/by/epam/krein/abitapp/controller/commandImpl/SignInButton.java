package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.*;
import by.epam.krein.abitapp.util.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SignInButton implements Command {

    private final Logger logger = LoggerFactory.getLogger(SignInButton.class);

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UserService userService = serviceFactory.getUserService();
    AdminService adminService = serviceFactory.getAdminService();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();
    SecurityService securityService = serviceFactory.getSecurityService();

//    private UserService userService = new UserServiceImpl(); // fabrika potom
//    private final AdminService adminService = new AdminServiceImpl();
//    private final SpecialtyService facultyService = new SpecialtyServiceImpl();

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        if (HttpUtils.isMethodGet(req)) {
            logger.info("Failed, call method get in signIn page.");
            return CommandName.ERROR.getCommand().callCommandMethod(req);
        }

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            if (signInUser(req, email, password)) {
                logger.info("User: " + email + " sign in.");
                return CommandName.PROFILE;
            }
            if (signInAdmin(req, email, password)) {
                logger.info("Admin: " + email + " sign in.");
                return CommandName.EDIT_ADMIN_INFORMATION_BUTTON;
            }
        } catch (RuntimeException exception) {
            throw new CommandException("Sign in failed ", exception);
        }
        
        logger.info("Invalid signIn information. (Email: " + req.getParameter("email") + ")");
        return CommandName.SIGN_IN_BUTTON;
    }

    private boolean signInUser(HttpServletRequest req, String email, String password) {
        User user = (User) userService.signIn(email);
        if (user != null && securityService.equalsPassword(password, user.getPassword())) {
            HttpUtils.updateSession(req, "user", user);
            return true;
        }
        return false;
    }

    private boolean signInAdmin(HttpServletRequest req, String email, String password) {
        Admin admin = adminService.findByEmail(email);
        if (admin != null && securityService.equalsPassword(password, admin.getPassword())) {
            HttpUtils.updateSession(req, "admin", admin);
            if (admin.getUniversity().isFaculty()) {
                setSpecialities(req, admin.getUniversity().getId());
            }
            return true;
        }
        return false;
    }

    private void setSpecialities(HttpServletRequest req, int facultyId) {
        List<Specialty> specialties = specialtyService.findSpecialtiesByFacultyId(facultyId);
        HttpUtils.updateSession(req, "specialties", specialties);
    }
}
