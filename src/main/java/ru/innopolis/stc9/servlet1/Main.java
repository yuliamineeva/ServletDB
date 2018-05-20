package ru.innopolis.stc9.servlet1;

import ru.innopolis.stc9.servlet1.db.dao.I_StudentDAO;
import ru.innopolis.stc9.servlet1.db.dao.I_UserDAO;
import ru.innopolis.stc9.servlet1.db.dao.StudentDAO;
import ru.innopolis.stc9.servlet1.db.dao.UserDAO;
import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.pojo.User;
import ru.innopolis.stc9.servlet1.service.EducationalService;
import ru.innopolis.stc9.servlet1.service.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        I_StudentDAO studentDAO = new StudentDAO();
        I_UserDAO userDAO = new UserDAO();
        UserService userService = new UserService();
        EducationalService educationalService = new EducationalService();

        List<Student> students = educationalService.getAllStudents();

        for (Student student : students) {
            System.out.println(student.toString());
        }

        List<User> users = educationalService.getAllUsers();
        System.out.println(users.size());
        for (User user : users) {
            System.out.println(user.toString());
        }
    }
}
