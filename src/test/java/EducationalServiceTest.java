import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.innopolis.stc9.servlet1.db.dao.*;
import ru.innopolis.stc9.servlet1.pojo.Attendance;
import ru.innopolis.stc9.servlet1.pojo.Lesson;
import ru.innopolis.stc9.servlet1.pojo.Student;
import ru.innopolis.stc9.servlet1.service.EducationalService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EducationalServiceTest {
    private static Logger logger = Logger.getLogger(EducationalServiceTest.class);
    private EducationalService educationalService;

    @Before
    public void before() {
        logger.info("@Before");
        I_StudentDAO mockStudentDAO = mock(StudentDAO.class);
        I_LessonDAO mockLessonDAO = mock(LessonDAO.class);
        I_AttendanceDAO mockAttendanceDAO = mock(AttendanceDAO.class);
        educationalService = new EducationalService(mockStudentDAO, mockLessonDAO, mockAttendanceDAO);
        List<Student> studentsTest = new ArrayList<>();
        Student student1 = new Student(1, "Иванов Иван Иванович", "ivanov", "ivanov", 0);
        studentsTest.add(student1);
        studentsTest.add(new Student(2, "name2", "login2", "passw2", 4));
        ArrayList<Attendance> attList = new ArrayList<>();
        attList.add(new Attendance(1, new Date(), 1, 1, true));
        try {
            when(mockStudentDAO.getAllStudents()).thenReturn(studentsTest);
            when(mockStudentDAO.getStudentById(1)).thenReturn(student1);
            when(mockLessonDAO.getLessonById(1)).thenReturn(new Lesson(1, "introductory lesson", new Date(), 2));
            when(mockAttendanceDAO.getAttendanceByStudent(student1)).thenReturn(attList);
        } catch (SQLException e) {
            logger.error("error accessing database", e);
        }
    }

    @Test
    public void getAllStudentsTest() {
        logger.info("getAllStudentsTest");
        List<Student> result = educationalService.getAllStudents();
        boolean res = result != null;
        assertTrue("Список студентов не равен null", res);
        assertEquals(result.get(0).getId(), 1);
        assertEquals(result.get(0).getName(), "Иванов Иван Иванович");
        assertEquals(result.get(0).getLogin(), "ivanov");
    }

    @Test
    public void getAttendanceByStudentTest() {
        logger.info("getAttendanceByStudentTest");
        List<Attendance> result = educationalService.getAttendanceByStudent(1);
        assertEquals(result.get(0).getId(), 1);
        assertEquals(result.get(0).getLesson_id(), 1);
        assertEquals(result.get(0).getLesson().getTopic(), "introductory lesson");
        assertEquals(result.get(0).getStudent_id(), 1);
        assertTrue(result.get(0).isBe_present());
    }
}
