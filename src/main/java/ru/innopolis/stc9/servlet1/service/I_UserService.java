package ru.innopolis.stc9.servlet1.service;

import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.pojo.User;

public interface I_UserService {

    User getUserByLogin(String login);

    Student getStudentByLogin(String login);

    boolean checkAuth(String login, String password);

    String getUsersFieldFromDB(String login);

    int getRole(String login);

    float calculateAverageMark(Student student);
}
