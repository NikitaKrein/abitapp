package by.epam.krein.abitapp.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/languageButton")
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
        String URI = req.getHeader("Referer");
        resp.sendRedirect(URI);
    }

    @Override
    public void destroy() {

    }
}
