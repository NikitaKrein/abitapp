package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.University;
import by.epam.krein.abitapp.exception.CommandException;
import by.epam.krein.abitapp.service.SpecialtyService;
import by.epam.krein.abitapp.service.ServiceFactory;
import by.epam.krein.abitapp.service.UniversityService;
import by.epam.krein.abitapp.util.UniversityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class UniversityCatalog implements Command {

    private final Logger logger = LoggerFactory.getLogger(UniversityCatalog.class);

    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    UniversityService universityService = serviceFactory.getUniversityService();

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) throws CommandException{
        String categoryId = UniversityUtils.getId(req);
        List<University> universities;

        if(UniversityUtils.checkId(categoryId)){
            logger.info("Failed, incorrect id in path.");
            return CommandName.ERROR.getCommand().callCommandMethod(req);
        }

        try {
            universities = getCategoryList(req, Integer.parseInt(categoryId));
        } catch (RuntimeException exception) {
            throw new CommandException("University catalog failed ", exception);
        }

        req.setAttribute("universities", universities);
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
