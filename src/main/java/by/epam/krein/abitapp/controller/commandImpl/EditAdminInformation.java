package by.epam.krein.abitapp.controller.commandImpl;

import by.epam.krein.abitapp.controller.Command;
import by.epam.krein.abitapp.controller.CommandName;
import by.epam.krein.abitapp.entity.Exam;

import javax.servlet.http.HttpServletRequest;

public class EditAdminInformation implements Command {
    @Override
    public CommandName callCommandMethod(HttpServletRequest req) {
        req.setAttribute("exams", Exam.values());
        return CommandName.EDIT_ADMIN_INFORMATION;
    }
}
