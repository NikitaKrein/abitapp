package by.epam.krein.abitapp.service;

import by.epam.krein.abitapp.entity.Admin;

import java.sql.SQLException;

public interface AdminService {
    public void updatePassword(int id, String password);
    public Admin findByEmail(String email);
}
