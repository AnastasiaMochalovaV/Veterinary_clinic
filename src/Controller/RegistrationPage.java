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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Stage;
import Model.Model;
import Model.Owner;

public class RegistrationPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField addressOnRegistrationPage;

//    @FXML
//    private Button lastOnRegistrationPage;
//
//    @FXML
//    private Button loginOnRegistrationPage;

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

    @FXML
    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
    }

    @FXML
    private void insertOwnerIntoDatabase() throws IOException {
        String surname = surnameOnRegistrationPage.getText();
        String name = nameOnRegistrationPage.getText();
        String patronymic = patronymicOnRegistrationPage.getText();
        String phoneNumber = phoneNumberOnRegistrationPage.getText();
        String address = addressOnRegistrationPage.getText();
        String password = passwordOnRegistrationPage.getText();

        Owner owner = new Owner(0, surname, name, patronymic, phoneNumber, address, password);

        if(!model.registerOwner(owner)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Пользователь уже существует");
            alert.showAndWait();
        }
        else{
            openHomePage();
        }
    }

    @FXML
    private void openHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));

        Parent root = loader.load();
        HomePage controller = loader.getController();
        controller.setModel(model, primaryStage);

        Stage currentStage = (Stage) surnameOnRegistrationPage.getScene().getWindow();
        currentStage.close();

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }
}