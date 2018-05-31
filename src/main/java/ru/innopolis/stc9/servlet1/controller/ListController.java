package ru.innopolis.stc9.servlet1.controller;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlet1.pojo.*;
import ru.innopolis.stc9.servlet1.service.EducationalService;
import ru.innopolis.stc9.servlet1.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class ListController extends HttpServlet {
    private final static Logger logger = Logger.getLogger(ListController.class);
    private EducationalService educationalService = new EducationalService();
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("Cp1251"); // Для отображение русских букв в браузере. Кодировка "UTF-8" не подошла
        String login = String.valueOf(req.getSession().getAttribute("login"));
        Student currentStudent = userService.getStudentByLogin(login);
        req.getSession().setAttribute("student", currentStudent);
        Object role = req.getSession().getAttribute("role");

        List<Lesson> lessons = educationalService.getAllLessons();
        req.setAttribute("lessons", lessons);
        Set<Date> dates = educationalService.getAllDatesFromLessons();
        req.setAttribute("dates", dates);
        List<Student> students = educationalService.getAllStudents();
        req.setAttribute("students", students);
        List<Lecturer> lecturers = educationalService.getAllLecturers();
        req.setAttribute("lecturers", lecturers);
        List<User> users = educationalService.getAllUsers();
        req.setAttribute("users", users);

        String listType = req.getParameter("listType");
        if ("students".equals(listType)) {
            if (role != null && (Integer) role != 3) {
                req.getRequestDispatcher("/students.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }
        if ("users".equals(listType)) {
            if (role != null && (Integer) role == 1) {
                req.getRequestDispatcher("/users.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }
        if ("lecturers".equals(listType)) {
            if (role != null) {
                req.getRequestDispatcher("/lecturers.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }
        if ("studyCourses".equals(listType)) {
            List<StudyCourse> studyCourses = educationalService.getAllStudyCourse();
            req.setAttribute("studyCourses", studyCourses);
            if (role != null) {
                req.getRequestDispatcher("/courses.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }

        if ("lessons".equals(listType)) {
            if (role != null) {
                req.getRequestDispatcher("/lessons.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }

        if ("attendance".equals(listType)) {
            List<Attendance> attList = educationalService.getAllAttendance();
            req.setAttribute("attendance", attList);
            if (role != null && (Integer) role != 3) {
                String chooseLess = req.getParameter("less_attendance");
                String chooseDate = req.getParameter("date");
                String chooseStudent = req.getParameter("student");
                attList = educationalService.chooseAttendanceList(chooseLess, chooseDate, chooseStudent);
                req.setAttribute("attendance", attList);
                logger.info("chooseLess = " + chooseLess + " , chooseDate = " + chooseDate + " , chooseStudent = " + chooseStudent);
                req.getRequestDispatcher("/attendance.jsp").forward(req, resp);
            } else if ((Integer) role == 3) {
                attList = educationalService.getAttendanceByStudent(currentStudent.getId());
                req.setAttribute("attendance", attList);
                req.getRequestDispatcher("/attendance.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }

        if ("marks".equals(listType)) {
            List<Marks> marks = educationalService.getAllMarks();
            req.getSession().setAttribute("marks", marks);
            if (role != null && (Integer) role != 3) {
                req.getRequestDispatcher("/marks.jsp").forward(req, resp);
            } else if ((Integer) role == 3) {
                marks = educationalService.getMarksByStudent(currentStudent);
                float averageMark = userService.calculateAverageMark(currentStudent);
                System.out.println(averageMark);
                req.getSession().setAttribute("marks", marks);
                req.getSession().setAttribute("averageMark", averageMark);
                req.getRequestDispatcher("/marks.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }

    }
}

