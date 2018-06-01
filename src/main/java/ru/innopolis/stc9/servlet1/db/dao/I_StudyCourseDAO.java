package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Lecturer;
import ru.innopolis.stc9.servlet1.pojo.StudyCourse;

import java.sql.SQLException;
import java.util.ArrayList;

public interface I_StudyCourseDAO {
    boolean addStudyCourse(StudyCourse studyCourse) throws SQLException;

    StudyCourse getStudyCourseById(int id) throws SQLException;

    ArrayList<StudyCourse> getAllStudyCourse() throws SQLException;

    ArrayList<StudyCourse> getStudyCourseByLecturer(Lecturer lecturer) throws SQLException;

    boolean updateStudyCourse(StudyCourse studyCourse) throws SQLException;

    boolean deleteStudyCourseById(int id) throws SQLException;
}
