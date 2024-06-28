package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authOnHomePage;

    @FXML
    private Button loginOnHomePage;

    @FXML
    void initialize() {
//        authOnHomePage.setOnAction(actionEvent -> {
//            System.out.println("Войти");
//        });

        authOnHomePage.setOnAction(actionEvent -> {
            try {
                openLoginPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loginOnHomePage.setOnAction(actionEvent -> {
            try {
                openRegistrationPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void openLoginPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/LoginPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) authOnHomePage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }

    @FXML
    private void openRegistrationPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/RegistrationPage.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) loginOnHomePage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }
}