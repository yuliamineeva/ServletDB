package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Lecturer;

import java.sql.SQLException;
import java.util.List;

public interface I_LecturerDAO {
    public boolean addLecturer(Lecturer lecturer) throws SQLException;

    public List<Lecturer> getAllLecturers() throws SQLException;

    public Lecturer getLecturerByLogin(String login) throws SQLException;

    public Lecturer getLecturerById(int id) throws SQLException;

    public boolean updateLecturer(Lecturer lecturer) throws SQLException;

    public boolean deleteLecturerById(int id) throws SQLException;
}
