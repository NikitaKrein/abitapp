package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.util.UniversityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EditAdminSpecialtyInformation implements Command {

    private final Logger logger = LoggerFactory.getLogger(EditAdminSpecialtyInformation.class);

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        String prepareId = req.getParameter("id");
        if(UniversityUtils.checkId(prepareId)){ // Никогда не сработает, есть фильтр, хз, что лучше
            logger.info("Failed, incorrect id");
            return CommandName.ERROR.getCommand().callCommandMethod(req);
        }

        int id = Integer.parseInt(prepareId);
        List<Specialty> specialties = (List<Specialty>) req.getSession().getAttribute("specialties");
        Specialty specialty = findSpecialty(specialties, id);

        req.setAttribute("exams", Exam.values());
        req.setAttribute("specialty", specialty);
        return CommandName.EDIT_ADMIN_SPECIALTY_INFORMATION;
    }

    private Specialty findSpecialty(List<Specialty> specialties, int specialtyId) {
        for (Specialty specialty : specialties) {
            if (specialty.getId() == specialtyId) {
                return specialty;
            }
        }
        return null;
    }
}
