package ru.innopolis.stc9.servlet1.service;

import ru.innopolis.stc9.servlet1.db.dao.*;
import ru.innopolis.stc9.servlet1.pojo.Admin;
import ru.innopolis.stc9.servlet1.pojo.Lecturer;
import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.pojo.User;

import java.sql.SQLException;

public class UserService {
    private static I_UserDAO userDao = new UserDAO();
    private I_AdminDAO adminDAO = new AdminDAO();
    private I_LecturerDAO lecturerDAO = new LecturerDAO();
    private I_StudentDAO studentDAO = new StudentDAO();

    public boolean checkAuth(String login, String password) {
        User user = null;
        String passwordFromBD = null;
        try {
            user = userDao.getUserByLogin(login);
            passwordFromBD = getPasswordFromDB(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user != null) && (passwordFromBD.equals(password));
    }

    private String getPasswordFromDB(User user) throws SQLException {
        String passwordFromBD = null;
        if (user != null) {
            String login = user.getLogin();
            int role = user.getRole_number();
            switch (role) {
                case 1:
                    Admin admin = adminDAO.getAdminByLogin(login);
                    passwordFromBD = admin.getPassword();
                    break;
                case 2:
                    Lecturer lecturer = lecturerDAO.getLecturerByLogin(login);
                    passwordFromBD = lecturer.getPassword();
                    break;
                case 3:
                    Student student = studentDAO.getStudentByLogin(login);
                    passwordFromBD = student.getPassword();
                    break;
            }
        }
        return passwordFromBD;
    }

    public int getRole(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user != null) ? user.getRole_number() : 0;
    }
}
