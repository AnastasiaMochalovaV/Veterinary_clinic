package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class DoctorAccountPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> chooseDogOnDoctorPage;

    @FXML
    private Button homeOnDoctorPage;

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
        homeOnDoctorPage.setOnAction(actionEvent -> {
            try {
                goToHomePageAfterAuth();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @FXML
    private void goToHomePageAfterAuth() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/HomePageAfterAuth.fxml"));
        Parent root = fxmlLoader.load();

        HomePageAfterAuth homePageAfterAuthController = fxmlLoader.getController();
        homePageAfterAuthController.setModel(model, primaryStage);

        homePageAfterAuthController.setUserType(false);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) homeOnDoctorPage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }
}