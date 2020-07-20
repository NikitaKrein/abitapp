package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.SpecialtyService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditAdminSpecialtyInformation implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Specialty> specialties = (List<Specialty>) req.getSession().getAttribute("specialties");
        Specialty specialty = null;
        for (Specialty specialtyExample : specialties){
            if (specialtyExample.getId() == id) {
                specialty = specialtyExample;
                break;
            }
        }
        req.setAttribute("exams", Exam.values());
        req.setAttribute("specialty", specialty);
        return CommandName.EDIT_ADMIN_SPECIALTY_INFORMATION;
    }
}
