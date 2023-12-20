package Test.BookStoreDataTest;

import BookstoreData.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

    Book book;
    @BeforeEach
    void setUp(){
        book = new Book("1234567890123",
                "LOTR",
                "abc",
                1200,
                "Qazimi",
                true,
                20);
    }
    @Test
    void testRemoveStockWhenThereIsStock(){
        book.removeStock(10);
        Assertions.assertEquals(10,book.getStockInt());
    }
    @Test
    void testRemoveStockWhenThereIsNotEnoughStock(){
        book.removeStock(25);
        Assertions.assertEquals(20,book.getStockInt());
    }
    @Test
    void testRemoveStockWhenThereIsNoStock(){
        book.removeStock(20);
        book.removeStock(10);
        Assertions.assertEquals(0,book.getStockInt());
    }
    @Test
    void testAddStockWhenThereIsNotEnoughStock(){
        book.addStock(25);
        Assertions.assertEquals(45,book.getStockInt());
    }

}
