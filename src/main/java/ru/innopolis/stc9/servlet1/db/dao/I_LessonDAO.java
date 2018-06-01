package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.StudyCourse;

import java.sql.SQLException;
import java.util.ArrayList;

public interface I_LessonDAO {
    boolean addLesson(Lesson lesson) throws SQLException;

    Lesson getLessonById(int id) throws SQLException;

    ArrayList<Lesson> getAllLessons() throws SQLException;

    ArrayList<Lesson> getLessonsByStudyCourse(StudyCourse studyCourse) throws SQLException;

    boolean updateLesson(Lesson lesson) throws SQLException;

    boolean deleteLessonById(int id) throws SQLException;
}
