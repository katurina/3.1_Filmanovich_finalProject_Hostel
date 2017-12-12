package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.exception.DAOException;
import org.junit.Test;

public class UserDAOImplTest {

    @Test
    public void registration() throws DAOException {
        UserDAOImpl userDAO = new UserDAOImpl();
        userDAO.registration("1", "1", "1", "1", "1", "1");
//        todo asset
        userDAO.deleteUser("1");
    }
    @Test
    public void adminSignIn(){

    }
}
