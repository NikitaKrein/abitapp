package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;

import javax.servlet.http.HttpServletRequest;

public class SignOutButton implements Command {
    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        req.getSession().invalidate();
        return CommandName.MAIN_PAGE;
    }
}
