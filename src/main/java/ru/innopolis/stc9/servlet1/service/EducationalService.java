package ru.innopolis.stc9.servlet1.service;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.servlet1.db.dao.*;
import ru.innopolis.stc9.servlet1.pojo.*;

import java.sql.SQLException;
import java.util.*;

public class EducationalService {
    private static Logger logger = Logger.getLogger(EducationalService.class);
    I_AdminDAO adminDao = new AdminDAO();
    I_StudentDAO studentDAO = new StudentDAO();
    I_UserDAO userDAO = new UserDAO();
    I_RoleDAO roleDAO = new RoleDAO();
    I_LecturerDAO lecturerDAO = new LecturerDAO();
    I_StudyCourseDAO studyCourseDAO = new StudyCourseDAO();
    I_LessonDAO lessonDAO = new LessonDAO();
    I_MarksDAO marksDAO = new MarksDAO();
    I_AttendanceDAO attendanceDAO = new AttendanceDAO();


    public List<Student> getAllStudents() {
        try {
            return studentDAO.getAllStudents();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lecturer> getAllLecturers() {
        try {
            return lecturerDAO.getAllLecturers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Admin> getAllAdmins() {
        try {
            return adminDao.getAllAdmins();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Role> getAllRoles() {
        try {
            return roleDAO.getAllRoles();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<StudyCourse> getAllStudyCourse() {
        try {
            List<StudyCourse> courses = studyCourseDAO.getAllStudyCourse();
            List<StudyCourse> coursesWithLecturer = new ArrayList<>();
            for (StudyCourse course : courses) {
                course.setLecturer(lecturerDAO.getLecturerById(course.getLecturer_id()));
                coursesWithLecturer.add(course);
            }
            return coursesWithLecturer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lesson> getAllLessons() {
        try {
            List<Lesson> lessons = lessonDAO.getAllLessons();
            List<Lesson> lessonsWithCourses = new ArrayList<>();
            for (Lesson lesson : lessons) {
                lesson.setStudyCourse(studyCourseDAO.getStudyCourseById(lesson.getStudycourse_id()));
                lessonsWithCourses.add(lesson);
            }
            return lessonsWithCourses;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Set<Date> getAllDatesFromLessons() {
        Set<Date> dates = new HashSet<>();
        try {
            List<Lesson> lessons = lessonDAO.getAllLessons();
            for (Lesson lesson : lessons) {
                Date date = lesson.getDate();
                dates.add(date);
            }
        } catch (SQLException e) {
            logger.error("Error trying to get dates", e);
        }
        return dates;
    }

    public List<Marks> getAllMarks() {
        try {
            List<Marks> marksList = marksDAO.getAllMarks();
            List<Marks> marksListWithAllField = new ArrayList<>();
            for (Marks marks : marksList) {
                marks.setLesson(lessonDAO.getLessonById(marks.getLesson_id()));
                marks.setStudyCourse(studyCourseDAO.getStudyCourseById(marks.getStudycourse_id()));
                marks.setStudent(studentDAO.getStudentById(marks.getStudent_id()));
                marksListWithAllField.add(marks);
            }
            return marksListWithAllField;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Attendance> getAllAttendance() {
        try {
            List<Attendance> attList = attendanceDAO.getAllAttendance();
            List<Attendance> attListWithAllField = new ArrayList<>();
            for (Attendance attendance : attList) {
                attendance.setLesson(lessonDAO.getLessonById(attendance.getLesson_id()));
                attendance.setStudent(studentDAO.getStudentById(attendance.getStudent_id()));

                attListWithAllField.add(attendance);
            }
            return attListWithAllField;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
