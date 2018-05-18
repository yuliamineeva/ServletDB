package ru.innopolis.stc9.servlet1.service;

import ru.innopolis.stc9.servlet1.db.dao.I_MarksDAO;
import ru.innopolis.stc9.servlet1.db.dao.MarksDAO;
import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.Marks;

import java.sql.SQLException;
import java.util.List;

public class MarksService {
    I_MarksDAO iMarksDAO = new MarksDAO();

    public List<Marks> getMarksByLessonID(int id) {
        try {
            return iMarksDAO.getMarksByLesson(new Lesson(id, null, null, 0));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
