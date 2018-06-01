package ru.innopolis.stc9.servlet1.service;

import ru.innopolis.stc9.servlet1.pojo.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface I_EducationalService {
    List<Student> getAllStudents();

    List<Lecturer> getAllLecturers();

    List<Admin> getAllAdmins();

    List<User> getAllUsers();

    List<Role> getAllRoles();

    List<StudyCourse> getAllStudyCourse();

    List<Lesson> getAllLessons();

    Set<Date> getAllDatesFromLessons();

    List<Marks> getAllMarks();

    List<Marks> getMarksByStudent(Student student);

    List<Attendance> getAllAttendance();

    List<Attendance> getAttendanceByLesson(int lesson_id);

    List<Attendance> getAttendanceByDate(String date);

    List<Attendance> getAttendanceByStudent(int student_id);
}
