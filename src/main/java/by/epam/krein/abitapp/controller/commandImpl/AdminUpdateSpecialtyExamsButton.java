package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.util.UniversityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class AdminUpdateSpecialtyExamsButton implements Command {

    private final Logger logger = LoggerFactory.getLogger(AdminUpdateSpecialtyExamsButton.class);

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();
    private String exam = "";

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        String prepareId = req.getParameter("id");
        if (UniversityUtils.checkId(prepareId)) { // Никогда не сработает, есть фильтр, хз, что лучше
            logger.info("Failed, incorrect id");
            return CommandName.ERROR.getCommand().callCommandMethod(req);
        }
        int id = Integer.parseInt(prepareId);

        Admin admin = (Admin) req.getSession().getAttribute("admin");

        String action = req.getParameter("action");
        action = deleteChecker(action);

        try {
            switch (action) {
                case "update": {
                    updateExams(req, specialtyService.findById(id));
                    break;
                }
                case "delete": {
                    deleteExam(exam, req, specialtyService.findById(id));
                    break;
                }
                case "add": {
                    addExam(req, specialtyService.findById(id));
                    break;
                }
            }
            UniversityUtils.setSpecialities(req, admin.getUniversity().getId());
        } catch (RuntimeException exception) {
            throw new CommandException("AdminUpdateSpecialtyExamsButton failed ", exception);
        }
        return CommandName.ADMIN_EDIT_SPECIALTY_EXAM_BUTTON;
    }

    private void updateExams(HttpServletRequest req, Specialty specialty) {
        List<Exam> nameOfExams = new ArrayList<>();
        for (Exam exam : specialty.getNameOfExams()) {
            Exam newExam = Exam.valueOf(req.getParameter("Exam" + exam));
            if (exam != newExam) {
                specialtyService.updateSpecialtyExam(specialty.getId(), exam.toString(), newExam.toString());
                logger.info("Replace " +  exam + " with " + newExam);
            }
        }
    }

    private void deleteExam(String exam, HttpServletRequest req, Specialty specialty) {
        specialtyService.deleteSpecialtyExam(specialty.getId(), getExam());
        logger.info("Delete in " + specialty.getName()  + " " + getExam() + " exam.");
    }

    private void addExam(HttpServletRequest req, Specialty specialty) {
        String newExam = req.getParameter("newExam");
        specialtyService.createSpecialtyExam(specialty.getId(), newExam);
        logger.info("Add in " + specialty.getName() +  " " + getExam() + " exam.");
    }

    private String deleteChecker(String action) {
        if (action.contains("delete")) {
            setExam(action.substring(7));
            return "delete";
        }
        return action;
    }

}
