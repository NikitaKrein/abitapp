package by.epam.krein.abitapp.filter;

import by.epam.krein.abitapp.entity.Admin;
import by.epam.krein.abitapp.entity.Specialty;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AdminSpecialtyEditFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);
        List<Specialty> specialties = (List<Specialty>) session.getAttribute("specialties");
        int id = Integer.parseInt(req.getParameter("id"));
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
