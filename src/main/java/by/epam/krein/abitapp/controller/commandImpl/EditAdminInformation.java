package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Exam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditAdminInformation implements Command {
    @Override
    public CommandName callCommandMethod(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("exams", Exam.values());
        return CommandName.EDIT_ADMIN_INFORMATION;
    }
}
