package Test.StaffTest;

import Staff.Worker.ACCESSLEVEL;
import Staff.Admin;
import Staff.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdminTest {

    @Test
    public void testAdminCreation() {
        Admin admin = new Admin(
                "Cozman BS", "1234567890", "cozi@example.com", 60000,
                "1990-01-01", Gender.FEMALE, "pass123", ACCESSLEVEL.ADMIN
        );

        assertNotNull(admin);
        assertEquals("Cozman BS", admin.getFullName());
        assertEquals("cozi@example.com", admin.getEmail());
        assertEquals(60000, admin.getSalary(), 0.001);
    }

    @Test
    public void testAdminInteract() {
        Admin admin = new Admin(
                "Bob", "9876543210", "bob@example.com", 70000,
                "1995-05-05", Gender.MALE, "pass456", ACCESSLEVEL.ADMIN
        );
        admin.interact();
    }
}

