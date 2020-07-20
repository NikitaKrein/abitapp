package by.epam.krein.abitapp.entity;


import java.util.Map;

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private char[] password;
    private Specialty specialty;
    private Specialty requestSpecialty;
    private int formOfTraining = 0;
    private String adminMessage;
    Map<Exam, Integer> examMarks;
    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Specialty getRequestSpecialty() {
        return requestSpecialty;
    }

    public void setRequestSpecialty(Specialty requestSpecialty) {
        this.requestSpecialty = requestSpecialty;
    }

    public int getFormOfTraining() {
        return formOfTraining;
    }

    public void setFormOfTraining(int formOfTraining) {
        this.formOfTraining = formOfTraining;
    }

    public String getAdminMessage() {
        return adminMessage;
    }

    public void setAdminMessage(String adminMessage) {
        this.adminMessage = adminMessage;
    }

    public Map<Exam, Integer> getExamMarks() {
        return examMarks;
    }

    public void setExamMarks(Map<Exam, Integer> examMarks) {
        this.examMarks = examMarks;
    }

}
