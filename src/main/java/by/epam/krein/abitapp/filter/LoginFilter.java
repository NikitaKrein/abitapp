package by.epam.krein.abitapp.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(false);

        if (session != null){
            if (session.getAttribute("user") != null){
                resp.sendRedirect(req.getContextPath() + "/profile");
                return;
            }

            if(session.getAttribute("admin") != null){
                resp.sendRedirect(req.getContextPath() + "/admin/profile");
                return;
            }
        }

        filterChain.doFilter(req, resp);
    }
}
