package by.epam.krein.abitapp.service;

import by.epam.krein.abitapp.service.impl.*;

public class ServiceFactory {

    private ServiceFactory(){

    }

    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userServiceImpl = new UserServiceImpl();
    private final UniversityService universityServiceImpl = new UniversityServiceImpl();
    private final AdminService adminServiceImpl = new AdminServiceImpl();
    private final SpecialtyService specialtyServiceImpl = new SpecialtyServiceImpl();
    private final SecurityService securityServiceImpl = new SecurityServiceImpl();

    public static ServiceFactory getInstance(){
        return instance;
    }

    public UserService getUserService(){
        return userServiceImpl;
    }
    public UniversityService getUniversityService(){
        return universityServiceImpl;
    }
    public AdminService getAdminService(){
        return adminServiceImpl;
    }
    public SpecialtyService getSpecialtyService(){
        return specialtyServiceImpl;
    }
    public SecurityService getSecurityService(){
        return securityServiceImpl;
    }
}
