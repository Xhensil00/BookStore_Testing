package Test.StaffTest;

import Staff.Worker;
import Staff.Worker.ACCESSLEVEL;
import Staff.Gender;
import Staff.Librarian;
import mocks.WorkerDummy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianTest {

    @Test
    public void testLibrarianCreation() {

        Librarian librarian = new Librarian(
                "Jane Doe", "1234567890", "jane@example.com", "1990-01-01",
                Gender.FEMALE, 55000, "passLibrarian", ACCESSLEVEL.LIBRARIAN, true
        );

        assertNotNull(librarian);
        assertEquals("Jane Doe", librarian.getFullName());
        assertEquals("jane@example.com", librarian.getEmail());
        assertTrue(librarian.isPermitionToBill());
    }

    @Test
    public void testLibrarianInteract() {

        Librarian librarian = new Librarian(
                "John Doe", "9876543210", "john@example.com", "1995-05-05",
                Gender.MALE, 60000, "pass123", ACCESSLEVEL.LIBRARIAN, true
        );
        librarian.interact();;
    }
}

