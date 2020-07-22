package by.epam.krein.abitapp.service;

import by.epam.krein.abitapp.exception.ServiceException;

public interface SecurityService {
    public boolean equalsPassword(String firstPassword, char[] secondPassword);
    public char[] createPassword(String password);
}
