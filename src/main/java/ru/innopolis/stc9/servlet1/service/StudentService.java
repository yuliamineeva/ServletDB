package ru.innopolis.stc9.servlet1.service;

import ru.innopolis.stc9.servlet1.db.dao.I_StudentDAO;
import ru.innopolis.stc9.servlet1.db.dao.StudentDAO;
import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.SQLException;

public class StudentService {
    private I_StudentDAO studentDAO = new StudentDAO();

    public void updateStudentTest() {
        Student student = new Student(5, "Student5", "student", "student", 0);
        try {
            studentDAO.updateStudent(student);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
