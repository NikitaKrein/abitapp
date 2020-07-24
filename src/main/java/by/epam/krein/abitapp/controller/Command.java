package by.epam.krein.abitapp.controller;

import by.epam.krein.abitapp.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandName callCommandMethod(HttpServletRequest req) throws CommandException;
}
