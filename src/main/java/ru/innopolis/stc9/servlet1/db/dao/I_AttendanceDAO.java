package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Attendance;
import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface I_AttendanceDAO {
    public boolean addAttendance(Attendance attendance) throws SQLException;

    public Attendance getAttendanceById(int id) throws SQLException;

    public ArrayList<Attendance> getAttendanceByLesson(Lesson lesson) throws SQLException;

    public ArrayList<Attendance> getAttendanceByStudent(Student student) throws SQLException;

    public ArrayList<Attendance> getAttendanceByDate(Date date) throws SQLException;

    public boolean updateAttendance(Attendance attendance) throws SQLException;

    public boolean deleteAttendanceById(int id) throws SQLException;
}
