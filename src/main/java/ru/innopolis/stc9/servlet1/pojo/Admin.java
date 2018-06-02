package ru.innopolis.stc9.servlet1.pojo;

/**
 * Класс админа (POJO - слой)
 */
public class Admin {
    public static final String ROLE = "admin";
    private int id;
    private String name;
    private String login;
    private String password;

    public Admin(int id, String name, String login, String password) {
        this(name, login, password);
        this.id = id;
    }

    public Admin(String name, String login, String password) {
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

    public String getROLE() {
        return ROLE;
    }

    @Override
    public String toString() {
        return "user= " + ROLE + ", " +
                "name= " + name + ", " +
                "login= " + login;
    }
}
