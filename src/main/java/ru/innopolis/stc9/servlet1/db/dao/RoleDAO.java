package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO implements I_RoleDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    @Override
    public Role getRoleById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM roles WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Role role = null;
        if (resultSet.next()) {
            role = new Role(
                    resultSet.getInt("id"),
                    resultSet.getString("role_name"),
                    resultSet.getInt("role"));
        }
        connection.close();
        return role;
    }
}
