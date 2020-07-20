package by.epam.krein.abitapp.service;

public interface SecurityService {
    public boolean equalsPassword(String firstPassword, char[] secondPassword);
    public char[] createPassword(String password);
}
