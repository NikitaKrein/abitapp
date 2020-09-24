package by.epam.krein.abitapp.controller;

import by.epam.krein.abitapp.exception.CommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ControllerServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(ControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        callCommand(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        callCommand(req, resp);
    }

    public void callCommand(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //CommandProvider commandProvider = new CommandProvider();
        //Command command = commandProvider.getCommand(commandName.toString());
        CommandName commandName = getCommandName();
        Command command = commandName.getCommand();
        CommandName nextCommand = null;
        try {
            nextCommand = command.callCommandMethod(req);
        } catch (CommandException exception) {
            logger.error("Failed something in " + commandName.toString(), exception);
            resp.sendRedirect(req.getContextPath() + "/error");
            return;
        }

        if (commandName.toString().matches(".+BUTTON")) {
            resp.sendRedirect(req.getContextPath() + nextCommand.getJspAddress());
        } else {
            req.getRequestDispatcher(nextCommand.getJspAddress()).forward(req, resp);
        }
    }

    private CommandName getCommandName() {
        String commandName = getServletConfig().getInitParameter("command").toUpperCase().replace(" ", "_");
        //String commandName = req.getParameter(parameter).toUpperCase().replace(" ", "_");
        return CommandName.valueOf(commandName);
    }

}
