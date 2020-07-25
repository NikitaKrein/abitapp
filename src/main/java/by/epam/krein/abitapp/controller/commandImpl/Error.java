package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.controller.ControllerServlet;
import by.epam.krein.abitapp.exception.CommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class Error implements Command {

    private final Logger logger = LoggerFactory.getLogger(Error.class);

    @Override
    public CommandName callCommandMethod(HttpServletRequest req) throws CommandException {
        return CommandName.ERROR;
    }
}
