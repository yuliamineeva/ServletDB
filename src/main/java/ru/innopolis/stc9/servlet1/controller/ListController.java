package ru.innopolis.stc9.servlet1.controller;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlet1.pojo.*;
import ru.innopolis.stc9.servlet1.service.EducationalService;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("Cp1251"); // Для отображение русских букв в браузере. Кодировка "UTF-8" не подошла
//todo установить атрибуты за пределами if
        List<Lesson> lessons = educationalService.getAllLessons();
        req.setAttribute("lessons", lessons);
        Set<Date> dates = educationalService.getAllDatesFromLessons();
        req.setAttribute("dates", dates);
        List<Student> students = educationalService.getAllStudents();
        req.setAttribute("students", students);

        String listType = req.getParameter("listType");
        if ("students".equals(listType)) {
//            List<Student> students = educationalService.getAllStudents();
//            req.setAttribute("students", students);
            if (req.getSession().getAttribute("role") != null &&
                    (Integer) req.getSession().getAttribute("role") != 3) {
                req.getRequestDispatcher("/students.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }
        if ("users".equals(listType)) {
            List<User> users = educationalService.getAllUsers();
            req.setAttribute("users", users);
            if (req.getSession().getAttribute("role") != null &&
                    (Integer) req.getSession().getAttribute("role") == 1) {
                req.getRequestDispatcher("/users.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }
        if ("lecturers".equals(listType)) {
            List<Lecturer> lecturers = educationalService.getAllLecturers();
            req.setAttribute("lecturers", lecturers);
            if (req.getSession().getAttribute("role") != null) {
                req.getRequestDispatcher("/lecturers.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }

        if ("studyCourses".equals(listType)) {
            List<StudyCourse> studyCourses = educationalService.getAllStudyCourse();
            req.setAttribute("studyCourses", studyCourses);
            if (req.getSession().getAttribute("role") != null) {
                req.getRequestDispatcher("/courses.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }

        if ("lessons".equals(listType)) {
//            List<Lesson> lessons = educationalService.getAllLessons();
//            req.setAttribute("lessons", lessons);
            if (req.getSession().getAttribute("role") != null) {
                req.getRequestDispatcher("/lessons.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }

        if ("attendance".equals(listType)) {
            List<Attendance> attList = educationalService.getAllAttendance();
            req.setAttribute("attendance", attList);
            if (req.getSession().getAttribute("role") != null) {
                req.getRequestDispatcher("/attendance.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }

        if ("marks".equals(listType)) {
            List<Marks> marks = educationalService.getAllMarks();
            req.setAttribute("marks", marks);
            if (req.getSession().getAttribute("role") != null) {
                req.getRequestDispatcher("/marks.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }

    }
}

