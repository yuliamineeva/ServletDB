package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements I_StudentDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    @Override
    public boolean addStudent(Student student) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO student " +
                "(id, name, login, password, average_mark) VALUES (?, ?, ?, ?, ?)");
        statement.setInt(1, student.getId());
        statement.setString(2, student.getName());
        statement.setString(3, student.getLogin());
        statement.setString(4, student.getPassword());
        statement.setFloat(5, student.getAverageMark());
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM student");
        ArrayList<Student> students = new ArrayList<>();
        Student student = null;
        while (resultSet.next()) {
            student = new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getFloat("average_mark"));
            students.add(student);
        }
        connection.close();
        return students;
    }

    @Override
    public Student getStudentById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM student WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Student student = null;
        if (resultSet.next()) {
            student = new Student(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getFloat("average_mark"));
        }
        connection.close();
        return student;
    }

//    @Override
//    public boolean updateStudent(Student student) throws SQLException {
//        Connection connection = connectionManager.getConnection();
//        PreparedStatement statement = connection.prepareStatement("UPDATE student " +
//                "SET name = ?, login = ?, password = ?, average_mark = ? WHERE id = ?");
//        statement.setString(1, student.getName());
//        statement.setString(2, student.getLogin());
//        statement.setString(3, student.getPassword());
//        statement.setFloat(4, student.getAverageMark());
//        statement.setInt(5, student.getId());
//        int countRow = statement.executeUpdate();
//        connection.close();
//        if (countRow > 0) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    @Override
    public boolean updateStudent(Student student) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE student " +
                "SET name = ?, login = ?, password = ?, average_mark = ? WHERE id = ?");
        getStatement(student, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void getStatement(Student student, PreparedStatement statement) throws SQLException {
        statement.setString(1, student.getName());
        statement.setString(2, student.getLogin());
        statement.setString(3, student.getPassword());
        statement.setFloat(4, student.getAverageMark());
        statement.setInt(5, student.getId());

    }

    @Override
    public boolean deleteStudentById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM student " +
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
