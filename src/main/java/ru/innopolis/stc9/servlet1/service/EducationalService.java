package ru.innopolis.stc9.servlet1.service;

import ru.innopolis.stc9.servlet1.db.dao.*;
import ru.innopolis.stc9.servlet1.pojo.Admin;
import ru.innopolis.stc9.servlet1.pojo.Lecturer;
import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.pojo.User;

import java.sql.SQLException;
import java.util.List;

public class EducationalService {
    I_StudentDAO studentDAO = new StudentDAO();
    I_UserDAO userDAO = new UserDAO();
    I_LecturerDAO lecturerDAO = new LecturerDAO();
    I_AdminDAO adminDao = new AdminDAO();


    public List<Student> getAllStudents() {
        try {
            return studentDAO.getAllStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lecturer> getAllLecturers() {
        try {
            return lecturerDAO.getAllLecturers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Admin> getAllAdmins() {
        try {
            return adminDao.getAllAdmins();
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
