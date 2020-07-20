package by.epam.krein.abitapp.controller;

import by.epam.krein.abitapp.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface Command {
    CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) throws CommandException;
}
