package ru.innopolis.stc9.servlet1.pojo;

import java.util.Date;

/**
 * Класс оценок (POJO - слой)
 */
public class Marks {
    private int id;
    private Date date;
    private int studycourse_id;
    private StudyCourse studyCourse;
    private int lesson_id;
    private Lesson lesson;
    private int student_id;
    private Student student;
    private Mark mark;

    public Marks(int id, Date date, int studycourse_id, int lesson_id, int student_id, Mark mark) {
        setFields(date, studycourse_id, lesson_id, student_id);
        this.id = id;
        this.mark = mark;
    }

    public Marks(Date date, int studycourse_id, int lesson_id, int student_id, Mark mark) {
        setFields(date, studycourse_id, lesson_id, student_id);
        this.mark = mark;
    }

    public Marks(int id, Date date, int studycourse_id, StudyCourse studyCourse,
                 int lesson_id, Lesson lesson, int student_id,
                 Student student, Mark mark) {
        setFields(date, studycourse_id, lesson_id, student_id);
        this.id = id;
        this.studyCourse = studyCourse;
        this.lesson = lesson;
        this.student = student;
        this.mark = mark;
    }

    public Marks(int id, Date date, int studycourse_id, int lesson_id, int student_id, int mark) {
        setFields(date, studycourse_id, lesson_id, student_id);
        this.id = id;
        this.mark = getMarkByInt(mark);
    }

    private void setFields(Date date, int studycourse_id, int lesson_id, int student_id) {
        this.date = date;
        this.studycourse_id = studycourse_id;
        this.lesson_id = lesson_id;
        this.student_id = student_id;
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

    public int getStudycourse_id() {
        return studycourse_id;
    }

    public void setStudycourse_id(int studycourse_id) {
        this.studycourse_id = studycourse_id;
    }

    public StudyCourse getStudyCourse() {
        return studyCourse;
    }

    public void setStudyCourse(StudyCourse studyCourse) {
        this.studyCourse = studyCourse;
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

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    /**
     * Получить Mark из Enum
     *
     * @param intValue
     * @return Mark
     */
    public Mark getMarkByInt(int intValue) {
        switch (intValue) {
            case 1:
                return Mark.MARK_1;
            case 2:
                return Mark.MARK_2;
            case 3:
                return Mark.MARK_3;
            case 4:
                return Mark.MARK_4;
            case 5:
                return Mark.MARK_5;
        }
        return null;
    }

    @Override
    public String toString() {
        if (lesson != null && student != null && studyCourse != null) {
            return "Marks{" +
                    "id=" + id +
                    ", date=" + date +
                    ", studycourse_id=" + studycourse_id +
                    ", studycourse_name=" + studyCourse.getName() +
                    ", lesson_id=" + lesson_id +
                    ", lesson_topic=" + lesson.getTopic() +
                    ", student_id=" + student_id +
                    ", student_name=" + student.getName() +
                    ", mark=" + mark +
                    '}';
        } else {
            return "Marks{" +
                    "id=" + id +
                    ", date=" + date +
                    ", studycourse_id=" + studycourse_id +
                    ", lesson_id=" + lesson_id +
                    ", student_id=" + student_id +
                    ", mark=" + mark +
                    '}';
        }
    }
}
