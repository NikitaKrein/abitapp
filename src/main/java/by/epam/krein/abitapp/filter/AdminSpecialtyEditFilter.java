package by.epam.krein.abitapp.filter;

import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.controller.commandImpl.FacultyPage;
import by.epam.krein.abitapp.entity.Specialty;
import by.epam.krein.abitapp.util.UniversityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminSpecialtyEditFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(AdminSpecialtyEditFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        List<Specialty> specialties = (List<Specialty>) session.getAttribute("specialties");

        String prepareId = req.getParameter("id");
        if(UniversityUtils.checkId(prepareId)){
            logger.info("Failed, incorrect id in req.");
            resp.sendRedirect(req.getContextPath() + CommandName.ERROR.getJspAddress());
            return;
        }

        int id = Integer.parseInt(prepareId);
        boolean flag = true;
        for (Specialty specialty : specialties){
            if (specialty.getId() == id){
                flag = false;
                break;
            }
        }
        if(flag){
            resp.sendRedirect(req.getContextPath() + "/admin/edit");
            return;
        }
        filterChain.doFilter(req, resp);
    }

}
