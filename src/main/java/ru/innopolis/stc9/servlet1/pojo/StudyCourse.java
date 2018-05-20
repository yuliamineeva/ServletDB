package ru.innopolis.stc9.servlet1.pojo;

public class StudyCourse {
    private int id;
    private String name;
    private int lecturer_id;
    private Lecturer lecturer;

    public StudyCourse(int id, String name, int lecturer_id) {
        this.id = id;
        this.name = name;
        this.lecturer_id = lecturer_id;
    }

    public StudyCourse(String name, int lecturer_id) {
        this.name = name;
        this.lecturer_id = lecturer_id;
    }

    public StudyCourse(int id, String name, int lecturer_id, Lecturer lecturer) {
        this.id = id;
        this.name = name;
        this.lecturer_id = lecturer_id;
        this.lecturer = lecturer;
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

    public int getLecturer_id() {
        return lecturer_id;
    }

    public void setLecturer_id(int lecturer_id) {
        this.lecturer_id = lecturer_id;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public String toString() {
        if (lecturer != null) {
            return "StudyCourse{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", lecturer_id=" + lecturer_id +
                    ", lecturer_name=" + lecturer.getName() +
                    '}';
        } else {
            return "StudyCourse{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", lecturer_id=" + lecturer_id +
                    '}';
        }
    }
}
