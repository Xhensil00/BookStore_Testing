package Test.OrdersTest;
import Orders.BuyOrders;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.util.ArrayList;

public class BuyOrdersTest {
    @TempDir
    public File tempFolder;
    private File tempFile1;
    private File tempFile2;
    private BuyOrders buyOrder;

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
        BuyOrders.setTestingTrue();
        buyOrder = new BuyOrders(isbnList, quantityList, totalPrice, name);
        buyOrder.setTxtFile(tempFile1);
        buyOrder.setDataFile(tempFile2);
        BuyOrders.setTestingTrue();
    }
    @AfterEach
    public void tearDown() {
        if (tempFile1.exists()) {
            boolean isDeleted=tempFile1.delete();
        }
        if (tempFile2.exists()) {
            boolean isDeleted=tempFile2.delete();
        }
        BuyOrders.setTestingFalse();
         buyOrder= null;
    }
    @Test
    public void testGetTotalPrice() {
        assertEquals(45.67, buyOrder.getTotalPrice(), 0.001);
    }

    @Test
    public void testGetName() {
        assertEquals("Sokoli Vogel", buyOrder.getName());
    }

    @Test
    public void testGetIsbns() {
        assertEquals(1, buyOrder.getIsbns().size());
        assertEquals("1234567890123", buyOrder.getIsbns().get(0));
    }

    @Test
    public void testGetQuantity() {
        assertEquals(1, buyOrder.getQuantity().size());
        assertEquals(Integer.valueOf(3), buyOrder.getQuantity().get(0));
    }

    @Test
    public void testAddToDatabase() {
        assertTrue(buyOrder.addToDatabase());
    }

    @Test
    public void testWriteToFile() {
        buyOrder.writeToFile();
    }
}
