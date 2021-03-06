package ru.innopolis.stc9.servlet1.pojo;

/**
 * Класс студента (POJO - слой)
 */
public class Student {
    public static final String ROLE = "student";
    private int id;
    private String name;
    private String login;
    private String password;
    private float averageMark;

    public Student(String name, String login, String password) {
        setFields(name, login, password);
    }

    public Student(String name, String login, String password, float averageMark) {
        setFields(name, login, password);
        this.averageMark = averageMark;
    }

    public Student(int id, String name, String login, String password, float averageMark) {
        setFields(name, login, password);
        this.id = id;
        this.averageMark = averageMark;
    }

    private void setFields(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(float averageMark) {
        this.averageMark = averageMark;
    }

    public String getROLE() {
        return ROLE;
    }

    @Override
    public String toString() {
        return "user= " + ROLE + ", " +
                "name= " + name + ", " +
                "login= " + login + ", " +
                "averageMark= " + averageMark;
    }
}
