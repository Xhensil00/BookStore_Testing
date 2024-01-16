package Test.TestFx;

import BookstoreData.Book;
import BookstoreData.BookData;
import Staff.Worker;
import Staff.WorkerData;
import Style.LoginPage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.framework.junit5.ApplicationTest;
import java.util.ArrayList;
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
    Button LogOutBtn;
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
        LogOutBtn = lookup("#LogOutBtn").queryAs(Button.class);

    }

    @AfterEach
    public void tearDownAll()
    {
        sleep(1000);
        LogOutBtn = lookup("#LogOutBtn").queryAs(Button.class);
        clickOn(LogOutBtn);
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
        clickOn(b1);
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
        assertNotNull(LogInBtn);
    }

    @Test
    void test_Add_Worker(){
        TabPane tab = lookup("#tabPane").queryAs(TabPane.class);
        Tab tab1 = tab.getTabs().get(1);
        int x1= (int) tab.getLayoutX()+150;
        int y1= (int) tab.getLayoutY()+20;
        clickOn(x1,y1);
        sleep(1000);
        Button addWorkerBtn = lookup("#addWorkerBtn").queryAs(Button.class);
        clickOn(addWorkerBtn);
        sleep(1000);
        TextField t0 = lookup("#nameTF").queryAs(TextField.class);
        TextField t1 = lookup("#emailTF").queryAs(TextField.class);
        TextField t2 = lookup("#phoneTF").queryAs(TextField.class);
        TextField t3 = lookup("#salaryTF").queryAs(TextField.class);
        PasswordField t4 = lookup("#passwordTF").queryAs(PasswordField.class);
        DatePicker datePicker = lookup("#datePicker").queryAs(DatePicker.class);
        RadioButton r1 =lookup("#maleRadio").queryAs(RadioButton.class);
        ChoiceBox location = lookup("#locationChoiceBox").queryAs(ChoiceBox.class);
        Button registerBtn = lookup("#RegisterBtn").queryAs(Button.class);
        String name="TestWorker";
        clickOn(t0);
        write(name);
        clickOn(t1);
        write("testWorker@gmail.com");
        clickOn(t2);
        write("0691234567");
        clickOn(t3);
        write("1300");
        clickOn(t4);
        write("123456");
        clickOn(datePicker);
        write("1/1/2001");
        clickOn(r1);
        clickOn(location);
        int x= (int)location.getLayoutX();
        int y= (int)location.getLayoutY();
        x+=450;
        y+=100;
        clickOn(x,y);
        clickOn(registerBtn);
        clickOn(x1,y1);
        bookTableView= lookup("#workerTable").queryAs(TableView.class);
        int n2=bookTableView.getItems().size();
        assertEquals(9,n2);

    }
    @Test
    void test_Edit_Worker(){
        TabPane tab = lookup("#tabPane").queryAs(TabPane.class);
        Tab tab1 = tab.getTabs().get(1);
        int x= (int) tab.getLayoutX()+150;
        int y= (int) tab.getLayoutY()+20;
        clickOn(x,y);
        sleep(1000);
        doubleClickOn(workerTableView);
        Worker worker =(Worker) workerTableView.getSelectionModel().getSelectedItem();
        Label workerEmail = lookup("#workerEmail").queryAs(Label.class);
        doubleClickOn(workerEmail);
        write("newEmail@gmail.com");
        press(KeyCode.ENTER);
        sleep(1000);
        Button editBtn= lookup("#editBtn").queryAs(Button.class);
        clickOn(editBtn);
        clickOn(x,y);
        workerTableView= lookup("#workerTable").queryAs(TableView.class);
        clickOn(workerTableView);
        worker =(Worker) workerTableView.getSelectionModel().getSelectedItem();
        assertEquals("newEmail@gmail.com",worker.getEmail());
    }
    @Test
    void deleteWorker(){
        TabPane tab = lookup("#tabPane").queryAs(TabPane.class);
        Tab tab1 = tab.getTabs().get(1);
        int x= (int) tab.getLayoutX()+150;
        int y= (int) tab.getLayoutY()+20;
        clickOn(x,y);
        sleep(1000);
        int n1=workerTableView.getItems().size();
        doubleClickOn(workerTableView);
        Worker worker =(Worker) workerTableView.getSelectionModel().getSelectedItem();
        clickOn(workerTableView);
        Button deleteWorkerBtn = lookup("#deletWorkerBtn").queryAs(Button.class);
        clickOn(deleteWorkerBtn);
        sleep(1000);
        workerTableView= lookup("#workerTable").queryAs(TableView.class);
        int n2=workerTableView.getItems().size();
        assertEquals(n1-1,n2);
    }
}
