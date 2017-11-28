package by.epam.project.hostel.controller;

import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceInitException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        try {
            serviceFactory.init();
        } catch (ServiceInitException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
