package ru.innopolis.stc9.servlet1.db.dao;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.CryptoUtils;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Student;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс студента, работающий с базой данных
 */
public class StudentDAO implements I_StudentDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();
    private final static Logger logger = Logger.getLogger(StudentDAO.class);

    /**
     * Добавить студента в базу данных
     *
     * @param student
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean addStudent(Student student) throws SQLException {
        if (student == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO student " +
                "(name, login, password, average_mark) VALUES (?, ?, ?, ?)");
        setStatementForAdd(student, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForAdd(Student student, PreparedStatement statement) throws SQLException {
        statement.setString(1, student.getName());
        statement.setString(2, student.getLogin());
        try {
            statement.setString(3, CryptoUtils.byteArrayToHexString(CryptoUtils.computeHash(student.getPassword())));
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error trying to crypto password", e);
        }
        statement.setFloat(4, student.getAverageMark());
    }

    /**
     * Получить список всех студентов
     *
     * @return List<Student>
     * @throws SQLException
     */
    @Override
    public List<Student> getAllStudents() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM student");
        ArrayList<Student> students = new ArrayList<>();
        Student student = null;
        while (resultSet.next()) {
            student = getStudentFromResultset(resultSet);
            students.add(student);
        }
        connection.close();
        return students;
    }

    /**
     * Получить студента по логину
     * @param login
     * @return Student
     * @throws SQLException
     */
    @Override
    public Student getStudentByLogin(String login) throws SQLException {
        if (login == null) return null;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM student WHERE login = ?");
        statement.setString(1, login);
        ResultSet resultSet = statement.executeQuery();
        Student student = null;
        if (resultSet.next()) {
            student = getStudentFromResultset(resultSet);
        }
        connection.close();
        return student;
    }

    /**
     * Получить студента по id
     * @param id
     * @return Student
     * @throws SQLException
     */
    @Override
    public Student getStudentById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM student WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Student student = null;
        if (resultSet.next()) {
            student = getStudentFromResultset(resultSet);
        }
        connection.close();
        return student;
    }

    /**
     * Обработка resultSet
     */
    private Student getStudentFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return null;
        Student student = new Student(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("login"),
                resultSet.getString("password"),
                resultSet.getFloat("average_mark"));
        return student;
    }

    /**
     * обновить студента в базе данных
     * @param student
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean updateStudent(Student student) throws SQLException {
        if (student == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE student " +
                "SET name = ?, login = ?, password = ?, average_mark = ? WHERE id = ?");
        setStatementForUpdate(student, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForUpdate(Student student, PreparedStatement statement) throws SQLException {
        setStatementForAdd(student, statement);
        statement.setInt(5, student.getId());
    }

    /**
     * удалить студента из базы данных
     * @param id
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean deleteStudentById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM student " +
                "WHERE id = ?");
        statement.setInt(1, id);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }
}
