package ru.innopolis.stc9.servlet1.controller;

import ru.innopolis.stc9.servlet1.pojo.Marks;
import ru.innopolis.stc9.servlet1.service.MarksService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MarksController extends HttpServlet {
    private MarksService marksService = new MarksService();
    //todo добавить методы: вывод оценок по дате, по студенту
    //todo добавить вывод всех таблиц

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("Cp1251"); // Для отображение русских букв в браузере. Кодировка "UTF-8" не подошла
        resp.getWriter().println("Оценки студентов\n" + "Database of students marks");

        String lesId = req.getParameter("lesId");
        if (lesId != null) {
            List<Marks> marks = marksService.getMarksByLessonID(Integer.parseInt(lesId));
            //todo перенести логику вывода на экран в jsp
            for (Marks mark : marks) {
                resp.getWriter().println(mark.toString());
            }
        } else {
            resp.getWriter().println("Введите номер лекции");
        }
    }
}
