package ru.innopolis.stc9.servlet1.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.CryptoUtils;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Admin;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements I_AdminDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();
    private final static Logger logger = Logger.getLogger(AdminDAO.class);

    @Override
    public boolean addAdmin(Admin admin) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO admin (" +
                "name, login, password) VALUES (?, ?, ?)");
        try {
            setStatementForAdd(admin, statement);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error trying to crypto password", e);
        }
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForAdd(Admin admin, PreparedStatement statement) throws SQLException, NoSuchAlgorithmException {
        statement.setString(1, admin.getName());
        statement.setString(2, admin.getLogin());
        statement.setString(3, CryptoUtils.byteArrayToHexString(CryptoUtils.computeHash(admin.getPassword())));
    }


    @Override
    public List<Admin> getAllAdmins() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM admin");
        List<Admin> admins = getAdminlistFromResultset(resultSet);
        connection.close();
        return admins;
    }

    private List<Admin> getAdminlistFromResultset(ResultSet resultSet) throws SQLException {
        List<Admin> admins = new ArrayList<>();
        Admin admin = null;
        while (resultSet.next()) {
            admin = new Admin(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password"));
            admins.add(admin);
        }
        return admins;
    }

    @Override
    public Admin getAdminByLogin(String login) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM admin WHERE login = ?");
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        Admin admin = getAdminFromResultset(resultSet);
        connection.close();
        return admin;
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
        try {
            setStatementForAdd(admin, statement);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error trying to crypto password", e);
        }
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
