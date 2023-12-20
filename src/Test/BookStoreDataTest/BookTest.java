package Test.BookStoreDataTest;

import BookstoreData.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {
    private Book book;

    @BeforeEach
    public void setUp() {
        // Initialize a sample Book object before each test
        book = new Book("978-3-16-148410-0", "Sample Book", "Sample Description", 29.99f, "Sample Author", true, 10);
        book.addGenre(Book.Genre.FANTASY);
        book.addGenre(Book.Genre.ACTION);
    }

    @Test
    public void testGetters() {
        assertEquals("978-3-16-148410-0", book.getIsbn13());
        assertEquals("Sample Book", book.getTitle());
        assertEquals("Sample Author", book.getAuthor());
        assertEquals("29.99", book.getPrice());
        assertEquals("true", book.getPaperBack());
        assertEquals("10", book.getStock());
    }

    @Test
    public void testSetters() {
        book.setIsbn13("978-3-16-148410-1");
        assertEquals("978-3-16-148410-1", book.getIsbn13());

        book.setTitle("New Title");
        assertEquals("New Title", book.getTitle());

        book.setAuthor("New Author");
        assertEquals("New Author", book.getAuthor());

        book.setPrice(19.99f);
        assertEquals("19.99", book.getPrice());

        book.setPaperBack(false);
        assertEquals("false", book.getPaperBack());

        book.setStock(15);
        assertEquals("15", book.getStock());
    }

    @Test
    public void testAddGenre() {
        ArrayList<Book.Genre> genres = new ArrayList<>();
        genres.add(Book.Genre.FANTASY);
        genres.add(Book.Genre.ACTION);

        assertEquals(genres.toString(), book.getGenres().toString());
    }
    @Test
    public void testAddStock() {
        book.addStock(10);
        assertEquals(20, book.getStockInt());
    }

    @Test
    public void testRemoveStock() {
        book.removeStock(5);
        assertEquals(5, book.getStockInt());
    }

    @Test
    public void testGetGenres() {
        ArrayList<Book.Genre> genres = new ArrayList<>();
        genres.add(Book.Genre.FANTASY);
        genres.add(Book.Genre.ACTION);

        assertEquals(genres.toString(), book.getGenres().toString());
    }
}

