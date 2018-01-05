package by.epam.project.hostel.dao.impl;

import by.epam.project.hostel.dao.UserDAO;
import by.epam.project.hostel.dao.db.connection.ConnectionProvider;
import by.epam.project.hostel.dao.exception.ConnectionPoolException;
import by.epam.project.hostel.dao.exception.DAOException;
import by.epam.project.hostel.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.project.hostel.controller.pagination.PageWrapper.MAX_ENTRIES_PER_PAGE;


public class UserDAOImpl extends EntityDAOImpl implements UserDAO {

    private static final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

    private static final String SELECT_USER_BY_LOGIN_AND_PASSWORD = "SELECT id,name,surname,login,password,email,role,banned,number FROM user WHERE login= ? AND password= ?";
    private static final String INSERT_USER = "INSERT INTO user(name, surname,login, password, email,number) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_BY_LOGIN = "DELETE FROM user WHERE login = ?";
    private static final String SELECT_USER_LIMIT = "SELECT id, name,surname,login,password,email,role,banned,number FROM user LIMIT ?,?";
    private static final String UPDATE_USER_BY_ID = "UPDATE user SET role=?,banned=? WHERE id=?";

    @Override
    public User signIn(String login, String password) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_LOGIN_AND_PASSWORD)) {
            ps.setString(1, login);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return createUser(rs);
            }

        } catch (SQLException e) {
            throw new DAOException("Error during user sign in: ", e);
        }
    }

    @Override
    public void editUser(int id, String role, int banned) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            ps.setString(1, role);
            ps.setInt(2, banned);
            ps.setInt(3, id);
            ps.executeUpdate();

        } catch (SQLException |
                ConnectionPoolException e)

        {
            throw new DAOException("Error during getting user by id: " + id, e);
        }

    }

    @Override
    public void register(String name, String surname, String login, String password, String email, String number) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_USER)) {
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.setString(3, login);
            ps.setString(4, password);
            ps.setString(5, email);
            ps.setString(6, number);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("error during registration: ", e);
        }
    }

    @Override
    public void deleteUser(String login) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_BY_LOGIN)) {
            ps.setString(1, login);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("error during delete user: " + login, e);
        }
    }

    @Override
    public List<User> getUsers(int pageNumber) throws DAOException {
        try (Connection connection = connectionProvider.takeConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_USER_LIMIT)) {
            ps.setInt(1, pageNumber - 1);
            ps.setInt(2, MAX_ENTRIES_PER_PAGE);
            try (ResultSet rs = ps.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (rs.next()) {
                    users.add(createUser(rs));
                }
                return users;
            }

        } catch (SQLException e) {
            throw new DAOException("can't get users from database", e);
        }
    }

    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setName(rs.getString(2));
        user.setSurname(rs.getString(3));
        user.setLogin(rs.getString(4));
        user.setPassword(rs.getString(5));
        user.setEmail(rs.getString(6));
        user.setRole(rs.getString(7));
        user.setBanned(rs.getInt(8));
        user.setNumber(rs.getString(9));
        return user;
    }


    @Override
    protected String getTableName() {
        return "user";
    }
}
