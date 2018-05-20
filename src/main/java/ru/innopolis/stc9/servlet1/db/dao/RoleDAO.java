package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements I_RoleDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    @Override
    public boolean addRole(Role role) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO roles " +
                "(role_name, role) VALUES (?, ?)");
        setStatementForAdd(role, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForAdd(Role role, PreparedStatement statement) throws SQLException {
        statement.setString(1, role.getRole_name());
        statement.setInt(2, role.getRole());
    }

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

    private Role getRoleFromResultset(ResultSet resultSet) throws SQLException {
        Role role = null;
        if (resultSet.next()) {
            role = new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("role_name"),
                    resultSet.getInt("role"));
        }
        return role;
    }

    @Override
    public List<Role> getAllRoles() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM roles");
        List<Role> rolesArrayList = new ArrayList<>();
        Role role = null;
        while (resultSet.next()) {
            role = getRoleFromResultset(resultSet);
            rolesArrayList.add(role);
        }
        connection.close();
        return rolesArrayList;
    }

    @Override
    public boolean updateRole(Role role) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE roles " +
                "SET role_name = ?, role = ? WHERE id = ?");
        setStatementForUpdate(role, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForUpdate(Role role, PreparedStatement statement) throws SQLException {
        statement.setString(1, role.getRole_name());
        statement.setInt(2, role.getRole());
        statement.setInt(3, role.getId());
    }

    @Override
    public boolean deleteRoleById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM roles " +
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
