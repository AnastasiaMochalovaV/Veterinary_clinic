package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Model;

public class LoginPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authOnLoginPage;

    @FXML
    private CheckBox checkDoctorOnLoginPage;

    @FXML
    private Button lastOnLoginPage;

    @FXML
    private TextField loginNameOnLoginPage;

    @FXML
    private Button loginOnLoginPage;

    @FXML
    private PasswordField passwordOnLoginPage;

    private Stage primaryStage;

    private Model model;

    private String username;
    private String password;

    @FXML
    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
    }

    @FXML
    private void openHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
        Parent root = loader.load();
        HomePage controller = loader.getController();
        controller.setModel(model, primaryStage);

        Stage currentStage = (Stage) loginOnLoginPage.getScene().getWindow();
        currentStage.close();

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    private void openRegistrationPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/RegistrationPage.fxml"));
        Parent root = loader.load();
        RegistrationPage controller = loader.getController();
        controller.setModel(model, primaryStage);

        Stage currentStage = (Stage) loginOnLoginPage.getScene().getWindow();
        currentStage.close();

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка входа");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void openHomePageAfterAuth() throws IOException {
        boolean isOwner = !checkDoctorOnLoginPage.isSelected();
        if (isOwner) {
            int idOwner = model.getOwnerId(loginNameOnLoginPage.getText(), passwordOnLoginPage.getText());
            if (idOwner != -1) {
                loadHomePageAfterAuth(isOwner);
            } else {
                showAlert("Пользователь не найден");
            }
        } else {
            int idDoc = model.getDoctorId(loginNameOnLoginPage.getText(), passwordOnLoginPage.getText());
            if (idDoc != -1) {
                loadHomePageAfterAuth(isOwner);
            } else {
                showAlert("Пользователь не найден");
            }
        }
    }

    private void loadHomePageAfterAuth(boolean isOwner) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/HomePageAfterAuth.fxml"));
        Parent root = loader.load();
        HomePageAfterAuth controller = loader.getController();
        controller.setModel(model, primaryStage);
        controller.setUserType(isOwner);

        Stage currentStage = (Stage) loginOnLoginPage.getScene().getWindow();
        currentStage.close();

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }
}
