package by.epam.krein.abitapp.entity;

public class Admin {
    private int id;
    private String email;
    private char[] password;
    private University university;

    public Admin() {
    }

    public Admin(String email, char[] password, University university) {
        this.email = email;
        this.password = password;
        this.university = university;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

}
