package ru.innopolis.stc9.servlet1.pojo;

public class Role {
    private int id;
    private String role_name;
    private int role;

    public Role(int id, String role_name, int role) {
        this.id = id;
        this.role_name = role_name;
        this.role = role;
    }

    public Role(String role_name, int role) {
        this.role_name = role_name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                ", role=" + role +
                '}';
    }
}
