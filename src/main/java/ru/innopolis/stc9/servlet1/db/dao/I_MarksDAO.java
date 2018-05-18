package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.Marks;
import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface I_MarksDAO {
    public boolean addMarks(Marks marks) throws SQLException;

    public Marks getMarksById(int id) throws SQLException;

    public ArrayList<Marks> getMarksByLesson(Lesson lesson) throws SQLException;

    public ArrayList<Marks> getMarksByStudent(Student student) throws SQLException;

    public float calculateAverageMark(Student student) throws SQLException;

    public ArrayList<Marks> getMarksByDate(Date date) throws SQLException;

    public boolean updateMarks(Marks marks) throws SQLException;

    public boolean deleteMarksById(int id) throws SQLException;
}
