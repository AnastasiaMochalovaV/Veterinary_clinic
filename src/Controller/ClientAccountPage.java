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

public class ClientAccountPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button changeOnClientPage;

    @FXML
    private ComboBox<?> chooseDogOnClientPage;

    @FXML
    private Button homeOnClientPage;

    @FXML
    private Button recordOnClientPage;

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
        homeOnClientPage.setOnAction(actionEvent -> {
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

        homePageAfterAuthController.setUserType(true);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) homeOnClientPage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }
}