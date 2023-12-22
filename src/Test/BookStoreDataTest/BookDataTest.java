package Test.BookStoreDataTest;

import BookstoreData.Book;
import BookstoreData.BookData;
import Orders.BuyOrders;
import Orders.PurchaseOrders;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;
import java.io.*;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

public class BookDataTest {

    @TempDir
    public File tempFolder;

    private BookData bookData;
    private File tempFile;
    private Book book;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = new File(tempFolder, "tempBookDataFile.dat");
        BookData.setTestingTrue();
        boolean created= tempFile.createNewFile();
        bookData = new BookData();
        book = new Book("ISBN123", "Book Title", "Description", 20.0f, "Author", true, 0);
        bookData.getBooks().add(book);

    }

    @AfterEach
    public void tearDown() {
        if (tempFile.exists()) {
            boolean isDeleted=tempFile.delete();
        }
        BookData.setTestingFalse();
        bookData = null;
    }

    @Test
    public void testBookDataConstructor() {
        assertNotNull(bookData);
        assertNotNull(bookData.getBooks());
        assertNotNull(bookData.getFile());
        assertTrue(bookData.getFile().exists());
    }

    @Test
    public void testAddBooksToStock() throws IOException {
        bookData.setFile(tempFile);
        bookData.writeBookToFile(book);

        ArrayList<String> isbns = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        isbns.add(book.getIsbn13());
        quantities.add(5);
        BuyOrders.setTestingTrue();
        BuyOrders buyOrder = new BuyOrders(isbns, quantities, 100.0, "Admin");

        bookData.addBooksToStock(buyOrder);

        assertEquals(5, bookData.getBookQuantity(book.getIsbn13()));
        BuyOrders.setTestingFalse();
    }
    @Test
    public void testRemoveBooksFromStock() throws IOException {
        bookData.setFile(tempFile);
        book.addStock(5);
        bookData.writeBookToFile(book);
        ArrayList<String> isbns = new ArrayList<>();
        ArrayList<Integer> quantities = new ArrayList<>();
        isbns.add(book.getIsbn13());
        quantities.add(5);
        PurchaseOrders.setTestingTrue();
        PurchaseOrders purchaseOrders = new PurchaseOrders(isbns, quantities, 100.0, "Admin");

        bookData.removeBooksFromStock(purchaseOrders);

        assertEquals(0, bookData.getBookQuantity(book.getIsbn13()));
        PurchaseOrders.setTestingFalse();
    }

    @Test
    public void testReadBookData() throws IOException {
        bookData.setFile(tempFile);
        bookData.writeBookToFile(book);

        bookData.readBookData();

        Book retrievedBook = bookData.getBook("ISBN123");

        assertEquals(book.getIsbn13(), retrievedBook.getIsbn13());
        assertEquals(book.getTitle(), retrievedBook.getTitle());
        assertEquals(book.getPrice(), retrievedBook.getPrice());
        assertEquals(book.getAuthor(), retrievedBook.getAuthor());
        assertEquals(book.getStockInt(), retrievedBook.getStockInt());
    }

    @Test
    public void testWriteBookToFile() throws IOException {
        bookData.setFile(tempFile);

        boolean result = bookData.writeBookToFile(book);
        assertTrue(result);
        assertTrue(tempFile.exists());
    }
    @Test
    public void testValidIsbn13() {
        assertTrue(bookData.checkIsbn13("1234567890123"));
    }

    @Test
    public void testInvalidIsbn13() {
        assertFalse(bookData.checkIsbn13("invalidisbn"));
    }

    @Test
    public void testEmptyIsbn13() { assertFalse(bookData.checkIsbn13(""));}
    @Test
    public void testGetBookQuantity() {
        book.addStock(10);
        assertEquals(10,bookData.getBookQuantity(book.getIsbn13()));
    }
}