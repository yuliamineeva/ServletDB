package ru.innopolis.stc9.servlet1.service;

import ru.innopolis.stc9.servlet1.db.dao.I_StudentDAO;
import ru.innopolis.stc9.servlet1.db.dao.StudentDAO;
import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.SQLException;
import java.util.List;

public class EducationalService {
    I_StudentDAO iStudentDAO = new StudentDAO();

    public List<Student> getAllStudents(String table) {
        try {
            return iStudentDAO.getAllStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
