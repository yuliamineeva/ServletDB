package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Role;
import ru.innopolis.stc9.servlet1.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements I_UserDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();


    @Override
    public boolean addUser(User user) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO user " +
                "(login, role) VALUES (?, ?)");
        setStatementForAdd(user, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForAdd(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setInt(2, user.getRole_number());
    }


    @Override
    public User getUserById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM user INNER JOIN roles AS r(r_id, r_role_name, r_role) " +
                "ON user.role = r.r_role WHERE user.id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        User user = getUserFromResultset(resultSet);
        connection.close();
        return user;
    }

    private User getUserFromResultset(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getInt("role"),
                    new Role(
                            resultSet.getInt("r_id"),
                            resultSet.getString("r_role_name"),
                            resultSet.getInt("r_role")));
        }
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM user INNER JOIN roles AS r(r_id, r_role_name, r_role) " +
                "ON user.role = r.r_role WHERE user.login = ?");
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        User user = getUserFromResultset(resultSet);
        connection.close();
        return user;
    }

    @Override
    public List<User> getAllUsersByRole(String role_name) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM user INNER JOIN roles AS r(r_id, r_role_name, r_role) " +
                "ON user.role = r.r_role WHERE r_role = ?");
        statement.setString(1, role_name);
        ResultSet resultSet = statement.executeQuery();
        List<User> users = getUserlistFromResultset(resultSet);
        connection.close();
        return users;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM user");
        List<User> users = getUserlistFromResultset(resultSet);
        connection.close();
        return users;
    }

    private List<User> getUserlistFromResultset(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getInt("role"));
            users.add(user);
        }
        return users;
    }

    @Override
    public boolean updateUser(User user) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE user " +
                "SET login = ?, role = ? WHERE id = ?");
        setStatementForUpdate(user, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForUpdate(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setInt(2, user.getRole_number());
        statement.setInt(3, user.getId());
    }

    @Override
    public boolean deleteUserById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM user " +
                "WHERE id = ?");
        statement.setInt(1, id);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

}
