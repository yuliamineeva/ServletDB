package ru.innopolis.stc9.servlet1.controller;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlet1.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginController extends HttpServlet {
    private static Logger logger = Logger.getLogger(HelloController.class);
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("logout".equals(action)) {
            req.getSession().invalidate();
        }
        logger.info("Received request for login page");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("message", "Введите логин и пароль");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String login = req.getParameter("userName");
        String password = req.getParameter("userPassword");
        if (userService.checkAuth(login, password)) {
            int role = userService.getRole(login);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            logger.info("Login " + login + " , role = " + role);
            resp.sendRedirect(req.getContextPath() + "/user/dashboard");
        } else {
            resp.sendRedirect(req.getContextPath() + "/login?errorMsg=authErr");
        }
    }
}
