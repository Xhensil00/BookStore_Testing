package Test.BookStoreDataTest;

import BookstoreData.Book;
import BookstoreData.BookData;
import Orders.BuyOrders;
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
        tempFile.createNewFile();
        bookData = new BookData();
        book = new Book("ISBN123", "Book Title", "Description", 20.0f, "Author", true, 0);
        bookData.getBooks().add(book);
    }

    @AfterEach
    public void tearDown() {
        if (tempFile.exists()) {
            tempFile.delete();
        }
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
        BuyOrders buyOrder = new BuyOrders(isbns, quantities, 100.0, "Admin");

        bookData.addBooksToStock(buyOrder);

        assertEquals(5, bookData.getBookQuantity(book.getIsbn13()));
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
}