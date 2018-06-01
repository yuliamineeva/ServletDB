package ru.innopolis.stc9.servlet1;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlet1.controller.LoginController;
import ru.innopolis.stc9.servlet1.db.dao.*;
import ru.innopolis.stc9.servlet1.pojo.Admin;
import ru.innopolis.stc9.servlet1.pojo.Lecturer;
import ru.innopolis.stc9.servlet1.pojo.Role;
import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.service.EducationalService;
import ru.innopolis.stc9.servlet1.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static EducationalService educationalService = new EducationalService();
    public static UserService userService = new UserService();
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        I_UserDAO userDAO = new UserDAO();

        logger.info("Received request for Main");
        LoginController loginController = new LoginController();


        //        Lesson lesson = new Lesson(1, "Lesson1", new Date(), 1);
//        Student student = new Student(2,"Ivan", "loginI", "passw", 0);
//        Attendance attendance = new Attendance(1, new Date(), 1, lesson, 2, student, true);
//        System.out.println(attendance);

//        try {
//            checkAdmin();
//            checkLecturer();
//            checkStudent();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        checkRole();
    }

    public static void checkAdmin() throws SQLException {
        System.out.println("------Admin----------");
        I_AdminDAO adminDAO = new AdminDAO();

        System.out.println("\n addAdmin");
        Admin admin1 = new Admin("admin3 name", "admin3", "admin3");
        System.out.println(adminDAO.addAdmin(admin1));

        System.out.println("\n updateAdmin");
        Admin admin2 = new Admin(1, "Минеева Юлия Николаевна", "admin", "admin");
        System.out.println(adminDAO.updateAdmin(admin2));

        System.out.println("\n getAllAdmins");
        List<Admin> admins = educationalService.getAllAdmins();
        for (Admin admin : admins) {
            System.out.println(admin.toString());
        }
        System.out.println("----------");
    }

    public static void checkLecturer() throws SQLException {
        System.out.println("------Lecturer----------");
        I_LecturerDAO lecturerDAO = new LecturerDAO();

        System.out.println("\n addLecturer");
        Lecturer lecturer1 = new Lecturer("Зайцев Николай Петрович", "zaycev", "zaycev");
        System.out.println(lecturerDAO.addLecturer(lecturer1));

        System.out.println("\n updateLecturer");
        Lecturer lecturer2 = new Lecturer(1, "Львов Сергей Петрович", "lvov", "lvov");
        System.out.println(lecturerDAO.updateLecturer(lecturer2));

        System.out.println("\n updateLecturer");
        Lecturer lecturer3 = new Lecturer(2, "Тополев Алексей Иванович", "topolev", "topolev");
        System.out.println(lecturerDAO.updateLecturer(lecturer3));

        System.out.println("\n getAllLecturers");
        List<Lecturer> lecturers = educationalService.getAllLecturers();
        for (Lecturer lecturer : lecturers) {
            System.out.println(lecturer.toString());
        }
        System.out.println("----------");
    }

    public static void checkStudent() throws SQLException {
        System.out.println("------Student----------");
        I_StudentDAO studentDAO = new StudentDAO();

        System.out.println("\n addStudent");
        Student student1 = new Student("Гусева Василина Макаровна", "student", "student");
        System.out.println(studentDAO.addStudent(student1));

        System.out.println("\n updateStudent");
        Student student2 = new Student(1, "Иванов Иван Иванович", "ivanov", "ivanov", 0);
        System.out.println(studentDAO.updateStudent(student2));

        System.out.println("\n getAllStudents");
        List<Student> students = educationalService.getAllStudents();
        for (Student student : students) {
            System.out.println(student.toString());
        }
        System.out.println("----------");
    }

    public static void checkRole() {
        System.out.println("------Role----------");
        I_RoleDAO roleDAO = new RoleDAO();

        System.out.println("\n getAllRoles");
        List<Role> roles = educationalService.getAllRoles();
        for (Role role : roles) {
            System.out.println(role.toString());
        }

        System.out.println("\n getRoleById");
        try {
            Role role1 = roleDAO.getRoleById(1);
            System.out.println(role1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
