package by.epam.krein.abitapp.controller;

import by.epam.krein.abitapp.controller.commandImpl.*;
import by.epam.krein.abitapp.controller.commandImpl.Error;

public enum CommandName {
    SIGN_IN_BUTTON("/signIn", new SignInButton()),
    SIGN_UP_BUTTON("/signUp", new SignUpButton()),
    SIGN_OUT_BUTTON("/", new SignOutButton()),
    EDIT_PROFILE_BUTTON("/editProfile", new EditProfileButton()),
    SUBMIT_FOR_FACULTY_BUTTON("/faculty", new SubmitForFacultyButton()),
    EDIT_ADMIN_INFORMATION_BUTTON("/admin/profile", new EditAdminInformationButton()),
    ADMIN_EDIT_SPECIALTY_EXAM_BUTTON("/admin/profile", new AdminUpdateSpecialtyExamsButton()),
    ADMIN_UPDATE_PASSWORD_BUTTON("/admin/profile", new AdminUpdatePasswordButton()),
    ADMIN_REQUESTS_BUTTON("/admin/requests", new AdminRequestsButton()),
    //EDIT_ADMIN_INFORMATION("/adminEditInformation.jsp", new EditAdminInformation()),
    EDIT_ADMIN_SPECIALTY_INFORMATION("/adminEditSpecialty.jsp", new EditAdminSpecialtyInformation()),
    ADMIN_REQUESTS("/adminRequests.jsp", new AdminRequests()),
    ADMIN_RATING("/adminFacultyRating.jsp", new AdminFacultyRating()),
    PROFILE("/profile", new Profile()),
    PERSONAL_INFO("/personalInfo.jsp", new Profile()),
    REQUESTS("/requests.jsp", new Profile()),
    RATING("/rating.jsp", new Profile()),
    UNIVERSITY_CATALOG("/university.jsp", new UniversityCatalog()),
    EDIT_PROFILE("/editProfile.jsp", new Profile()),
    FACULTY_PAGE("/faculty.jsp", new FacultyPage()),
    LANGUAGE_BUTTON("/language.jsp", new LanguageButton()),
    ERROR("/error", new Error());
    //MAIN_PAGE("/", new MainPage());


    private final String jspAddress;
    private final Command command;

    public String getJspAddress() {
        return jspAddress;
    }

    public Command getCommand() {
        return command;
    }

    CommandName(String jspAddress, Command command) {
        this.jspAddress = jspAddress;
        this.command = command;
    }
}