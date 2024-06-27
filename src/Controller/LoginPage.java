package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authOnLoginPage;

    @FXML
    private Button lastOnLoginPage;

    @FXML
    private TextField loginNameOnLoginPage;

    @FXML
    private Button loginOnLoginPage;

    @FXML
    private PasswordField passwordOnLoginPage;

    @FXML
    void initialize() {
        lastOnLoginPage.setOnAction(actionEvent -> {
            try {
                openHomePage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loginOnLoginPage.setOnAction(actionEvent -> {
            try {
                openRegistrationPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

//        authOnLoginPage.setOnAction(actionEvent -> { // Разделить на клиента и врача
//            try {
//                openRegistrationPage();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }

    @FXML
    private void openHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void openRegistrationPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/RegistrationPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


}
