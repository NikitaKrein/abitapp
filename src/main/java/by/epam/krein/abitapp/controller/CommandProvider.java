package by.epam.krein.abitapp.controller;

import by.epam.krein.abitapp.controller.commandImpl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandProvider {


    private final Map<CommandName, Command> repository = new HashMap<>();

    CommandProvider() {
        repository.put(CommandName.SIGN_IN_BUTTON, new SignInButton());
        repository.put(CommandName.SIGN_UP_BUTTON, new SignUpButton());
        repository.put(CommandName.PROFILE, new Profile());
        repository.put(CommandName.MAIN_PAGE, new MainPage());
        repository.put(CommandName.SIGN_OUT_BUTTON, new SignOutButton());
        repository.put(CommandName.UNIVERSITY_CATALOG, new UniversityCatalog());
        repository.put(CommandName.FACULTY_PAGE, new FacultyPage());
        repository.put(CommandName.SUBMIT_FOR_FACULTY_BUTTON, new SubmitForFacultyButton());
        repository.put(CommandName.EDIT_PROFILE_BUTTON, new EditProfileButton());
        repository.put(CommandName.LANGUAGE_BUTTON, new LanguageButton());
        repository.put(CommandName.EDIT_ADMIN_INFORMATION_BUTTON, new EditAdminInformationButton());
    }

    public Command getCommand(String name){
        Command command = null;
        try {
            command = repository.get(CommandName.valueOf(name.toUpperCase()));
        }
        catch (IllegalArgumentException | NullPointerException e){
            command = repository.get(CommandName.MAIN_PAGE);//тут сделать все плохо
        }
        return command;

    }

}
