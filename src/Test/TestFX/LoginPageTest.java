package Test.TestFX;
import Style.LoginPage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.query.NodeQuery;

import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
class LoginPageTest {

    private TextField emailTextField;
    private TextField passwordTextField;
    private Button loginButton;

    @Start
    private void start(Stage stage) {
        LoginPage loginPage = new LoginPage(stage);
        Scene scene = new Scene(loginPage.getRoot(), 800, 600);
        stage.setScene(scene);
        stage.show();

        // Initialize references to UI components
        GridPane center = (GridPane) scene.lookup("center");
        emailTextField = (TextField) center.lookup("Email");
        passwordTextField = (TextField) center.lookup("Password");
        loginButton = (Button) scene.lookup("Enter");
    }

    @Test
    void testLoginWithValidCredentials(FxRobot robot) {
        robot.clickOn(emailTextField).write("Admin");
        robot.clickOn(passwordTextField).write("1");

        robot.clickOn(loginButton);

    }

    @Test
    void testInvalidLogin(FxRobot robot) {
        robot.clickOn(emailTextField).write("invalid@example.com");
        robot.clickOn(passwordTextField).write("wrongpassword");

        robot.clickOn(loginButton);

        verifyThat(".error-message", LabeledMatchers.hasText("Invalid credentials. Please try again."));
    }

    @Test
    void testEmptyFields(FxRobot robot) {
        // Test behavior when fields are empty
        robot.clickOn(loginButton);

        verifyThat(".error-message", LabeledMatchers.hasText("Please enter your email and password."));
    }

    @Test
    void testCancelButton(FxRobot robot) {
        // Test behavior of cancel or clear button
        robot.clickOn(emailTextField).write("test@example.com");
        robot.clickOn(passwordTextField).write("password");

        robot.press(KeyCode.ESCAPE);

        verifyThat((NodeQuery) emailTextField, LabeledMatchers.hasText(""));
        verifyThat((NodeQuery) passwordTextField, LabeledMatchers.hasText(""));
    }
}

