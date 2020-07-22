package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.University;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UniversityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class UniversityCatalog implements Command  {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UniversityService universityService = serviceFactory.getUniversityService();
    SpecialtyService facultyService = serviceFactory.getSpecialtyService();

    //private final UniversityService universityService = new UniversityServiceImpl(); // fabrika potom
    //private final SpecialtyService facultyService = new SpecialtyServiceImpl();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {
        List<University> universities = null;
        int id;

        if(req.getPathInfo() == null){
            id = 0;
        }
        else{
            StringBuilder prepareId = new StringBuilder(req.getPathInfo());
            prepareId.deleteCharAt(0);
            //prepareId.toString().matches("^/+\\d*")
            id = Integer.parseInt(prepareId.toString());
        }

        if(id == 0){
            try {
                universities = universityService.findMain();
            } catch(RuntimeException exception){
                throw new CommandException("message", exception);
            }
        }
        else{
            try {
                universities = universityService.findByParentId(id);
            }catch(RuntimeException exception){
                throw new CommandException("message", exception);
            }
        }
        req.setAttribute("universities", universities);
        return CommandName.UNIVERSITY_CATALOG;
    }
}
