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

public class MarksDAO implements I_MarksDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    @Override
    public boolean addMarks(Marks marks) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO marks " +
                "(date, studycourse_id, lessons_id, student_id, mark) VALUES (?, ?, ?, ?, ?)");
        setStatementForAdd(marks, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForAdd(Marks marks, PreparedStatement statement) throws SQLException {
        statement.setDate(1, new java.sql.Date(marks.getDate().getTime())); /** преобразование даты из java.util.Date в java.sql.Date */
        statement.setInt(2, marks.getStudycourse_id());
        statement.setInt(3, marks.getLesson_id());
        statement.setInt(4, marks.getStudent_id());
        statement.setInt(5, marks.getMark().getIntValue());
    }

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

    private Marks getMarksFromResultset(ResultSet resultSet) throws SQLException {
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

    @Override
    public ArrayList<Marks> getMarksByLesson(Lesson lesson) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM marks WHERE lessons_id = ?");
        statement.setInt(1, lesson.getId());
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Marks> marksArrayList = getMarksListFromResultset(resultSet);
        connection.close();
        return marksArrayList;
    }

    private ArrayList<Marks> getMarksListFromResultset(ResultSet resultSet) throws SQLException {
        ArrayList<Marks> marksArrayList = new ArrayList<>();
        Marks marks = null;
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

    @Override
    public ArrayList<Marks> getMarksByStudent(Student student) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM marks WHERE student_id= ?");
        statement.setInt(1, student.getId());
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Marks> marksArrayList = getMarksListFromResultset(resultSet);
        connection.close();
        return marksArrayList;
    }

    @Override
    public ArrayList<Marks> getMarksByDate(Date date) throws SQLException {
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
     * todo перенести в сервис calculateAverageMark ???
     */
    @Override
    public float calculateAverageMark(Student student) throws SQLException {
        ArrayList<Marks> marksArrayList = getMarksByStudent(student);
        int countOfMarks = 0;
        int summOfMarks = 0;
        for (Marks marks : marksArrayList) {
            if (marks.getMark() != null) {
                countOfMarks++;
                summOfMarks += marks.getMark().getIntValue();
            }
        }
        float averageMark = (float) summOfMarks / countOfMarks;
        return averageMark;
    }

//    @Override
//    public float calculateAverageMark(Student student) throws SQLException {
//        Connection connection = connectionManager.getConnection();
//        PreparedStatement statement = connection.prepareStatement("SELECT * " +
//                "FROM marks WHERE student_id= ?");
//        statement.setInt(1, student.getId());
//        ResultSet resultSet = statement.executeQuery();
//        ArrayList<Marks> marksArrayList = new ArrayList<>();
//        Marks marks = null;
//        int countOfMarks = 0;
//        int summOfMarks = 0;
//        while (resultSet.next()) {
//            marks = new Marks(
//                    resultSet.getInt("id"),
//                    resultSet.getDate("date"),
//                    resultSet.getInt("studycourse_id"),
//                    resultSet.getInt("lessons_id"),
//                    resultSet.getInt("student_id"),
//                    resultSet.getInt("mark"));
//            marksArrayList.add(marks);
//            if (marks.getMark() != null) {
//                countOfMarks++;
//                summOfMarks += marks.getMark().getIntValue();
//            }
//        }
//        connection.close();
//        float averageMark = (float) summOfMarks / countOfMarks;
//        return averageMark;
//    }

    @Override
    public boolean updateMarks(Marks marks) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE marks " +
                "SET date = ?, studycourse_id = ?, lessons_id = ?, student_id = ?, mark = ? WHERE id = ?");
        setStatementForUpdate(marks, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForUpdate(Marks marks, PreparedStatement statement) throws SQLException {
        statement.setDate(1, new java.sql.Date(marks.getDate().getTime()));
        statement.setInt(2, marks.getStudycourse_id());
        statement.setInt(3, marks.getLesson_id());
        statement.setInt(4, marks.getStudent_id());
        statement.setInt(5, marks.getMark().getIntValue());
        statement.setInt(6, marks.getId());
    }

    @Override
    public boolean deleteMarksById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM marks " +
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
