package ru.innopolis.stc9.servlet1.pojo;

import java.util.Date;

public class Attendance {
    private int id;
    private Date date;
    private int lesson_id;
    private Lesson lesson;
    private int student_id;
    private Student student;
    private boolean be_present;

    public Attendance(int id, Date date, int lesson_id, int student_id, boolean be_present) {
        this.id = id;
        this.date = date;
        this.lesson_id = lesson_id;
        this.student_id = student_id;
        this.be_present = be_present;
    }

    public Attendance(Date date, int lesson_id, int student_id, boolean be_present) {
        this.date = date;
        this.lesson_id = lesson_id;
        this.student_id = student_id;
        this.be_present = be_present;
    }

    public Attendance(int id, Date date, int lesson_id, Lesson lesson, int student_id, Student student, boolean be_present) {
        this.id = id;
        this.date = date;
        this.lesson_id = lesson_id;
        this.lesson = lesson;
        this.student_id = student_id;
        this.student = student;
        this.be_present = be_present;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public boolean isBe_present() {
        return be_present;
    }

    public void setBe_present(boolean be_present) {
        this.be_present = be_present;
    }

    @Override
    public String toString() {
        if (lesson != null && student != null) {
            return "Attendance{" +
                    "id=" + id +
                    ", date=" + date +
                    ", lesson_id=" + lesson_id +
                    ", lesson_topic=" + lesson.getTopic() +
                    ", student_id=" + student_id +
                    ", student_name=" + student.getName() +
                    ", be_present=" + be_present +
                    '}';
        } else {
            return "Attendance{" +
                    "id=" + id +
                    ", date=" + date +
                    ", lesson_id=" + lesson_id +
                    ", student_id=" + student_id +
                    ", be_present=" + be_present +
                    '}';
        }
    }
}
