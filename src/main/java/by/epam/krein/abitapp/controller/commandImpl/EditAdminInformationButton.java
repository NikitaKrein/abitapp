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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditAdminInformationButton implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    AdminService adminService = serviceFactory.getAdminService();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();
    UniversityService universityService = serviceFactory.getUniversityService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        if (req.getParameter("button").equals("university")) {
            editUniversityInformation(req);
        }
        if (req.getParameter("button").equals("specialty")) {
            editSpecialtyInformation(req);
        }

        admin = adminService.findByEmail(admin.getEmail());
        HttpUtils.updateSession(req, "admin", admin);
        if (admin.getUniversity().isFaculty()) {
            UniversityUtils.setSpecialities(req, admin.getUniversity().getId());
        }
        return CommandName.EDIT_ADMIN_INFORMATION_BUTTON;
    }

    private void editUniversityInformation(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String information = req.getParameter("information");
        universityService.updateUniversity(name, information, id);
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
    }

}
