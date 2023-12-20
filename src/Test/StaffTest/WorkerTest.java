package Test.StaffTest;

import Staff.Gender;
import Staff.Worker;
import mocks.WorkerDummy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorkerTest {
    private Worker worker;

    @BeforeEach
    public void setUp() {
        worker = new WorkerDummy("John Doe", "123456789", "john@example.com", "1990-01-01", 2500f, "password123", Worker.ACCESSLEVEL.LIBRARIAN, Gender.MALE);
    }

    @Test
    public void testGetters() {
        assertEquals("John Doe", worker.getFullName());
        assertEquals("123456789", worker.getPhone());
        assertEquals("john@example.com", worker.getEmail());
        assertEquals(2500f, worker.getSalary(), 0.01);
        assertEquals("password123", worker.getPassword());
        assertEquals("1990-01-01", worker.getDateOfBirth());
        assertEquals(Worker.ACCESSLEVEL.LIBRARIAN, worker.getACCESSLEVEL());
        assertEquals("Librarian", worker.getStatus());
        assertEquals(Gender.MALE, worker.getGender());
    }

    @Test
    public void testSetters() {
        worker.setFullName("Jane Doe");
        assertEquals("Jane Doe", worker.getFullName());

        worker.setPhone("987654321");
        assertEquals("987654321", worker.getPhone());

        worker.setEmail("jane@example.com");
        assertEquals("jane@example.com", worker.getEmail());

        worker.setSalary(3000f);
        assertEquals(3000f, worker.getSalary(), 0.01);

        worker.setPassword("newPassword");
        assertEquals("newPassword", worker.getPassword());

        worker.setDateOfBirth("1995-01-01");
        assertEquals("1995-01-01", worker.getDateOfBirth());

        worker.setACCESSLEVEL(Worker.ACCESSLEVEL.MANAGER);
        assertEquals(Worker.ACCESSLEVEL.MANAGER, worker.getACCESSLEVEL());

        worker.setGender(Gender.FEMALE);
        assertEquals(Gender.FEMALE, worker.getGender());
    }
}
