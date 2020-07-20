package by.epam.krein.abitapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/language")
public class LanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getParameter("language") != null) {
            String s = req.getParameter("language");
            Cookie cookie = new Cookie("language", req.getParameter("language"));
            resp.addCookie(cookie);
        }
        StringBuilder URI  = new StringBuilder(req.getRequestURI());
        int pos = URI.indexOf("language");
        URI.delete(pos, URI.length());
        resp.sendRedirect(URI.toString());
    }

    @Override
    public void destroy() {

    }
}
