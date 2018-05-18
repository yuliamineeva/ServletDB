package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Admin;

import java.sql.SQLException;

public interface I_AdminDAO {
    public boolean addAdmin(Admin admin) throws SQLException;

    public Admin getAdminById(int id) throws SQLException;

    public boolean updateAdmin(Admin admin) throws SQLException;

    public boolean deleteAdminById(int id) throws SQLException;
}
