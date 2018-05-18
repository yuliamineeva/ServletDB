package ru.innopolis.stc9.servlet1.db.dao;

import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.SQLException;
import java.util.List;

public interface I_StudentDAO {
    public boolean addStudent(Student student) throws SQLException;

    public List<Student> getAllStudents() throws SQLException;

    public Student getStudentById(int id) throws SQLException;

    public boolean updateStudent(Student student) throws SQLException;

    public boolean deleteStudentById(int id) throws SQLException;
}
