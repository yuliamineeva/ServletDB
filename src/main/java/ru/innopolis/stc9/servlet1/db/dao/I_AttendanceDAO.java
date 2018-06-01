package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Attendance;
import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface I_AttendanceDAO {
    boolean addAttendance(Attendance attendance) throws SQLException;

    Attendance getAttendanceById(int id) throws SQLException;

    ArrayList<Attendance> getAllAttendance() throws SQLException;

    ArrayList<Attendance> getAttendanceByLesson(Lesson lesson) throws SQLException;

    ArrayList<Attendance> getAttendanceByStudent(Student student) throws SQLException;

    ArrayList<Attendance> getAttendanceByDate(Date date) throws SQLException;

    boolean updateAttendance(Attendance attendance) throws SQLException;

    boolean deleteAttendanceById(int id) throws SQLException;
}
