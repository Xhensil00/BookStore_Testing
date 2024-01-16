package Test.OrdersTest;
import Orders.BillData;
import Orders.PurchaseOrders;
import Orders.BuyOrders;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.util.ArrayList;
public class BillDataTest {
    @TempDir
    public File tempFolder;
    private File purchaseFile;
    private File buysFile;
    private PurchaseOrders purchaseOrder;
    private BuyOrders buyOrder;
    private BillData bill;
    @BeforeEach
    public void setUp() {
        purchaseFile = new File(tempFolder, "purchases.dat");
        buysFile = new File(tempFolder, "buys.dat");

        ArrayList<String> isbnList = new ArrayList<>();
        isbnList.add("1234567890123");
        ArrayList<Integer> quantityList = new ArrayList<>();
        quantityList.add(3);

        double totalPrice = 45.67;
        String name = "Sokoli Vogel";
        PurchaseOrders.setTestingTrue();
        purchaseOrder = new PurchaseOrders(isbnList, quantityList, totalPrice, name);
        purchaseOrder.setDataFile(purchaseFile);
        purchaseOrder.writeToFile();
        BuyOrders.setTestingTrue();
        buyOrder= new BuyOrders(isbnList, quantityList, totalPrice, name);
        buyOrder.setDataFile(buysFile);
        buyOrder.writeToFile();
        BillData.setTestingTrue();
        bill= new BillData();
        bill.setBuysFile(buysFile);
        bill.setPurchasesFile(purchaseFile);
    }
    @Test
    public void testreadPurchaseBillsData(){
        bill.readPurchaseBillsData();
        assertNotNull(bill.getPurchases());
    }
    @Test
    public void testreadBuyBillsData(){
        bill.readBuyBillsData();
        assertNotNull(bill.getBuys());
    }
    @Test
    public void testSetTestingTrue() {
        // Test setting testing to true
        BillData.setTestingTrue();
        assertTrue(BillData.isTesting()); // Assuming you have a static getter method for testing
    }

    @Test
    public void testSetTestingFalse() {
        // Test setting testing to false
        BillData.setTestingFalse();
        assertFalse(BillData.isTesting()); // Assuming you have a static getter method for testing
    }
    @Test
    public void testSetPurchasesFile(){
        File file= new File("test.dat");
        bill.setPurchasesFile(file);
        assertEquals(file, bill.getPurchasesFile());
    }
    @Test
    public void testSetBuysFile(){
        File file= new File("test.dat");
        bill.setBuysFile(file);
        assertEquals(file, bill.getBuysFile());
    }
    @AfterEach
    public void tearDown(){
        BillData.setTestingFalse();
        if (purchaseFile.exists()) {
            boolean isDeleted=purchaseFile.delete();
        }
        if (buysFile.exists()) {
            boolean isDeleted=buysFile.delete();
        }
        bill= null;
    }
}
