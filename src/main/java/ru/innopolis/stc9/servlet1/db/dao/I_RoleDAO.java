package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Role;

import java.sql.SQLException;

public interface I_RoleDAO {
    public Role getRoleById(int id) throws SQLException;
}
