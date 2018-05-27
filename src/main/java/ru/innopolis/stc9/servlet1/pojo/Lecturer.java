package ru.innopolis.stc9.servlet1.pojo;

public class Lecturer {
    public static final String ROLE = "lecturer";
    private int id;
    private String name;
    private String login;
    private String password;

    public Lecturer(int id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public Lecturer(String name, String login, String password, int role) {
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
        return "user= " + ROLE + ",\n" +
                "name= " + name + ",\n" +
                "login= " + login;
    }
}
