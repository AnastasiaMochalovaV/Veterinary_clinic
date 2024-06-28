package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Stage;
import Model.Model;

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

    private Stage primaryStage;

    private Model model;

    private Connection connection;

    @FXML
    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
    }

    @FXML
    void initialize() {
        lastOnRegistrationPage.setOnAction(actionEvent -> {
            try {
                openHomePage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loginOnRegistrationPage.setOnAction(actionEvent -> {
            try {
                insertOwnerIntoDatabase();
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

        Stage currentStage = (Stage) lastOnRegistrationPage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }

    @FXML
    private boolean insertOwnerIntoDatabase() {
        String surname = surnameOnRegistrationPage.getText();
        String name = nameOnRegistrationPage.getText();
        String patronymic = patronymicOnRegistrationPage.getText();
        String phoneNumber = phoneNumberOnRegistrationPage.getText();
        String address = addressOnRegistrationPage.getText();
        String password = passwordOnRegistrationPage.getText();

        return model.registerOwner(surname, name, patronymic, phoneNumber, address, password);
    }
}