package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements I_AdminDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    @Override
    public boolean addAdmin(Admin admin) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO admin (" +
                "name, login, password) VALUES (?, ?, ?)");
        setStatementForAdd(admin, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForAdd(Admin admin, PreparedStatement statement) throws SQLException {
        statement.setString(1, admin.getName());
        statement.setString(2, admin.getLogin());
        statement.setString(3, admin.getPassword());
    }


    @Override
    public List<Admin> getAllAdmins() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM admin");
        ArrayList<Admin> admins = new ArrayList<>();
        while (resultSet.next()) {
            Admin admin = getAdminFromResultset(resultSet);
            admins.add(admin);
        }
        connection.close();
        return admins;
    }

    @Override
    public Admin getAdminById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM admin WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Admin admin = getAdminFromResultset(resultSet);
        connection.close();
        return admin;
    }

    private Admin getAdminFromResultset(ResultSet resultSet) throws SQLException {
        Admin admin = null;
        if (resultSet.next()) {
            admin = new Admin(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password"));
        }
        return admin;
    }

    @Override
    public boolean updateAdmin(Admin admin) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE admin " +
                "SET name = ?, login = ?, password = ? WHERE id = ?");
        setStatementForUpdate(admin, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForUpdate(Admin admin, PreparedStatement statement) throws SQLException {
        statement.setString(1, admin.getName());
        statement.setString(2, admin.getLogin());
        statement.setString(3, admin.getPassword());
        statement.setInt(4, admin.getId());
    }

    @Override
    public boolean deleteAdminById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM admin " +
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
