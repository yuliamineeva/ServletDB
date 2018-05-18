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
    public User getUserById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM user INNER JOIN roles AS r(r_id, r_role_name, r_role) " +
                "ON user.role = r.r_role WHERE user.id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
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
        connection.close();
        return user;
    }

    @Override
    public User getUserByLogin(String login) throws SQLException {
//todo узнать таблицу по роли, вывести админа, студента или лектора
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM user INNER JOIN roles AS r(r_id, r_role_name, r_role) " +
                "ON user.role = r.r_role WHERE user.login = ?");
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
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
        connection.close();
        return user;
    }

    @Override
    public List<User> getAllUsersByRole(String role_name) throws SQLException {
        Connection connection = connectionManager.getConnection();
//todo узнать таблицу по роли, вывести всех админов, студентов или лекторов
        connection.close();
        return null;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM user");
        ArrayList<User> users = new ArrayList<>();
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getInt("role"));
            users.add(user);
        }
        connection.close();
        return users;
    }
}
