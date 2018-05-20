package ru.innopolis.stc9.servlet1.pojo;

import java.util.Date;

public class Lesson {
    private int id;
    private String topic;
    private Date date;
    private int studycourse_id;
    private StudyCourse studyCourse;

    public Lesson(int id, String topic, Date date, int studycourse_id) {
        this.id = id;
        this.topic = topic;
        this.date = date;
        this.studycourse_id = studycourse_id;
    }

    public Lesson(String topic, Date date, int studycourse_id) {
        this.topic = topic;
        this.date = date;
        this.studycourse_id = studycourse_id;
    }

    public Lesson(int id, String topic, Date date, int studycourse_id, StudyCourse studyCourse) {
        this.id = id;
        this.topic = topic;
        this.date = date;
        this.studycourse_id = studycourse_id;
        this.studyCourse = studyCourse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
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

    @Override
    public String toString() {
        if (studyCourse != null) {
            return "Lesson{" +
                    "id=" + id +
                    ", topic='" + topic + '\'' +
                    ", date=" + date +
                    ", studycourse_id=" + studycourse_id +
                    ", studycourse_name=" + studyCourse.getName() +
                    '}';
        } else {
            return "Lesson{" +
                    "id=" + id +
                    ", topic='" + topic + '\'' +
                    ", date=" + date +
                    ", studycourse_id=" + studycourse_id +
                    '}';
        }
    }
}
