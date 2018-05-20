package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.pojo.Attendance;
import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class AttendanceDAO implements I_AttendanceDAO {
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();

    @Override
    public boolean addAttendance(Attendance attendance) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO attendance " +
                "(date, lessons_id, student_id, be_present) VALUES (?, ?, ?, ?)");
        setStatementForAdd(attendance, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForAdd(Attendance attendance, PreparedStatement statement) throws SQLException {
        statement.setDate(1, new java.sql.Date(attendance.getDate().getTime())); /** преобразование даты из java.util.Date в java.sql.Date */
        statement.setInt(2, attendance.getLesson_id());
        statement.setInt(3, attendance.getStudent_id());
        statement.setBoolean(4, attendance.isBe_present());
    }

    @Override
    public Attendance getAttendanceById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM lessons AS les (les_id, les_topic, les_date, les_studycourse_id) " +
                "INNER JOIN attendance ON les.les_id = attendance.lessons_id " +
                "INNER JOIN student AS st (st_id, st_name, st_login, st_password, st_average_mark) " +
                "ON attendance.student_id = st.st_id " +
                "WHERE attendance.id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Attendance attendance = getAttendanceFromResultset(resultSet);
        connection.close();
        return attendance;
    }

    private Attendance getAttendanceFromResultset(ResultSet resultSet) throws SQLException {
        Attendance attendance = null;
        if (resultSet.next()) {
            attendance = new Attendance(
                    resultSet.getInt("id"),
                    resultSet.getDate("date"),
                    resultSet.getInt("lessons_id"),
                    new Lesson(
                            resultSet.getInt("les_id"),
                            resultSet.getString("les_topic"),
                            resultSet.getDate("les_date"),
                            resultSet.getInt("les_studycourse_id")),
                    resultSet.getInt("student_id"),
                    new Student(
                            resultSet.getInt("st_id"),
                            resultSet.getString("st_name"),
                            resultSet.getString("st_login"),
                            resultSet.getString("st_password"),
                            resultSet.getFloat("st_average_mark")),
                    resultSet.getBoolean("be_present"));
        }
        return attendance;
    }

    @Override
    public ArrayList<Attendance> getAllAttendance() throws SQLException {
        Connection connection = connectionManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * " +
                "FROM attendance");
        ArrayList<Attendance> attArrayList = getAttendanceListFromResultset(resultSet);
        connection.close();
        return attArrayList;
    }

    @Override
    public ArrayList<Attendance> getAttendanceByLesson(Lesson lesson) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM attendance WHERE lessons_id = ?");
        statement.setInt(1, lesson.getId());
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Attendance> attArrayList = getAttendanceListFromResultset(resultSet);
        connection.close();
        return attArrayList;
    }

    private ArrayList<Attendance> getAttendanceListFromResultset(ResultSet resultSet) throws SQLException {
        ArrayList<Attendance> attArrayList = new ArrayList<>();
        Attendance attendance = null;
        while (resultSet.next()) {
            attendance = new Attendance(
                    resultSet.getInt("id"),
                    resultSet.getDate("date"),
                    resultSet.getInt("lessons_id"),
                    resultSet.getInt("student_id"),
                    resultSet.getBoolean("be_present"));
            attArrayList.add(attendance);
        }
        return attArrayList;
    }

    @Override
    public ArrayList<Attendance> getAttendanceByStudent(Student student) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM attendance WHERE student_id = ?");
        statement.setInt(1, student.getId());
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Attendance> attArrayList = getAttendanceListFromResultset(resultSet);
        connection.close();
        return attArrayList;
    }

    @Override
    public ArrayList<Attendance> getAttendanceByDate(Date date) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * " +
                "FROM attendance WHERE attendance.date = ?");
        statement.setDate(1, new java.sql.Date(date.getTime()));
        ResultSet resultSet = statement.executeQuery();
        ArrayList<Attendance> attArrayList = getAttendanceListFromResultset(resultSet);
        connection.close();
        return attArrayList;
    }

    @Override
    public boolean updateAttendance(Attendance attendance) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE attendance " +
                "SET date = ?, lessons_id = ?, student_id = ?, be_present = ? WHERE id = ?");
        setStatementForUpdate(attendance, statement);
        int countRow = statement.executeUpdate();
        connection.close();
        if (countRow > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void setStatementForUpdate(Attendance attendance, PreparedStatement statement) throws SQLException {
        statement.setDate(1, new java.sql.Date(attendance.getDate().getTime()));
        statement.setInt(2, attendance.getLesson_id());
        statement.setInt(3, attendance.getStudent_id());
        statement.setBoolean(4, attendance.isBe_present());
        statement.setInt(5, attendance.getId());
    }

    @Override
    public boolean deleteAttendanceById(int id) throws SQLException {
        Connection connection = connectionManager.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM attendance " +
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
