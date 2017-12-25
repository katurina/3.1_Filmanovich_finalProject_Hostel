package by.epam.project.hostel.service;

import by.epam.project.hostel.service.impl.GuestroomServiceImpl;
import by.epam.project.hostel.service.impl.HostelServiceImpl;
import by.epam.project.hostel.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private UserService userService = new UserServiceImpl();
    private HostelService hostelService = new HostelServiceImpl();
    private GuestroomService guestroomService = new GuestroomServiceImpl();


    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public HostelService getHostelService() {
        return hostelService;
    }

    public GuestroomService getGuestroomService() {
        return guestroomService;
    }
}
