package by.epam.project.hostel.controller;

import by.epam.project.hostel.service.ServiceFactory;
import by.epam.project.hostel.service.exception.ServiceInitException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServiceFactoryInitializer implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ServiceFactoryInitializer.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        try {
            serviceFactory.init();
        } catch (ServiceInitException e) {
            LOGGER.error("service wasn't init", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
