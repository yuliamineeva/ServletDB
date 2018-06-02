package ru.innopolis.stc9.servlet1.pojo;

/**
 * Класс лектора (POJO - слой)
 */
public class Lecturer {
    public static final String ROLE = "lecturer";
    private int id;
    private String name;
    private String login;
    private String password;

    public Lecturer(int id, String name, String login, String password) {
        this.id = id;
        setFields(name, login, password);
    }

    public Lecturer(String name, String login, String password) {
        setFields(name, login, password);
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

    public String getRole() {
        return ROLE;
    }

    @Override
    public String toString() {
        return "user= " + ROLE + ", " +
                "name= " + name + ", " +
                "login= " + login;
    }
}
