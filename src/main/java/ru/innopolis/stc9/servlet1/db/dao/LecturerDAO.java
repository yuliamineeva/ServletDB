package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Lecturer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LecturerDAO implements I_LecturerDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    @Override
    public boolean addLecturer(Lecturer lecturer) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO lecturer " +
                "(id, name, login, password) VALUES (?, ?, ?, ?)");
        statement.setInt(1, lecturer.getId());
        statement.setString(2, lecturer.getName());
        statement.setString(3, lecturer.getLogin());
        statement.setString(4, lecturer.getPassword());
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Lecturer getLecturerById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM lecturer WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Lecturer lecturer = null;
        if (resultSet.next()) {
            lecturer = new Lecturer(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password"));
        }
        connection.close();
        return lecturer;
    }

    @Override
    public boolean updateLecturer(Lecturer lecturer) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE lecturer " +
                "SET name = ?, login = ?, password = ? WHERE id = ?");
        statement.setString(1, lecturer.getName());
        statement.setString(2, lecturer.getLogin());
        statement.setString(3, lecturer.getPassword());
        statement.setInt(4, lecturer.getId());
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteLecturerById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM lecturer " +
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
