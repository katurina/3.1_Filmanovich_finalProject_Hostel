package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.ConnectionProvider;
import by.epam.project.hostel.dao.UserDAO;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT id,name,surname,login,password,email,role,banned,number FROM user WHERE login= ? AND password= ?";
    private static final String INSERT_USER = "INSERT INTO user(name, surname,login, password, email,number) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_BY_LOGIN = "DELETE FROM user WHERE login = ?";
    private static ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

    public User signIn(String login, String password) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection()) {
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return initUser(rs);
        } catch (SQLException e) {
            throw new DAOException("Error from DB: ", e);
        }
    }

    @Override
    public void registration(String name, String surname, String login, String password, String email, String number) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_USER);
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, login);
            ps.setString(4, password);
            ps.setString(5, email);
            ps.setString(6, number);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("bad SQL request: ", e);
        }
    }

    public void deleteUser(String login) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_BY_LOGIN);
            ps.setString(1, login);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("bad delete gateway", e);
        }
    }

    private User initUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setName(rs.getString(2));
        user.setSurname(rs.getString(3));
        user.setLogin(rs.getString(4));
        user.setPassword(rs.getString(5));
        user.setEmail(rs.getString(6));
        user.setNumber(rs.getString(7));
        return user;
    }


}
