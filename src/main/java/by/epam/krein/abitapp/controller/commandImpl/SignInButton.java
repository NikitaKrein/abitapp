package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.User;
import by.epam.krein.abitapp.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SignInButton implements Command {

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
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (req.getMethod().equalsIgnoreCase("POST")) {
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                User user = userService.signIn(email);
                if (user != null && securityService.equalsPassword(password, user.getPassword())) { // sdelat' norm proverky
                    req.getSession().setAttribute("user", user);
                    return CommandName.PROFILE;
                }
                else{
                    Admin admin = adminService.findByEmail(email);
                    if(admin != null && admin.getPassword().equals(password)){
                        req.getSession().setAttribute("admin", admin);
                        if (admin.getUniversity().isFaculty()){
                            List<Specialty> specialties = specialtyService.findSpecialtiesByFacultyId(admin.getUniversity().getId());
                            req.getSession().setAttribute("specialties", specialties);
                        }
                        return CommandName.EDIT_ADMIN_INFORMATION_BUTTON;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getSession().removeAttribute("user");
        return CommandName.SIGN_IN_BUTTON;
    }
}
