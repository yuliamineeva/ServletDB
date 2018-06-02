package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.StudyCourse;

import java.sql.*;
import java.util.ArrayList;

/**
 * Класс лекций, работающий с базой данных
 */
public class LessonDAO implements I_LessonDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    /**
     * Добавить лекцию в базу данных
     *
     * @param lesson
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean addLesson(Lesson lesson) throws SQLException {
        if (lesson == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO lessons " +
                "(topic, date, studycourse_id) VALUES (?, ?, ?)");
        setStatementForAdd(lesson, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForAdd(Lesson lesson, PreparedStatement statement) throws SQLException {
        statement.setString(1, lesson.getTopic());
        statement.setDate(2, new java.sql.Date(lesson.getDate().getTime())); /** преобразование даты из java.util.Date в java.sql.Date */
        statement.setInt(3, lesson.getStudycourse_id());
    }

    /**
     * Получить лекцию по id
     *
     * @param id
     * @return Lesson
     * @throws SQLException
     */
    @Override
    public Lesson getLessonById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM lessons INNER JOIN studycourse AS course(c_id, c_name, c_lecturer_id) " +
                "ON lessons.studycourse_id = course.c_id WHERE lessons.id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Lesson lesson = getLessonFromResultset(resultSet);
        connection.close();
        return lesson;
    }

    /**
     * Обработка resultSet
     */
    private Lesson getLessonFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return null;
        Lesson lesson = null;
        if (resultSet.next()) {
            lesson = new Lesson(
                    resultSet.getInt("id"),
                    resultSet.getString("topic"),
                    resultSet.getDate("date"),
                    resultSet.getInt("studycourse_id"),
                    new StudyCourse(
                            resultSet.getInt("c_id"),
                            resultSet.getString("c_name"),
                            resultSet.getInt("c_lecturer_id")));
        }
        return lesson;
    }

    /**
     * Получить список всех лекций
     * @return ArrayList<Lesson>
     * @throws SQLException
     */
    @Override
    public ArrayList<Lesson> getAllLessons() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM lessons");
        ArrayList<Lesson> lessonsArrayList = getLessonsListFromResultset(resultSet);
        connection.close();
        return lessonsArrayList;
    }

    /**
     * Получить список всех лекций по курсу
     * @param studyCourse
     * @return ArrayList<Lesson>
     * @throws SQLException
     */
    @Override
    public ArrayList<Lesson> getLessonsByStudyCourse(StudyCourse studyCourse) throws SQLException {
        if (studyCourse == null) return new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM lessons WHERE studycourse_id = ?");
        statement.setInt(1, studyCourse.getId());
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Lesson> lessonsArrayList = getLessonsListFromResultset(resultSet);
        connection.close();
        return lessonsArrayList;
    }

    /**
     * обработка resultSet
     */
    private ArrayList<Lesson> getLessonsListFromResultset(ResultSet resultSet) throws SQLException {
        ArrayList<Lesson> lessonsArrayList = new ArrayList<>();
        Lesson lesson;
        while (resultSet.next()) {
            lesson = new Lesson(
                    resultSet.getInt("id"),
                    resultSet.getString("topic"),
                    resultSet.getDate("date"),
                    resultSet.getInt("studycourse_id"));
            lessonsArrayList.add(lesson);
        }
        return lessonsArrayList;
    }

    /**
     * Обновить лекцию в базе данных
     * @param lesson
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean updateLesson(Lesson lesson) throws SQLException {
        if (lesson == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE lessons " +
                "SET topic = ?, date = ?, studycourse_id = ? WHERE id = ?");
        setStatementForUpdate(lesson, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForUpdate(Lesson lesson, PreparedStatement statement) throws SQLException {
        statement.setString(1, lesson.getTopic());
        statement.setDate(2, new java.sql.Date(lesson.getDate().getTime()));
        statement.setInt(3, lesson.getStudycourse_id());
        statement.setInt(4, lesson.getId());
    }

    /**
     * Удалить лекцию из базы данных
     * @param id
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean deleteLessonById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM lessons " +
                "WHERE id = ?");
        statement.setInt(1, id);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }
}
