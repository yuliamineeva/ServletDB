package ru.innopolis.stc9.servlet1;

import ru.innopolis.stc9.servlet1.db.dao.*;
import ru.innopolis.stc9.servlet1.pojo.Admin;
import ru.innopolis.stc9.servlet1.service.EducationalService;
import ru.innopolis.stc9.servlet1.service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        I_StudentDAO studentDAO = new StudentDAO();
        I_UserDAO userDAO = new UserDAO();
        I_LecturerDAO lecturerDAO = new LecturerDAO();
        I_AdminDAO adminDAO = new AdminDAO();
        UserService userService = new UserService();
        EducationalService educationalService = new EducationalService();

//        try {
//            Student st = studentDAO.getStudentById(5);
//            System.out.println(st);
//            System.out.println("1--------------");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//
//        List<Student> students = educationalService.getAllStudents();
//        for (Student student : students) {
//            System.out.println(student.toString());
//        }
//        System.out.println("2--------------");
//
//        try {
//            User user1 = userDAO.getUserById(3);
//            System.out.println(user1);
//            User user2 = userDAO.getUserByLogin("admin");
//            System.out.println(user2);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("3--------------");

//        try {
//            List<User> users2 =userDAO.getAllUsersByRole("student");
//            for (User user : users2) {
//                System.out.println(user.toString());
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("выше по роли список--------------");

//        List<User> users = educationalService.getAllUsers();
//        System.out.println(users.size());
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
//
//        System.out.println("выше весь список--------------");

//        Lecturer lec3 = null;
//        Lecturer lec1 = null;
//        try {
//            lec3 = lecturerDAO.getLecturerById(2);
//            lec1 = lecturerDAO.getLecturerByLogin("lvov");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(lec3);
//        System.out.println(lec1);
//        System.out.println("лекторы--------------");

//        String login = "admin";
//        String password = "admin";
//        if (userService.checkAuth(login, password)) {
//            int role = userService.getRole(login);
//            String userInfo = userService.getUsersFieldFromDB(login);
//            System.out.println("role " + role);
//            System.out.println(userInfo);
//        }
//
//        List<Lecturer> lecturers = educationalService.getAllLecturers();
//        for (Lecturer lecturer : lecturers) {
//            System.out.println(lecturer.toString());
//        }
//        System.out.println("лекторы список--------------");

//        try {
//            Admin adm1 = adminDAO.getAdminById(2);
//            System.out.println(adm1);
//            Admin adm2 = adminDAO.getAdminByLogin("admin");
//            System.out.println(adm2);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        List<Admin> admins = educationalService.getAllAdmins();
        for (Admin admin : admins) {
            System.out.println(admin.toString());
        }


    }
}
