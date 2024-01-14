package Test.TestFx;

import BookstoreData.Book;
import BookstoreData.BookData;
import Staff.WorkerData;
import Style.LoginPage;
import Style.MainPage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.service.query.EmptyNodeQueryException;
import org.testfx.toolkit.PrimaryStageApplication;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
public class TestMainFx extends ApplicationTest {
    Stage primaryStage;
    Parent sceneRoot;
    Button LogInBtn;
    TextField EmailTextField;
    TextField PasswordTextField;
    VBox BookInfoHolder;
    BookData books;
    WorkerData workers;
    Button addBookBtn;
    Button purchaseBookBtn;
    Button addBookToStockBtn;
    TableView bookTableView;
    TableView workerTableView;
    public void start(Stage stage){
        sceneRoot = new LoginPage(stage).getRoot();
        Scene scene = new Scene(sceneRoot, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
    @BeforeAll
    static void startUp(){
    }
    @BeforeEach
    void setUp(){
        LogInBtn = lookup("#LogInBtn").queryAs(Button.class);
        EmailTextField = lookup("#EmailTextField").queryAs(TextField.class);
        PasswordTextField = lookup("#PasswordTextField").queryAs(TextField.class);
        clickOn(EmailTextField).write("Admin");
        clickOn(PasswordTextField).write("1");
        clickOn(LogInBtn);

        addBookBtn =  lookup("#addBookBtn").queryAs(Button.class);
        purchaseBookBtn =  lookup("#purchaseBookBtn").queryAs(Button.class);
        addBookToStockBtn = lookup("#addBookToStockBtn").queryAs(Button.class);
        bookTableView = lookup("#bookTable").queryAs(TableView.class);
        workerTableView = lookup("#workerTable").queryAs(TableView.class);

    }

    @AfterAll
    public static void tearDownAll()
    {
    }
    @Test
    void test_Purchasing_Books(){
        doubleClickOn(bookTableView);
        Book book =(Book) bookTableView.getSelectionModel().getSelectedItem();
        int n1=book.getStockInt();
        TextField amount;
        amount = lookup("#nrBooks").queryAs(TextField.class);
        clickOn(amount);
        write("20");
        press(KeyCode.ENTER);
        clickOn(purchaseBookBtn);
        sleep(1000);
        bookTableView = lookup("#bookTable").queryAs(TableView.class);
        doubleClickOn(bookTableView);
        book=(Book) bookTableView.getSelectionModel().getSelectedItem();
        int n2=book.getStockInt();
        assertEquals(n1-20,n2);
    }
    @Test
    void test_AddToStock(){
        doubleClickOn(bookTableView);
        Book book =(Book) bookTableView.getSelectionModel().getSelectedItem();
        int n1=book.getStockInt();
        TextField amount;
        amount = lookup("#nrBooks").queryAs(TextField.class);
        clickOn(amount);
        write("20");
        press(KeyCode.ENTER);
        clickOn(addBookToStockBtn);
        sleep(1000);
        bookTableView = lookup("#bookTable").queryAs(TableView.class);
        doubleClickOn(bookTableView);
        book=(Book) bookTableView.getSelectionModel().getSelectedItem();
        int n2=book.getStockInt();
        assertEquals(n1+20,n2);
    }
    @Test
    void test_Add_Books(){
        clickOn(addBookBtn);
        sleep(1000);
        TextField t0 = lookup("#titleTF").queryAs(TextField.class);
        TextField t1 = lookup("#isbnTF").queryAs(TextField.class);
        TextField t2 = lookup("#priceTF").queryAs(TextField.class);
        RadioButton r1 =lookup("#rbPaperback").queryAs(RadioButton.class);
        TextArea t3 = lookup("#descriptionTA").queryAs(TextArea.class);
        TextField t4 = lookup("#authorTF").queryAs(TextField.class);
        CheckBox c1 = lookup("#CheckBoxes").queryAs(CheckBox.class);
        Button b1 = lookup("#SubmitBtn").queryAs(Button.class);
        String name="TestBook";
        clickOn(t0);
        write(name);
        clickOn(t1);
        write("1234567895692");
        clickOn(t2);
        write("1300");
        clickOn(r1);
        clickOn(t3);
        write("description");
        clickOn(t4);
        write("Anita Maxwynn");
        clickOn(c1);
        ArrayList<Book> temp= new ArrayList<>();
        for(Object a : bookTableView.getItems()) temp.add((Book)a);
        boolean proceed=true;
        for(Book a : temp)if(a.getTitle().equals(name))proceed=false;
        if(proceed)clickOn(b1);
        temp= new ArrayList<>();
        for(Object a : bookTableView.getItems()) temp.add((Book)a);
        Book addedBook=null;
        for(Book a : temp)if(a.getTitle().equals(name))addedBook=a;
        assertNotNull(addedBook);
    }
    @Test
    void test_Login_LogOut(){
        Button logOut= lookup("#LogOutBtn").queryButton();
        clickOn(logOut);
        assertFalse(lookup("LogOutBtn").tryQuery().isPresent());
    }
}
