package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class SignOutButton implements Command {
    private final Logger logger = LoggerFactory.getLogger(SignOutButton.class);

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("user");
        logger.info(user.getEmail() + " sign out");
        req.getSession().invalidate();
        return CommandName.MAIN_PAGE;
    }
}
