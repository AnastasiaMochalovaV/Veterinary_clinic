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
import Model.Model;

public class HomePageAfterAuth {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button accountOnHomePage;

    @FXML
    private Button exitOnHomePage;

    private Stage primaryStage;

    private Model model;

    private boolean isOwner;

    public void setUserType(boolean isOwner) {
        this.isOwner = isOwner;
    }

    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
    }

    @FXML
    private void openAccountPage() throws IOException {
        if (isOwner) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientAccountPage.fxml"));
            Parent root = loader.load();
            ClientAccountPage controller = loader.getController();
            controller.setModel(model, primaryStage);

            Stage currentStage = (Stage) accountOnHomePage.getScene().getWindow();
            currentStage.close();

            primaryStage.setScene(new Scene(root, 1280, 720));
            primaryStage.show();
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DoctorAccountPage.fxml"));
            Parent root = loader.load();
            DoctorAccountPage controller = loader.getController();
            controller.setModel(model, primaryStage);

            Stage currentStage = (Stage) accountOnHomePage.getScene().getWindow();
            currentStage.close();

            primaryStage.setScene(new Scene(root, 1280, 720));
            primaryStage.show();
        }
    }

    public void exitToOpenPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HomePage.fxml"));

        Parent root = loader.load();
        HomePage controller = loader.getController();
        controller.setModel(model, primaryStage);

        Stage currentStage = (Stage) exitOnHomePage.getScene().getWindow();
        currentStage.close();

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }
}
