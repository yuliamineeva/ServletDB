package ru.innopolis.stc9.servlet1.controller;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.service.AdminService;
import ru.innopolis.stc9.servlet1.service.EducationalService;
import ru.innopolis.stc9.servlet1.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class HelloController extends HttpServlet {
    private final static Logger logger = Logger.getLogger(HelloController.class);
    private EducationalService educationalService = new EducationalService();
    private AdminService adminService = new AdminService();
    private StudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
//        if ("adminTest".equals(action)) {
//            adminService.insertAdminTest();
//        }
        if ("sTest".equals(action)) {
            studentService.updateStudentTest();
        }
        resp.setCharacterEncoding("Cp1251"); // Для отображение русских букв в браузере. Кодировка "UTF-8" не подошла
        resp.getWriter().println("База данных успеваемости студентов\n" + "Database of student performance");
        String table = req.getParameter("table");
        if (table.equals("student")) {
            List<Student> students = educationalService.getAllStudents(table);
            for (Student student : students) {
                resp.getWriter().println(student.getId() + " " + student.getName());
                logger.info(student.getId() + " " + student.getName());
            }
        } else {
            resp.getWriter().println("The table is not found");
            logger.info("The table is not found");
        }
    }
}

