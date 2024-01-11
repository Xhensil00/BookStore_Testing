package Test.OrdersTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Orders.Buy;
class BuyTest {

    @Test
    void testConstructorAndGetters() {
        // Test the constructor and getters
        String name = "Product";
        double totalPrice = 50.0;
        long time = System.currentTimeMillis();

        Buy buy = new Buy(name, totalPrice, time);

        assertEquals(name, buy.getName());
        assertEquals(totalPrice, buy.getTotalPrice());
        assertEquals(time, buy.getTime());
    }

    @Test
    void testGetName() {
        // Test the getter for name
        String name = "Product";
        double totalPrice = 50.0;
        long time = System.currentTimeMillis();

        Buy buy = new Buy(name, totalPrice, time);

        assertEquals(name, buy.getName());
    }

    @Test
    void testGetTotalPrice() {
        // Test the getter for total price
        String name = "Product";
        double totalPrice = 50.0;
        long time = System.currentTimeMillis();

        Buy buy = new Buy(name, totalPrice, time);

        assertEquals(totalPrice, buy.getTotalPrice());
    }

    @Test
    void testGetTime() {
        // Test the getter for time
        String name = "Product";
        double totalPrice = 50.0;
        long time = System.currentTimeMillis();

        Buy buy = new Buy(name, totalPrice, time);

        assertEquals(time, buy.getTime());
    }
}
