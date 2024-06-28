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

    private Connection connection;

    private boolean isOwner;

    @FXML
    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
    }

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

        authOnLoginPage.setOnAction(actionEvent -> {
            try {
                checkAccess();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void openHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/HomePage.fxml"));
        Parent root = fxmlLoader.load();

        HomePage homePageController = fxmlLoader.getController();
        homePageController.setModel(model, primaryStage);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) lastOnLoginPage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }

    @FXML
    private void openRegistrationPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/RegistrationPage.fxml"));
        Parent root = fxmlLoader.load();

        RegistrationPage registrationPageController = fxmlLoader.getController();
        registrationPageController.setModel(model, primaryStage);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) loginOnLoginPage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }

    @FXML
    private void checkAccess() throws IOException {
        if (model == null) {
            showAlert("Внутренняя ошибка: модель не установлена.");
            return;
        }

        String username = loginNameOnLoginPage.getText();
        String password = passwordOnLoginPage.getText();

        if (checkDoctorOnLoginPage.isSelected()) {
            boolean isDoctorAuthenticated = model.authenticateDoctor(username, password);
            if (isDoctorAuthenticated) {
                isOwner = false;
                openHomePageAfterAuth("/View/HomePageAfterAuth.fxml");
            } else {
                showAlert("Неверные данные врача");
            }
        } else {
            boolean isOwnerAuthenticated = model.authenticateOwner(username, password);
            if (isOwnerAuthenticated) {
                isOwner = true;
                openHomePageAfterAuth("/View/HomePageAfterAuth.fxml");
            } else {
                showAlert("Пользователь не найден");
            }
        }

//        boolean isOwnerAuthenticated = model.authenticateOwner(username, password);
//        if (isOwnerAuthenticated) {
//            isOwner = true;
//            openHomePageAfterAuth("/View/HomePageAfterAuth.fxml");
//            return;
//        }
//
//        boolean isDoctorAuthenticated = model.authenticateDoctor(username, password);
//        if (isDoctorAuthenticated) {
//            isOwner = false;
//            openHomePageAfterAuth("/View/HomePageAfterAuth.fxml");
//            return;
//        }
//
//        showAlert("Пользователь не найден");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка входа");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void openHomePageAfterAuth(String fxmlPath) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Parent root = fxmlLoader.load();

        HomePageAfterAuth homePageAfterAuthController = fxmlLoader.getController();
        homePageAfterAuthController.setModel(model, primaryStage);
        homePageAfterAuthController.setUserType(isOwner);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) authOnLoginPage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }

    @FXML
    private void clearFields() {
        loginNameOnLoginPage.clear();
        passwordOnLoginPage.clear();
    }
}

