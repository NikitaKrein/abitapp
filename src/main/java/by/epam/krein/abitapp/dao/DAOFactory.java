package by.epam.krein.abitapp.dao;

import by.epam.krein.abitapp.dao.impl.SQLAdminDAO;
import by.epam.krein.abitapp.dao.impl.SQLSpecialtyDAO;
import by.epam.krein.abitapp.dao.impl.SQLUniversityDAO;
import by.epam.krein.abitapp.dao.impl.SQLUserDAO;

public final class DAOFactory {

    private DAOFactory(){

    }

    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO sqlUserImpl = new SQLUserDAO();
    private final UniversityDAO sqlUniversityImpl = new SQLUniversityDAO();
    private final AdminDAO sqlAdminImpl = new SQLAdminDAO();
    private final SpecialtyDAO sqlSpecialtyImpl = new SQLSpecialtyDAO();

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDAO getUserDAO(){
        return sqlUserImpl;
    }
    public UniversityDAO getUniversityDAO(){
        return sqlUniversityImpl;
    }
    public AdminDAO getAdminDAO(){
        return sqlAdminImpl;
    }
    public SpecialtyDAO getSpecialtyDAO(){
        return sqlSpecialtyImpl;
    }
}
