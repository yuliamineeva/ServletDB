package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Role;
import ru.innopolis.stc9.servlet1.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс пользователя, работающий с базой данных
 */
public class UserDAO implements I_UserDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    /**
     * Добавить пользователя в базу данных
     *
     * @param user
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean addUser(User user) throws SQLException {
        if (user == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users " +
                "(login, role) VALUES (?, ?)");
        setStatementForAdd(user, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForAdd(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setInt(2, user.getRole_number());
    }

    /**
     * Получить пользователя по id
     *
     * @param id
     * @return User
     * @throws SQLException
     */
    @Override
    public User getUserById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM users INNER JOIN roles AS r(r_id, r_role_name, r_role) " +
                "ON users.role = r.r_role WHERE users.id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        User user = getUserFromResultset(resultSet);
        connection.close();
        return user;
    }

    /**
     * Обработка resultSet
     */
    private User getUserFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return null;
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

    /**
     * Получить пользователя по логину
     *
     * @param login
     * @return User
     * @throws SQLException
     */
    @Override
    public User getUserByLogin(String login) throws SQLException {
        if (login == null) return null;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM users INNER JOIN roles AS r(r_id, r_role_name, r_role) " +
                "ON users.role = r.r_role WHERE users.login = ?");
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        User user = getUserFromResultset(resultSet);
        connection.close();
        return user;
    }

    /**
     * Получить список пользователей по названию роли
     *
     * @param role_name
     * @return List<User>
     * @throws SQLException
     */
    @Override
    public List<User> getAllUsersByRole(String role_name) throws SQLException {
        if (role_name == null) return new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM users INNER JOIN roles AS r(r_id, r_role_name, r_role) " +
                "ON users.role = r.r_role WHERE r_role_name = ?");
        statement.setString(1, role_name);
        ResultSet resultSet = statement.executeQuery();
        List<User> users = getUserlistFromResultset(resultSet);
        connection.close();
        return users;
    }

    /**
     * Получить список всех пользователей
     *
     * @return List<User>
     * @throws SQLException
     */
    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM users");
        List<User> users = getUserlistFromResultset(resultSet);
        connection.close();
        return users;
    }

    /**
     * Обработка resultSet
     */
    private List<User> getUserlistFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return new ArrayList<>();
        List<User> users = new ArrayList<>();
        User user;
        while (resultSet.next()) {
            user = new User(
                    resultSet.getInt("id"),
                    resultSet.getString("login"),
                    resultSet.getInt("role"));
            users.add(user);
        }
        return users;
    }

    /**
     * Обновить пользователя в базе данных
     *
     * @param user
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean updateUser(User user) throws SQLException {
        if (user == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE users " +
                "SET login = ?, role = ? WHERE id = ?");
        setStatementForUpdate(user, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForUpdate(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setInt(2, user.getRole_number());
        statement.setInt(3, user.getId());
    }

    /**
     * Удалить пользователя из базы данных
     *
     * @param id
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean deleteUserById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users " +
                "WHERE id = ?");
        statement.setInt(1, id);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

}
