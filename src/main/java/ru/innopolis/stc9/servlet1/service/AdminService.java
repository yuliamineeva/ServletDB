package ru.innopolis.stc9.servlet1.service;

import ru.innopolis.stc9.servlet1.db.dao.AdminDAO;
import ru.innopolis.stc9.servlet1.db.dao.I_AdminDAO;
import ru.innopolis.stc9.servlet1.pojo.Admin;

import java.sql.SQLException;

public class AdminService {
    private I_AdminDAO adminDao = new AdminDAO();

    //todo в методах add добавить добавление в таблицу user в сервисах и проверку, нет ли такого логина
    public void insertAdminTest() {
        Admin admin = new Admin("newAdmin", "222", "333");
        try {
            adminDao.addAdmin(admin);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
