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

/**
 * Класс админа, работающий с базой данных
 */
public class AdminDAO implements I_AdminDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();
    private final static Logger logger = Logger.getLogger(AdminDAO.class);

    /**
     * Добавить админа в базу данных
     *
     * @param admin
     * @return boolean - результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean addAdmin(Admin admin) throws SQLException {
        if (admin == null) return false;
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
        return countRow > 0;
    }

    /**
     * Установка полей админа в statement
     */
    private void setStatementForAdd(Admin admin, PreparedStatement statement) throws SQLException, NoSuchAlgorithmException {
        statement.setString(1, admin.getName());
        statement.setString(2, admin.getLogin());
        statement.setString(3, CryptoUtils.byteArrayToHexString(CryptoUtils.computeHash(admin.getPassword())));
    }

    /**
     * Получить список всех админов
     *
     * @return List<Admin>
     * @throws SQLException
     */
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

    /**
     * Обработка ResultSet
     */
    private List<Admin> getAdminlistFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return new ArrayList<>();
        List<Admin> admins = new ArrayList<>();
        Admin admin;
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

    /**
     * Получить админа по логину
     * @param login
     * @return Admin
     * @throws SQLException
     */
    @Override
    public Admin getAdminByLogin(String login) throws SQLException {
        if (login == null) return null;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM admin WHERE login = ?");
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        Admin admin = getAdminFromResultset(resultSet);
        connection.close();
        return admin;
    }

    /**
     * Получить админа по ID
     * @param id
     * @return Admin
     * @throws SQLException
     */
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

    /**
     * Обработка ResultSet
     */
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

    /**
     * Обновить админа в базе данных
     * @param admin
     * @return boolean - результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean updateAdmin(Admin admin) throws SQLException {
        if (admin == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE admin " +
                "SET name = ?, login = ?, password = ? WHERE id = ?");
        setStatementForUpdate(admin, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForUpdate(Admin admin, PreparedStatement statement) throws SQLException {
        try {
            setStatementForAdd(admin, statement);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error trying to crypto password", e);
        }
        statement.setInt(4, admin.getId());
    }

    /**
     * Удалить админа из базы данных
     * @param id
     * @return boolean - результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean deleteAdminById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM admin " +
                "WHERE id = ?");
        statement.setInt(1, id);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }
}
