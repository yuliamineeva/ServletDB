package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.Marks;
import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.pojo.StudyCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Класс оценок, работающий с базой данных
 */
public class MarksDAO implements I_MarksDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    /**
     * Добавить оценку в базу данных
     *
     * @param marks
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean addMarks(Marks marks) throws SQLException {
        if (marks == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO marks " +
                "(date, studycourse_id, lessons_id, student_id, mark) VALUES (?, ?, ?, ?, ?)");
        setStatementForAdd(marks, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForAdd(Marks marks, PreparedStatement statement) throws SQLException {
        statement.setDate(1, new java.sql.Date(marks.getDate().getTime())); /** преобразование даты из java.util.Date в java.sql.Date */
        statement.setInt(2, marks.getStudycourse_id());
        statement.setInt(3, marks.getLesson_id());
        statement.setInt(4, marks.getStudent_id());
        statement.setInt(5, marks.getMark().getIntValue());
    }

    /**
     * Получить оценку по id
     *
     * @param id
     * @return Marks
     * @throws SQLException
     */
    @Override
    public Marks getMarksById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM lessons AS les (les_id, les_topic, les_date, les_studycourse_id) " +
                "INNER JOIN marks ON les.les_id = marks.lessons_id " +
                "INNER JOIN student AS st (st_id, st_name, st_login, st_password, st_average_mark) " +
                "ON marks.student_id = st.st_id " +
                "INNER JOIN studycourse AS course (c_id, c_name, c_lecturer_id) " +
                "ON marks.studycourse_id = course.c_id " +
                "WHERE marks.id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Marks marks = getMarksFromResultset(resultSet);
        connection.close();
        return marks;
    }

    /**
     * Обработка resultSet
     */
    private Marks getMarksFromResultset(ResultSet resultSet) throws SQLException {
        if (resultSet == null) return null;
        Marks marks = null;
        if (resultSet.next()) {
            marks = new Marks(
                    resultSet.getInt("id"),
                    resultSet.getDate("date"),
                    resultSet.getInt("studycourse_id"),
                    resultSet.getInt("lessons_id"),
                    resultSet.getInt("student_id"),
                    resultSet.getInt("mark"));
            marks.setStudyCourse(new StudyCourse(
                    resultSet.getInt("c_id"),
                    resultSet.getString("c_name"),
                    resultSet.getInt("c_lecturer_id")));
            marks.setLesson(new Lesson(
                    resultSet.getInt("les_id"),
                    resultSet.getString("les_topic"),
                    resultSet.getDate("les_date"),
                    resultSet.getInt("les_studycourse_id")));
            marks.setStudent(new Student(
                    resultSet.getInt("st_id"),
                    resultSet.getString("st_name"),
                    resultSet.getString("st_login"),
                    resultSet.getString("st_password"),
                    resultSet.getFloat("st_average_mark")));
        }
        return marks;
    }

    /**
     * Получить список всех оценок
     *
     * @return ArrayList<Marks>
     * @throws SQLException
     */
    @Override
    public ArrayList<Marks> getAllMarks() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM marks");
        ArrayList<Marks> marksArrayList = getMarksListFromResultset(resultSet);
        connection.close();
        return marksArrayList;
    }

    /**
     * Получить список оценок по лекции
     *
     * @param lesson
     * @return ArrayList<Marks>
     * @throws SQLException
     */
    @Override
    public ArrayList<Marks> getMarksByLesson(Lesson lesson) throws SQLException {
        if (lesson == null) return new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM marks WHERE lessons_id = ?");
        statement.setInt(1, lesson.getId());
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Marks> marksArrayList = getMarksListFromResultset(resultSet);
        connection.close();
        return marksArrayList;
    }

    /**
     * Обработка resultSet
     */
    private ArrayList<Marks> getMarksListFromResultset(ResultSet resultSet) throws SQLException {
        ArrayList<Marks> marksArrayList = new ArrayList<>();
        Marks marks;
        while (resultSet.next()) {
            marks = new Marks(
                    resultSet.getInt("id"),
                    resultSet.getDate("date"),
                    resultSet.getInt("studycourse_id"),
                    resultSet.getInt("lessons_id"),
                    resultSet.getInt("student_id"),
                    resultSet.getInt("mark"));
            marksArrayList.add(marks);
        }
        return marksArrayList;
    }

    /**
     * Получить список оценок по студенту
     *
     * @param student
     * @return ArrayList<Marks>
     * @throws SQLException
     */
    @Override
    public ArrayList<Marks> getMarksByStudent(Student student) throws SQLException {
        if (student == null) return new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM marks WHERE student_id= ?");
        statement.setInt(1, student.getId());
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Marks> marksArrayList = getMarksListFromResultset(resultSet);
        connection.close();
        return marksArrayList;
    }

    /**
     * Получить список оценок по дате
     *
     * @param date
     * @return ArrayList<Marks>
     * @throws SQLException
     */
    @Override
    public ArrayList<Marks> getMarksByDate(Date date) throws SQLException {
        if (date == null) return new ArrayList<>();
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM marks WHERE marks.date= ?");
        statement.setDate(1, new java.sql.Date(date.getTime()));
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Marks> marksArrayList = getMarksListFromResultset(resultSet);
        connection.close();
        return marksArrayList;
    }

    /**
     * Обновить оценку в базе данных
     *
     * @param marks
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean updateMarks(Marks marks) throws SQLException {
        if (marks == null) return false;
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE marks " +
                "SET date = ?, studycourse_id = ?, lessons_id = ?, student_id = ?, mark = ? WHERE id = ?");
        setStatementForUpdate(marks, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }

    /**
     * Обработка statement
     */
    private void setStatementForUpdate(Marks marks, PreparedStatement statement) throws SQLException {
        statement.setDate(1, new java.sql.Date(marks.getDate().getTime()));
        statement.setInt(2, marks.getStudycourse_id());
        statement.setInt(3, marks.getLesson_id());
        statement.setInt(4, marks.getStudent_id());
        statement.setInt(5, marks.getMark().getIntValue());
        statement.setInt(6, marks.getId());
    }

    /**
     * Удалить оценку из базы данных
     *
     * @param id
     * @return boolean результат выполнения метода
     * @throws SQLException
     */
    @Override
    public boolean deleteMarksById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM marks " +
                "WHERE id = ?");
        statement.setInt(1, id);
        int countRow = statement.executeUpdate();
        connection.close();
        return countRow > 0;
    }
}
