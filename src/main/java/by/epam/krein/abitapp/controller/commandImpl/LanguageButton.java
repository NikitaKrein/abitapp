package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class LanguageButton implements Command {
    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        String url = req.getHeader("Referer");
        if (req.getParameter("language") != null) {
            String s = req.getParameter("language");
            Cookie cookie = new Cookie("language", req.getParameter("language"));
            //resp.addCookie(cookie);
        }
        return CommandName.LANGUAGE_BUTTON;
    }
}
