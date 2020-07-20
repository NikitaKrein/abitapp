package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainPage implements Command {
    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {

        return CommandName.MAIN_PAGE;
    }
}
