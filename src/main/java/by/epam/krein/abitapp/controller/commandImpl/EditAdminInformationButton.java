package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.entity.University;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.AdminService;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UniversityService;
import by.epam.krein.abitapp.util.HttpUtils;
import by.epam.krein.abitapp.util.UniversityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditAdminInformationButton implements Command {

    private final Logger logger = LoggerFactory.getLogger(EditAdminInformationButton.class);

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    AdminService adminService = serviceFactory.getAdminService();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();
    UniversityService universityService = serviceFactory.getUniversityService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        if (HttpUtils.isMethodGet(req)) {
            logger.info("Failed, call method get in editAdminInformation page.");
            return CommandName.ERROR.getCommand().callCommandMethod(req);
        }
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        try {
            editInformation(req);
            admin = adminService.findByEmail(admin.getEmail());
            if (admin.getUniversity().isFaculty()) {
                UniversityUtils.setSpecialities(req, admin.getUniversity().getId());
            }
        } catch (RuntimeException exception) {
            throw new CommandException("Edit university/faculty information failed", exception);
        }

        HttpUtils.updateSession(req, "admin", admin);
        return CommandName.EDIT_ADMIN_INFORMATION_BUTTON;
    }

    private void editUniversityInformation(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String information = req.getParameter("information");
        universityService.updateUniversity(name, information, id);
        logger.info("Update " + name + " university information");
    }

    private void editSpecialtyInformation(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String information = req.getParameter("information");
        int admissionPlanForFree = Integer.parseInt(req.getParameter("admissionPlanForFree"));
        int admissionPlanForPaid = Integer.parseInt(req.getParameter("admissionPlanForPaid"));
        int admissionPlanForCorrespondenceCourseForFree = Integer.parseInt(req.getParameter("admissionPlanForCorrespondenceCourseForFree"));
        int admissionPlanForCorrespondenceCourseForPaid = Integer.parseInt(req.getParameter("admissionPlanForCorrespondenceCourseForPaid"));
        specialtyService.updateSpecialty(id, name, information, admissionPlanForFree, admissionPlanForPaid, admissionPlanForCorrespondenceCourseForFree, admissionPlanForCorrespondenceCourseForPaid);
        logger.info("Update" + name + " specialty information");
    }

    private void editInformation(HttpServletRequest req) {
        if (req.getParameter("button").equals("university")) {
            editUniversityInformation(req);
        }
        if (req.getParameter("button").equals("specialty")) {
            editSpecialtyInformation(req);
        }
    }
}
