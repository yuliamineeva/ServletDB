package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Lecturer;
import ru.innopolis.stc9.servlet1.pojo.StudyCourse;

import java.sql.SQLException;
import java.util.ArrayList;

public interface I_StudyCourseDAO {
    public boolean addStudyCourse(StudyCourse studyCourse) throws SQLException;

    public StudyCourse getStudyCourseById(int id) throws SQLException;

    public ArrayList<StudyCourse> getStudyCourseByLecturer(Lecturer lecturer) throws SQLException;

    public boolean updateStudyCourse(StudyCourse studyCourse) throws SQLException;

    public boolean deleteStudyCourseById(int id) throws SQLException;
}
