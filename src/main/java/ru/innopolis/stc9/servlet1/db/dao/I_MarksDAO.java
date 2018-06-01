package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.Marks;
import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface I_MarksDAO {
    boolean addMarks(Marks marks) throws SQLException;

    Marks getMarksById(int id) throws SQLException;

    ArrayList<Marks> getAllMarks() throws SQLException;

    ArrayList<Marks> getMarksByLesson(Lesson lesson) throws SQLException;

    ArrayList<Marks> getMarksByStudent(Student student) throws SQLException;

    ArrayList<Marks> getMarksByDate(Date date) throws SQLException;

    boolean updateMarks(Marks marks) throws SQLException;

    boolean deleteMarksById(int id) throws SQLException;
}
