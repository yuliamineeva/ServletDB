package ru.innopolis.stc9.servlet1.pojo;

public class User {
    private int id;
    private String login;
    private int role_number;
    private Role role;

    public User(String login, int role_number) {
        this.login = login;
        this.role_number = role_number;
    }

    public User(int id, String login, int role_number) {
        this.id = id;
        this.login = login;
        this.role_number = role_number;
    }

    public User(int id, String login, int role_number, Role role) {
        this.id = id;
        this.login = login;
        this.role_number = role_number;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getRole_number() {
        return role_number;
    }

    public void setRole_number(int role_number) {
        this.role_number = role_number;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        if (role != null) {
            return "User{" +
                    "id=" + id +
                    ", login='" + login + '\'' +
                    ", role_number=" + role_number +
                    ", role_name=" + role.getRole_name() +
                    '}';
        } else {
            return "User{" +
                    "id=" + id +
                    ", login='" + login + '\'' +
                    ", role_number=" + role_number +
                    '}';
        }
    }
}
