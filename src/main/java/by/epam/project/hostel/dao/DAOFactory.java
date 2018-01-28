package by.epam.project.hostel.dao;

import by.epam.project.hostel.dao.impl.BookingDAOImpl;
import by.epam.project.hostel.dao.impl.CommentDAOImpl;
import by.epam.project.hostel.dao.impl.GuestroomDAOImpl;
import by.epam.project.hostel.dao.impl.HostelDAOImpl;
import by.epam.project.hostel.dao.impl.UserDAOImpl;

public class DAOFactory {
    private final static DAOFactory instance = new DAOFactory();

    private UserDAO userDAO = new UserDAOImpl();
    private HostelDAO hostelDAO = new HostelDAOImpl();
    private GuestroomDAO guestroomDAO = new GuestroomDAOImpl();
    private BookingDAO bookingDAO = new BookingDAOImpl();
    private CommentDAO commentDAO = new CommentDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public HostelDAO getHostelDAO() {
        return hostelDAO;
    }

    public GuestroomDAO getGuestroomDAO() {
        return guestroomDAO;
    }

    public BookingDAO getBookingDAO() {
        return bookingDAO;
    }

    public CommentDAO getCommentDAO() {
        return commentDAO;
    }

    public HostelDAO createHostelDAO() {
        return new HostelDAOImpl();
    }

    public UserDAO createUserDAO() {
        return new UserDAOImpl();
    }

    public GuestroomDAO createGuestroomDAO() {
        return new GuestroomDAOImpl();
    }

    public CommentDAO createCommentDAO() {
        return new CommentDAOImpl();
    }

    public BookingDAO createBookingDAO() {
        return new BookingDAOImpl();
    }

}


