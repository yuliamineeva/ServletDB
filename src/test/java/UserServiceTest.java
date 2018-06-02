import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.servlet1.db.dao.*;
import ru.innopolis.stc9.servlet1.pojo.Marks;
import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.pojo.User;
import ru.innopolis.stc9.servlet1.service.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.innopolis.stc9.servlet1.pojo.Mark.MARK_5;

public class UserServiceTest {
    private static Logger logger = Logger.getLogger(UserServiceTest.class);
    private static Student student1;
    private UserService userService;

    @Before
    public void before() {
        logger.info("@Before");
        I_StudentDAO mockStudentDAO = mock(StudentDAO.class);
        I_UserDAO mockUserDAO = mock(UserDAO.class);
        I_MarksDAO mockMarksDAO = mock(MarksDAO.class);
        userService = new UserService(mockUserDAO, mockStudentDAO, mockMarksDAO);
        student1 = new Student(1, "Иванов Иван Иванович", "ivanov", "ivanov", 5);
        User user1 = new User(1, "ivanov", 3);
        ArrayList<Marks> marksList = new ArrayList<>();
        marksList.add(new Marks(1, new Date(), 1, 1, 1, MARK_5));
        marksList.add(new Marks(2, new Date(), 1, 2, 1, MARK_5));

        try {
            when(mockStudentDAO.getStudentByLogin("ivanov")).thenReturn(student1);
            when(mockUserDAO.getUserByLogin("ivanov")).thenReturn(user1);
            when(mockMarksDAO.getMarksByStudent(student1)).thenReturn(marksList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUserByLoginTest() {
        logger.info("getUserByLoginTest");
        User result = userService.getUserByLogin("ivanov");
        boolean res = result != null;
        assertTrue("Студент не равен null", res);
        assertEquals(result.getId(), 1);
        assertEquals(result.getLogin(), "ivanov");
        assertEquals(result.getRole_number(), 3);
    }

    @Test
    public void getStudentByLoginTest() {
        logger.info("getStudentByLoginTest");
        Student result = userService.getStudentByLogin("ivanov");
        boolean res = result != null;
        assertTrue("Студент не равен null", res);
        assertEquals(result.getId(), 1);
        assertEquals(result.getLogin(), "ivanov");
        assertEquals(result.getName(), "Иванов Иван Иванович");
        assertEquals(result.getAverageMark(), 5.0, 0.0);
    }

    @Test
    public void calculateAverageMarkTest() {
        logger.info("calculateAverageMarkTest");
        float result = userService.calculateAverageMark(student1);
        float right = 5;
        assertEquals(result, right, 0.0);
    }

}
