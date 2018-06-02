package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс ролей, работающий с базой данных
 */
public class RoleDAO implements I_RoleDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    /**
     * Добавить роль в базу данных
     *
     * @param role
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean addRole(Role role) throws SQLException {
        if (role == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO roles " +
                "(role_name, role) VALUES (?, ?)");
        setStatementForAdd(role, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForAdd(Role role, PreparedStatement statement) throws SQLException {
        statement.setString(1, role.getRole_name());
        statement.setInt(2, role.getRole());
    }

    /**
     * Получить роль по id
     *
     * @param id
     * @return Role
     * @throws SQLException
     */
    @Override
    public Role getRoleById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM roles WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Role role = getRoleFromResultset(resultSet);
        connection.close();
        return role;
    }

    /**
     * Обработка resultSet
     */
    private Role getRoleFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return null;
        Role role = null;
        if (resultSet.next()) {
            role = new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("role_name"),
                    resultSet.getInt("role"));
        }
        return role;
    }

    /**
     * Получить список всех ролей
     * @return List<Role>
     * @throws SQLException
     */
    @Override
    public List<Role> getAllRoles() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM roles");
        List<Role> rolesArrayList = getRolelistFromResultset(resultSet);
        connection.close();
        return rolesArrayList;
    }

    /**
     * Обработка resultSet
     */
    private List<Role> getRolelistFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return new ArrayList<>();
        List<Role> rolesArrayList = new ArrayList<>();
        Role role;
        while (resultSet.next()) {
            role = new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("role_name"),
                    resultSet.getInt("role"));
            rolesArrayList.add(role);
        }
        return rolesArrayList;
    }

    /**
     * обновить роль в базе данных
     * @param role
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean updateRole(Role role) throws SQLException {
        if (role == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE roles " +
                "SET role_name = ?, role = ? WHERE id = ?");
        setStatementForUpdate(role, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForUpdate(Role role, PreparedStatement statement) throws SQLException {
        statement.setString(1, role.getRole_name());
        statement.setInt(2, role.getRole());
        statement.setInt(3, role.getId());
    }

    /**
     * Удалить роль из базы данных
     * @param id
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean deleteRoleById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM roles " +
                "WHERE id = ?");
        statement.setInt(1, id);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }
}
