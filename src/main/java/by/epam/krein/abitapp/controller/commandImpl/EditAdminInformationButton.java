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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class EditAdminInformationButton implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    AdminService adminService = serviceFactory.getAdminService();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();
    UniversityService universityService = serviceFactory.getUniversityService();

//    private final SpecialtyService facultyService = new SpecialtyServiceImpl(); // fabrika potom
//    private final UniversityService universityService = new UniversityServiceImpl();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        if (req.getParameter("button").equals("university")){
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String information = req.getParameter("information");
            try {
                universityService.updateUniversity(name, information, id);
            } catch(RuntimeException exception){
                throw new CommandException("message", exception);
            }
        }
        if(req.getParameter("button").equals("specialty")){
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String information = req.getParameter("information");
            int admissionPlanForFree = Integer.parseInt(req.getParameter("admissionPlanForFree"));
            int admissionPlanForPaid = Integer.parseInt(req.getParameter("admissionPlanForPaid"));
            int admissionPlanForCorrespondenceCourseForFree = Integer.parseInt(req.getParameter("admissionPlanForCorrespondenceCourseForFree"));
            int admissionPlanForCorrespondenceCourseForPaid = Integer.parseInt(req.getParameter("admissionPlanForCorrespondenceCourseForPaid"));
            try {
                specialtyService.updateSpecialty(id, name, information, admissionPlanForFree, admissionPlanForPaid, admissionPlanForCorrespondenceCourseForFree, admissionPlanForCorrespondenceCourseForPaid);
            } catch(RuntimeException exception){
                throw new CommandException("message", exception);
            }
        }
        admin = adminService.findByEmail(admin.getEmail());
        req.getSession().setAttribute("admin",admin);
        if (admin.getUniversity().isFaculty()){
            List<Specialty> specialties = null;
            try {
                specialties = specialtyService.findSpecialtiesByFacultyId(admin.getUniversity().getId());
            } catch(RuntimeException exception){
                throw new CommandException("message", exception);
            }
            req.getSession().setAttribute("specialties", specialties);
        }
        return CommandName.EDIT_ADMIN_INFORMATION_BUTTON;
    }
}
