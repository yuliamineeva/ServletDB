package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Lecturer;

import java.sql.SQLException;
import java.util.List;

public interface I_LecturerDAO {
    boolean addLecturer(Lecturer lecturer) throws SQLException;

    List<Lecturer> getAllLecturers() throws SQLException;

    Lecturer getLecturerByLogin(String login) throws SQLException;

    Lecturer getLecturerById(int id) throws SQLException;

    boolean updateLecturer(Lecturer lecturer) throws SQLException;

    boolean deleteLecturerById(int id) throws SQLException;
}
