package by.epam.krein.abitapp.util;

import javax.servlet.http.HttpServletRequest;

public class PasswordValidator {
    public static boolean validatePassword(HttpServletRequest req) {
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeatPassword");
        return password.equals(repeatPassword);
    }
}
