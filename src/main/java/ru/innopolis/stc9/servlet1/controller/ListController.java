package ru.innopolis.stc9.servlet1.controller;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.service.EducationalService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ListController extends HttpServlet {
    private final static Logger logger = Logger.getLogger(ListController.class);
    private EducationalService educationalService = new EducationalService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("Cp1251"); // Для отображение русских букв в браузере. Кодировка "UTF-8" не подошла
        String listType = req.getParameter("listType");
        if ("students".equals(listType)) {
            List<Student> students = educationalService.getAllStudents();
            req.setAttribute("students", students);
            if (req.getSession().getAttribute("role") != null &&
                    (Integer) req.getSession().getAttribute("role") != 3) {
                req.getRequestDispatcher("/students.jsp").forward(req, resp);
            } else {

                req.getRequestDispatcher("/wrongRole.jsp").forward(req, resp);
            }
        }

//            List<User> users = educationalService.getAllUsers();

    }
}

