package Test.StaffTest;

import Staff.Gender;
import Staff.Manager;
import Staff.Worker.ACCESSLEVEL;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {

    @Test
    public void testManagerCreation() {
        Manager manager = new Manager(
                "Bardhyl Gashi", "1234567890", "bardhyl@example.com", 50000,
                "2000-01-01", Gender.MALE, "password123", ACCESSLEVEL.MANAGER,
                true, true
        );

        assertNotNull(manager);
        assertEquals("Bardhyl Gashi", manager.getFullName());
        assertEquals("bardhyl@example.com", manager.getEmail());
        assertTrue(manager.isCheckLibrarians());
        assertTrue(manager.isPermitionToPurchse());
    }

    @Test
    public void testManagerInteract() {
        Manager manager = new Manager(
                "Luljeta Gashi", "9876543210", "luljeta@example.com", 60000,
                "1995-05-05", Gender.FEMALE, "pass321", ACCESSLEVEL.MANAGER,
                false, true
        );
        manager.interact();
    }

    @Test
    public void testSetCheckLibrarians() {
        Manager manager = new Manager(
                "Beni Hoxha", "5555555555", "beni@example.com", 70000,
                "1990-10-10", Gender.FEMALE, "pass456", ACCESSLEVEL.MANAGER,
                true, false
        );
        manager.setCheckLibrarians(true);
        assertTrue(manager.isCheckLibrarians());

        manager.setCheckLibrarians(false);
        assertFalse(manager.isCheckLibrarians());
    }
}

