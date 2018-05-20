package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public interface I_RoleDAO {

    public boolean addRole(Role role) throws SQLException;

    public Role getRoleById(int id) throws SQLException;

    public List<Role> getAllRoles() throws SQLException;

    public boolean updateRole(Role role) throws SQLException;

    public boolean deleteRoleById(int id) throws SQLException;
}
