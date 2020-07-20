package by.epam.krein.abitapp.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.epam.krein.abitapp.service.SecurityService;

import java.util.Arrays;

public class SecurityServiceImpl implements SecurityService {

    public char[] createPassword(String password){
        return BCrypt.withDefaults().hashToChar(12, password.toCharArray());
    }

    public boolean equalsPassword(String firstPassword, char[] secondPassword){
        return BCrypt.verifyer().verify(firstPassword.toCharArray(), secondPassword).verified;
    }
}
