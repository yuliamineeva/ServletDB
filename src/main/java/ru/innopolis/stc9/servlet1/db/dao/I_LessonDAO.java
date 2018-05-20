package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.StudyCourse;

import java.sql.SQLException;
import java.util.ArrayList;

public interface I_LessonDAO {
    public boolean addLesson(Lesson lesson) throws SQLException;

    public Lesson getLessonById(int id) throws SQLException;

    public ArrayList<Lesson> getAllLessons() throws SQLException;

    public ArrayList<Lesson> getLessonsByStudyCourse(StudyCourse studyCourse) throws SQLException;

    public boolean updateLesson(Lesson lesson) throws SQLException;

    public boolean deleteLessonById(int id) throws SQLException;
}
