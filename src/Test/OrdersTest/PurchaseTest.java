package Test.OrdersTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Orders.Purchase;
class PurchaseTest {

    @Test
    void testConstructorAndGetters() {
        // Test the constructor and getters
        String name = "Product";
        double totalPrice = 50.0;
        long time = System.currentTimeMillis();

        Purchase purchase = new Purchase(name, totalPrice, time);

        assertEquals(name, purchase.getName());
        assertEquals(totalPrice, purchase.getTotalPrice());
        assertEquals(time, purchase.getTime());
    }

    @Test
    void testGetName() {
        // Test the getter for name
        String name = "Product";
        double totalPrice = 50.0;
        long time = System.currentTimeMillis();

        Purchase purchase = new Purchase(name, totalPrice, time);

        assertEquals(name, purchase.getName());
    }

    @Test
    void testGetTotalPrice() {
        // Test the getter for total price
        String name = "Product";
        double totalPrice = 50.0;
        long time = System.currentTimeMillis();

        Purchase purchase = new Purchase(name, totalPrice, time);

        assertEquals(totalPrice, purchase.getTotalPrice());
    }

    @Test
    void testGetTime() {
        // Test the getter for time
        String name = "Product";
        double totalPrice = 50.0;
        long time = System.currentTimeMillis();

        Purchase purchase = new Purchase(name, totalPrice, time);

        assertEquals(time, purchase.getTime());
    }
}