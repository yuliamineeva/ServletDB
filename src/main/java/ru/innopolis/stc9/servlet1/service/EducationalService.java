package ru.innopolis.stc9.servlet1.service;

import ru.innopolis.stc9.servlet1.db.dao.I_StudentDAO;
import ru.innopolis.stc9.servlet1.db.dao.I_UserDAO;
import ru.innopolis.stc9.servlet1.db.dao.StudentDAO;
import ru.innopolis.stc9.servlet1.db.dao.UserDAO;
import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.pojo.User;

import java.sql.SQLException;
import java.util.List;

public class EducationalService {
    I_StudentDAO studentDAO = new StudentDAO();
    I_UserDAO userDAO = new UserDAO();

    public List<Student> getAllStudents() {
        try {
            return studentDAO.getAllStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
