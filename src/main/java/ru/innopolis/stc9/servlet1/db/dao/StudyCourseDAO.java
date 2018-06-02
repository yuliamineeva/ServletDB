package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Lecturer;
import ru.innopolis.stc9.servlet1.pojo.StudyCourse;

import java.sql.*;
import java.util.ArrayList;

/**
 * Класс курсов, работающий с базой данных
 */
public class StudyCourseDAO implements I_StudyCourseDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    /**
     * Добавить курс в базу данных
     *
     * @param studyCourse
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean addStudyCourse(StudyCourse studyCourse) throws SQLException {
        if (studyCourse == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO studycourse " +
                "(name, lecturer_id) VALUES (?, ?)");
        setStatementForAdd(studyCourse, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForAdd(StudyCourse studyCourse, PreparedStatement statement) throws SQLException {
        statement.setString(1, studyCourse.getName());
        statement.setInt(2, studyCourse.getLecturer_id());
    }

    /**
     * Получить курс по id
     *
     * @param id
     * @return StudyCourse
     * @throws SQLException
     */
    @Override
    public StudyCourse getStudyCourseById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM studycourse INNER JOIN lecturer AS lec(lec_id, lec_name, lec_login, lec_password) " +
                "ON studycourse.lecturer_id = lec.lec_id WHERE studycourse.id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        StudyCourse studyCourse = getStudyCourseFromResultset(resultSet);
        connection.close();
        return studyCourse;
    }

    /**
     * Обработка resultSet
     */
    private StudyCourse getStudyCourseFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return null;
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
        return studyCourse;
    }

    /**
     * Получить список всех курсов
     * @return ArrayList<StudyCourse>
     * @throws SQLException
     */
    @Override
    public ArrayList<StudyCourse> getAllStudyCourse() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM studycourse");
        ArrayList<StudyCourse> courseArrayList = getCourseArrayListFromResultset(resultSet);
        connection.close();
        return courseArrayList;
    }

    /**
     * Получить список курсов по лектору
     * @param lecturer
     * @return ArrayList<StudyCourse>
     * @throws SQLException
     */
    @Override
    public ArrayList<StudyCourse> getStudyCourseByLecturer(Lecturer lecturer) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM studycourse WHERE lecturer_id = ?");
        statement.setInt(1, lecturer.getId());
        ResultSet resultSet = statement.executeQuery();
        ArrayList<StudyCourse> courseArrayList = getCourseArrayListFromResultset(resultSet);
        connection.close();
        return courseArrayList;
    }

    /**
     * Обработка resultSet
     */
    private ArrayList<StudyCourse> getCourseArrayListFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return new ArrayList<>();
        ArrayList<StudyCourse> courseArrayList = new ArrayList<>();
        StudyCourse studyCourse;
        while (resultSet.next()) {
            studyCourse = new StudyCourse(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("lecturer_id"));
            courseArrayList.add(studyCourse);
        }
        return courseArrayList;
    }

    /**
     * Обновить курс в базе данных
     * @param studyCourse
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean updateStudyCourse(StudyCourse studyCourse) throws SQLException {
        if (studyCourse == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE studycourse " +
                "SET name = ?, lecturer_id = ? WHERE id = ?");
        setStatementForUpdate(studyCourse, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForUpdate(StudyCourse studyCourse, PreparedStatement statement) throws SQLException {
        statement.setString(1, studyCourse.getName());
        statement.setInt(2, studyCourse.getLecturer_id());
        statement.setInt(3, studyCourse.getId());
    }

    /**
     * Удалить курс из базы данных
     * @param id
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean deleteStudyCourseById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM studycourse " +
                "WHERE id = ?");
        statement.setInt(1, id);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }
}
