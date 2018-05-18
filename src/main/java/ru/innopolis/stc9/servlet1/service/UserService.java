package ru.innopolis.stc9.servlet1.service;

import ru.innopolis.stc9.servlet1.db.dao.I_UserDAO;
import ru.innopolis.stc9.servlet1.db.dao.UserDAO;
import ru.innopolis.stc9.servlet1.pojo.User;

import java.sql.SQLException;

public class UserService {
    private static I_UserDAO userDao = new UserDAO();

    public boolean checkAuth(String login, String password) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //todo исправить, когда будет метод в DAO
        return true;
//        return (user != null) && (user.getPasswordHash().equals(password));
    }

    public int getRole(String login) {
        User user = null;
        try {
            user = userDao.getUserByLogin(login);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user != null) ? user.getRole_number() : 0;
    }
}
