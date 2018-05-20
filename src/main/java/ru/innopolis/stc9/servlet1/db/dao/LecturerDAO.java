package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Lecturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LecturerDAO implements I_LecturerDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    @Override
    public boolean addLecturer(Lecturer lecturer) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO lecturer " +
                "(name, login, password) VALUES (?, ?, ?)");
        setStatementForAdd(lecturer, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForAdd(Lecturer lecturer, PreparedStatement statement) throws SQLException {
        statement.setString(1, lecturer.getName());
        statement.setString(2, lecturer.getLogin());
        statement.setString(3, lecturer.getPassword());
    }

    @Override
    public List<Lecturer> getAllLecturers() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM lecturer");
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        Lecturer lecturer = null;
        while (resultSet.next()) {
            lecturer = getlecturerFromResultset(resultSet);
            lecturers.add(lecturer);
        }
        connection.close();
        return lecturers;
    }

    @Override
    public Lecturer getLecturerById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM lecturer WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Lecturer lecturer = getlecturerFromResultset(resultSet);
        connection.close();
        return lecturer;
    }

    private Lecturer getlecturerFromResultset(ResultSet resultSet) throws SQLException {
        Lecturer lecturer = null;
        if (resultSet.next()) {
            lecturer = new Lecturer(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password"));
        }
        return lecturer;
    }

    @Override
    public boolean updateLecturer(Lecturer lecturer) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE lecturer " +
                "SET name = ?, login = ?, password = ? WHERE id = ?");
        setStatementForUpdate(lecturer, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForUpdate(Lecturer lecturer, PreparedStatement statement) throws SQLException {
        statement.setString(1, lecturer.getName());
        statement.setString(2, lecturer.getLogin());
        statement.setString(3, lecturer.getPassword());
        statement.setInt(4, lecturer.getId());
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
