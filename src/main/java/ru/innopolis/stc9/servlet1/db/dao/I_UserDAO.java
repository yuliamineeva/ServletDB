package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface I_UserDAO {

    public boolean addUser(User user) throws SQLException;

    public User getUserById(int id) throws SQLException;

    public User getUserByLogin(String login) throws SQLException;

    public List<User> getAllUsersByRole(String role_name) throws SQLException;

    public List<User> getAllUsers() throws SQLException;

    public boolean updateUser(User user) throws SQLException;

    public boolean deleteUserById(int id) throws SQLException;
}
