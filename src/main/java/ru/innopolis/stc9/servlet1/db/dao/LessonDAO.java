package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.StudyCourse;

import java.sql.*;
import java.util.ArrayList;

public class LessonDAO implements I_LessonDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    @Override
    public boolean addLesson(Lesson lesson) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO lessons " +
                "(topic, date, studycourse_id) VALUES (?, ?, ?)");
        setStatementForAdd(lesson, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForAdd(Lesson lesson, PreparedStatement statement) throws SQLException {
        statement.setString(1, lesson.getTopic());
        statement.setDate(2, new java.sql.Date(lesson.getDate().getTime())); /** преобразование даты из java.util.Date в java.sql.Date */
        statement.setInt(3, lesson.getStudycourse_id());
    }

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

    private Lesson getLessonFromResultset(ResultSet resultSet) throws SQLException {
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

    @Override
    public ArrayList<Lesson> getLessonsByStudyCourse(StudyCourse studyCourse) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM lessons WHERE studycourse_id = ?");
        statement.setInt(1, studyCourse.getId());
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Lesson> lessonsArrayList = getLessonsListFromResultset(resultSet);
        connection.close();
        return lessonsArrayList;
    }

    private ArrayList<Lesson> getLessonsListFromResultset(ResultSet resultSet) throws SQLException {
        ArrayList<Lesson> lessonsArrayList = new ArrayList<>();
        Lesson lesson = null;
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

    @Override
    public boolean updateLesson(Lesson lesson) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE lessons " +
                "SET topic = ?, date = ?, studycourse_id = ? WHERE id = ?");
        setStatementForUpdate(lesson, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForUpdate(Lesson lesson, PreparedStatement statement) throws SQLException {
        statement.setString(1, lesson.getTopic());
        statement.setDate(2, new java.sql.Date(lesson.getDate().getTime()));
        statement.setInt(3, lesson.getStudycourse_id());
        statement.setInt(4, lesson.getId());
    }

    @Override
    public boolean deleteLessonById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM lessons " +
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
