package by.epam.krein.abitapp.util;

import by.epam.krein.abitapp.entity.User;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {

    public static<T> void updateSession(HttpServletRequest req, String sessionAttributeName, T sessionAttribute){
        req.getSession().setAttribute(sessionAttributeName, sessionAttribute);
    }
}
