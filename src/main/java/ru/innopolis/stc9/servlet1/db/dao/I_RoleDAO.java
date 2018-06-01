package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Role;

import java.sql.SQLException;
import java.util.List;

public interface I_RoleDAO {

    boolean addRole(Role role) throws SQLException;

    Role getRoleById(int id) throws SQLException;

    List<Role> getAllRoles() throws SQLException;

    boolean updateRole(Role role) throws SQLException;

    boolean deleteRoleById(int id) throws SQLException;
}
