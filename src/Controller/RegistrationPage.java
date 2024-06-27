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

public class RegistrationPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField addressOnRegistrationPage;

    @FXML
    private Button lastOnRegistrationPage;

    @FXML
    private Button loginOnRegistrationPage;

    @FXML
    private TextField nameOnRegistrationPage;

    @FXML
    private PasswordField passwordOnRegistrationPage;

    @FXML
    private TextField patronymicOnRegistrationPage;

    @FXML
    private TextField phoneNumberOnRegistrationPage;

    @FXML
    private TextField surnameOnRegistrationPage;

    @FXML
    void initialize() {
        lastOnRegistrationPage.setOnAction(actionEvent -> {
            try {
                openHomePage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void openHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
