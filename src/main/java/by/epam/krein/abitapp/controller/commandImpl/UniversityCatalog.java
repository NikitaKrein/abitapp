package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.University;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UniversityService;
import by.epam.krein.abitapp.util.UniversityUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UniversityCatalog implements Command {

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UniversityService universityService = serviceFactory.getUniversityService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) throws CommandException{
        String categoryId = UniversityUtils.getId(req);
        if(UniversityUtils.checkId(categoryId)){
            return CommandName.ERROR.getCommand().callCommandMethod(req);
        }
        try {
            List<University> universities = getCategoryList(req, Integer.parseInt(categoryId));
            req.setAttribute("universities", universities);
        } catch (RuntimeException exception) {
            throw new CommandException("University catalog failed ", exception);
        }
        return CommandName.UNIVERSITY_CATALOG;
    }

    private List<University> getCategoryList(HttpServletRequest req, int catalogCategoryId) {
        if (catalogCategoryId == 0) {
            return universityService.findMain();
        }
        else {
            return universityService.findByParentId(catalogCategoryId);
        }
    }
}
