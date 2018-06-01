package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Admin;

import java.sql.SQLException;
import java.util.List;

public interface I_AdminDAO {

    boolean addAdmin(Admin admin) throws SQLException;

    List<Admin> getAllAdmins() throws SQLException;

    Admin getAdminByLogin(String login) throws SQLException;

    Admin getAdminById(int id) throws SQLException;

    boolean updateAdmin(Admin admin) throws SQLException;

    boolean deleteAdminById(int id) throws SQLException;
}
