package ru.innopolis.stc9.servlet1.service;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlet1.db.connectionManager.CryptoUtils;
import ru.innopolis.stc9.servlet1.db.dao.*;
import ru.innopolis.stc9.servlet1.pojo.*;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    private static I_UserDAO userDao = new UserDAO();
    private I_AdminDAO adminDAO = new AdminDAO();
    private I_LecturerDAO lecturerDAO = new LecturerDAO();
    private I_StudentDAO studentDAO = new StudentDAO();
    I_MarksDAO marksDAO = new MarksDAO();
    private final static Logger logger = Logger.getLogger(AdminDAO.class);

    public User getUserByLogin(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Student getStudentByLogin(String login) {
        Student student = null;
        try {
            student = studentDAO.getStudentByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public boolean checkAuth(String login, String password) {
        User user = null;
        String passwordFromBD = null;
        String hashPassword = null;
        try {
            user = userDao.getUserByLogin(login);
            passwordFromBD = getPasswordFromDB(user);
            hashPassword = CryptoUtils.byteArrayToHexString(CryptoUtils.computeHash(password));
        } catch (SQLException e) {
            logger.error("Error trying get User from DB", e);
        } catch (NoSuchAlgorithmException e) {
            logger.error("Error trying to crypto password", e);
        }
        return (user != null) && (passwordFromBD.equals(hashPassword));
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

    public String getUsersFieldFromDB(String login) {
        String userInfo = null;
        User user = getUserByLogin(login);
        if (user != null) {
            int role = user.getRole_number();
            switch (role) {
                case 1:
                    Admin admin = null;
                    try {
                        admin = adminDAO.getAdminByLogin(login);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    userInfo = admin.toString();
                    break;
                case 2:
                    Lecturer lecturer = null;
                    try {
                        lecturer = lecturerDAO.getLecturerByLogin(login);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    userInfo = lecturer.toString();
                    break;
                case 3:
                    Student student = null;
                    try {
                        student = studentDAO.getStudentByLogin(login);
                        student.setAverageMark(calculateAverageMark(student));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    userInfo = student.toString();
                    break;
            }
        }
        return userInfo;
    }

    public int getRole(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            logger.error("Error trying get Role from DB", e);
        }
        return (user != null) ? user.getRole_number() : 0;
    }

    public float calculateAverageMark(Student student) {
        ArrayList<Marks> marksArrayList = null;
        try {
            marksArrayList = marksDAO.getMarksByStudent(student);
        } catch (SQLException e) {
            logger.error("Error trying get marks from DB", e);
        }
        int countOfMarks = 0;
        int summOfMarks = 0;
        for (Marks marks : marksArrayList) {
            if (marks.getMark() != null) {
                countOfMarks++;
                summOfMarks += marks.getMark().getIntValue();
            }
        }
        float averageMark = 0;
        if (countOfMarks != 0) {
            averageMark = (float) summOfMarks / countOfMarks;
        }
        student.setAverageMark(averageMark);
        return averageMark;
    }

    //todo в методах add добавить добавление в таблицу user в сервисах и проверку, нет ли такого логина
}
