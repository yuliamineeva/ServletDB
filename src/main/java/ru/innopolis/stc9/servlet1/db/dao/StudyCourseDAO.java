package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Lecturer;
import ru.innopolis.stc9.servlet1.pojo.StudyCourse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudyCourseDAO implements I_StudyCourseDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    @Override
    public boolean addStudyCourse(StudyCourse studyCourse) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO studycourse " +
                "(id, name, lecturer_id) VALUES (?, ?, ?)");
        statement.setInt(1, studyCourse.getId());
        statement.setString(2, studyCourse.getName());
        statement.setInt(3, studyCourse.getLecturer_id());
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public StudyCourse getStudyCourseById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM studycourse INNER JOIN lecturer AS lec(lec_id, lec_name, lec_login, lec_password) " +
                "ON studycourse.lecturer_id = lec.lec_id WHERE studycourse.id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        StudyCourse studyCourse = null;
        if (resultSet.next()) {
            studyCourse = new StudyCourse(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("lecturer_id"),
                    new Lecturer(
                            resultSet.getInt("lec_id"),
                            resultSet.getString("lec_name"),
                            resultSet.getString("lec_login"),
                            resultSet.getString("lec_password")));
        }
        connection.close();
        return studyCourse;
    }

    @Override
    public ArrayList<StudyCourse> getStudyCourseByLecturer(Lecturer lecturer) throws SQLException {
        ArrayList<StudyCourse> courseArrayList = new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM studycourse WHERE lecturer_id = ?");
        statement.setInt(1, lecturer.getId());
        ResultSet resultSet = statement.executeQuery();
        StudyCourse studyCourse = null;
        while (resultSet.next()) {
            studyCourse = new StudyCourse(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("lecturer_id"));
            courseArrayList.add(studyCourse);
        }
        connection.close();
        return courseArrayList;
    }

    @Override
    public boolean updateStudyCourse(StudyCourse studyCourse) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE studycourse " +
                "SET name = ?, lecturer_id = ? WHERE id = ?");
        statement.setString(1, studyCourse.getName());
        statement.setInt(2, studyCourse.getLecturer_id());
        statement.setInt(3, studyCourse.getId());
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteStudyCourseById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM studycourse " +
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
