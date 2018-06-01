package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.SQLException;
import java.util.List;

public interface I_StudentDAO {
    boolean addStudent(Student student) throws SQLException;

    List<Student> getAllStudents() throws SQLException;

    Student getStudentByLogin(String login) throws SQLException;

    Student getStudentById(int id) throws SQLException;

    boolean updateStudent(Student student) throws SQLException;

    boolean deleteStudentById(int id) throws SQLException;
}
