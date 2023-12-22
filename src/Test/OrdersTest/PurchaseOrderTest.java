package Test.OrdersTest;
import Orders.PurchaseOrders;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.util.ArrayList;
public class PurchaseOrderTest {
    @TempDir
    public File tempFolder;
    private File tempFile1;
    private File tempFile2;
    private PurchaseOrders purchaseOrder;

    @BeforeEach
    public void setUp() {
        tempFile1 = new File(tempFolder, "tempBuysBillData.dat");
        tempFile2 = new File(tempFolder, "tempBuysBillData.txt");

        ArrayList<String> isbnList = new ArrayList<>();
        isbnList.add("1234567890123");
        ArrayList<Integer> quantityList = new ArrayList<>();
        quantityList.add(3);

        double totalPrice = 45.67;
        String name = "Sokoli Vogel";
        PurchaseOrders.setTestingTrue();
        purchaseOrder = new PurchaseOrders(isbnList, quantityList, totalPrice, name);
        purchaseOrder.setTxtFile(tempFile1);
        purchaseOrder.setDataFile(tempFile2);
        PurchaseOrders.setTestingTrue();
    }
    @AfterEach
    public void tearDown() {
        if (tempFile1.exists()) {
            boolean isDeleted=tempFile1.delete();
        }
        if (tempFile2.exists()) {
            boolean isDeleted=tempFile2.delete();
        }
        PurchaseOrders.setTestingFalse();
        purchaseOrder= null;
    }
    @Test
    public void testGetTotalPrice() {
        assertEquals(45.67, purchaseOrder.getTotalPrice(), 0.001);
    }

    @Test
    public void testGetName() {
        assertEquals("Sokoli Vogel", purchaseOrder.getName());
    }

    @Test
    public void testGetIsbns() {
        assertEquals(1, purchaseOrder.getIsbns().size());
        assertEquals("1234567890123", purchaseOrder.getIsbns().get(0));
    }

    @Test
    public void testGetQuantity() {
        assertEquals(1, purchaseOrder.getQuantity().size());
        assertEquals(Integer.valueOf(3), purchaseOrder.getQuantity().get(0));
    }

    @Test
    public void testAddToDatabase() {
        assertTrue(purchaseOrder.addToDatabase());
    }

    @Test
    public void testWriteToFile() {
        purchaseOrder.writeToFile();
    }
}
