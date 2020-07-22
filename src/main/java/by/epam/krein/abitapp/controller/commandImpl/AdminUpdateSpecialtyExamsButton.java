package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.entity.Exam;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminUpdateSpecialtyExamsButton implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    SpecialtyService specialtyService = serviceFactory.getSpecialtyService();
    //private final SpecialtyService facultyService = new SpecialtyServiceImpl(); // fabrika potom

    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        Specialty specialty = null;
        try {
            specialty = specialtyService.findById(id);
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }

        List<Specialty> specialties = null;
        String action  = req.getParameter("action");
        String exam = null;
        if (action.contains("delete")){
            exam = action.substring(7);
            action = "delete";
        }
        switch (action){
            case "update":{
                updateExams(req, specialty);
                break;
            }
            case "delete":{
                deleteExam(exam, req, specialty);
                break;
            }
            case "add":{
                addExam(req, specialty);
                break;
            }
        }
        try {
            specialties = specialtyService.findSpecialtiesByFacultyId(admin.getUniversity().getId());
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }
        req.getSession().setAttribute("specialties", specialties);
        return CommandName.ADMIN_EDIT_SPECIALTY_EXAM_BUTTON;
    }

    private void updateExams(HttpServletRequest req, Specialty specialty){
        List<Exam> nameOfExams = new ArrayList<>();
        for(Exam exam : specialty.getNameOfExams()){
            Exam newExam = Exam.valueOf(req.getParameter("Exam" + exam));
            if(exam != newExam){
                try {
                    specialtyService.updateSpecialtyExam(specialty.getId(), exam.toString(), newExam.toString());
                } catch(RuntimeException exception){
                    throw new CommandException("message", exception);
                }
            }
        }
    }

    private void deleteExam(String exam, HttpServletRequest req, Specialty specialty){
        try {
            specialtyService.deleteSpecialtyExam(specialty.getId(), exam);
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }
    }

    private void addExam(HttpServletRequest req, Specialty specialty){
        String newExam = req.getParameter("newExam");
        try {
            specialtyService.createSpecialtyExam(specialty.getId(), newExam);
        } catch(RuntimeException exception){
            throw new CommandException("message", exception);
        }
    }

}
