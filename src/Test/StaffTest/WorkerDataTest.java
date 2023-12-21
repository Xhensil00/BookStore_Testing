package Test.StaffTest;

import Staff.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class WorkerDataTest {

    WorkerData workerData;
    Librarian librarian;
    Manager manager;
    Admin admin;

    @TempDir
    File tempDir;

    File tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = new File(tempDir, "tempWorkerDataFile.dat");
        boolean created =tempFile.createNewFile();
        WorkerData.setTestingTrue();
        workerData = new WorkerData();
        workerData.setFile(tempFile);
        librarian = new Librarian("Efkaristo Poli", "123456789", "poli@example.com", "1990-01-01", Gender.MALE, 50000, "password", Worker.ACCESSLEVEL.LIBRARIAN, true);
        manager = new Manager("Potukseris Imego", "987654321", "potu@example.com", 60000, "1985-05-15", Gender.FEMALE, "password", Worker.ACCESSLEVEL.MANAGER, true, true);
        admin = new Admin("Puisere File", "111222333", "puis@example.com", 70000, "1978-12-25", Gender.MALE, "password", Worker.ACCESSLEVEL.ADMIN);
    }

    @AfterEach
    public void tearDown() {
        boolean isDeleted=tempFile.delete();
        workerData = null;
        WorkerData.setTestingFalse();
    }

    @Test
    public void testWorkerDataConstructor() {
        assertNotNull(workerData);
        assertNotNull(workerData.getData());
        assertEquals(0, workerData.getData().size());
        assertNotNull(workerData.getFile());
        assertTrue(workerData.getFile().exists());
    }

    @Test
    public void testWriteWorkerToFile() throws IOException {
        workerData.setFile(tempFile);
        assertTrue(workerData.writeWorkerToFile(librarian));
        assertTrue(tempFile.exists());
    }

    @Test
    public void testReadWorkerData() {
        workerData.setFile(tempFile);
        workerData.writeWorkerToFile(librarian);
        workerData.writeWorkerToFile(manager);
        workerData.writeWorkerToFile(admin);
        workerData.readWorkerData();
        assertEquals(3, workerData.getData().size());
    }

    @Test
    public void testDeleteWorker() {
        workerData.setFile(tempFile);
        workerData.writeWorkerToFile(librarian);
        workerData.deleteWorker(librarian);
        workerData.readWorkerData();
        assertEquals(0, workerData.getData().size());
    }

    @Test
    public void testGetLibrarians() {
        workerData.setFile(tempFile);
        workerData.writeWorkerToFile(librarian);
        workerData.writeWorkerToFile(manager);
        workerData.writeWorkerToFile(admin);
        workerData.readWorkerData();
        assertEquals(1, workerData.getLibrarians().size());
    }

    @Test
    public void testGetWorkerFromEmail() {
        workerData.setFile(tempFile);
        workerData.writeWorkerToFile(librarian);
        workerData.readWorkerData();
        Worker retrievedWorker = workerData.getWorkerFromEmail(librarian.getEmail());
        assertNotNull(retrievedWorker);
        assertEquals("Efkaristo Poli", retrievedWorker.getFullName());
    }

    @Test
    public void testGetWorker() {
        workerData.setFile(tempFile);
        workerData.writeWorkerToFile(librarian);
        workerData.writeWorkerToFile(manager);
        workerData.writeWorkerToFile(admin);
        workerData.readWorkerData();
        Worker retrievedWorker = workerData.getWorker(1);
        assertNotNull(retrievedWorker);
        assertEquals("Potukseris Imego", retrievedWorker.getFullName());
    }
}
