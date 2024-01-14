package Test.TestFx;

import Style.LoginPage;
import Style.MainPage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.service.query.EmptyNodeQueryException;
import org.testfx.toolkit.PrimaryStageApplication;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestLoginFx extends ApplicationTest {
    Stage primaryStage;
    Parent sceneRoot;
    Button LogInBtn;
    Label wrongPassword;
    Label wrongEmail;
    TextField EmailTextField;
    TextField PasswordTextField;

    public void start(Stage stage){
        sceneRoot = new LoginPage(stage).getRoot();
        Scene scene = new Scene(sceneRoot, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @BeforeEach
    void setUp(){
        LogInBtn = lookup("#LogInBtn").queryAs(Button.class);
        EmailTextField = lookup("#EmailTextField").queryAs(TextField.class);
        PasswordTextField = lookup("#PasswordTextField").queryAs(TextField.class);
    }

    @AfterAll
    public static void tearDownAll()
    {
    }

    @Test
    void test_Wrong_Email(){
        clickOn(EmailTextField).write("Besnik");
        clickOn(LogInBtn);
        wrongEmail = lookup("#WrongEmailLabel").queryAs(Label.class);
        assertNotNull(wrongEmail);
    }
    @Test
    void test_Wrong_Password(){
        clickOn(EmailTextField).write("Admin");
        clickOn(PasswordTextField).write("2");
        clickOn(LogInBtn);
        wrongPassword = lookup("#WrongPasswordLabel").queryAs(Label.class);
        assertNotNull(wrongPassword);
    }
    @Test
    void test_Login_Successfully(){
        clickOn(EmailTextField).write("Admin");
        clickOn(PasswordTextField).write("1");
        clickOn(LogInBtn);
        assertThrows(EmptyNodeQueryException.class ,() ->{lookup("#WrongEmailLabel").queryAs(Label.class);});
        assertThrows(EmptyNodeQueryException.class ,() ->{lookup("#WrongPasswordLabel").queryAs(Label.class);});
    }




}
