package by.epam.krein.abitapp.util;

import by.epam.krein.abitapp.entity.User;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {

    public static<T> void updateSession(HttpServletRequest req, String sessionAttributeName, T sessionAttribute){
        req.getSession().setAttribute(sessionAttributeName, sessionAttribute);
    }

    public static boolean isMethodPost(HttpServletRequest req) {
        return req.getMethod().equalsIgnoreCase("post");
    }

    public static boolean isMethodGet(HttpServletRequest req) {
        return req.getMethod().equalsIgnoreCase("get");
    }

    //public static boolean
}
