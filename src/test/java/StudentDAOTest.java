import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.innopolis.stc9.servlet1.db.connectionManager.ConnectionManagerJDBC;
import ru.innopolis.stc9.servlet1.db.connectionManager.IConnectionManager;
import ru.innopolis.stc9.servlet1.db.dao.StudentDAO;
import ru.innopolis.stc9.servlet1.pojo.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StudentDAOTest {
    private static Logger logger = Logger.getLogger(StudentDAOTest.class);
    private static IConnectionManager connectionManager = ConnectionManagerJDBC.getInstance();
    private static List<Student> studentsTest;
    Connection connection;
    private StudentDAO studentDAO;

    @BeforeClass
    public static void beforeTests() {
        logger.info("@BeforeClass");
        studentsTest = new ArrayList<>();
        studentsTest.add(new Student(1, null, null, null, 0));
        studentsTest.add(new Student(2, null, null, null, 0));
        studentsTest.add(new Student(3, null, null, null, 0));
        studentsTest.add(new Student(4, null, null, null, 0));
    }

    @Before
    public void before() {
        logger.info("@Before");
        this.studentDAO = new StudentDAO();
        connection = connectionManager.getConnection();
    }

    @After
    public void after() throws SQLException {
        logger.info("@After");
        connection.close();
    }

    @Test
    public void getAllStudentsTest() throws SQLException {
        logger.info("getAllStudentsTest");
        List<Student> students = studentDAO.getAllStudents();
        assertEquals(students.size(), studentsTest.size());
    }
}
