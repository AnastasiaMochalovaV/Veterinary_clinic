package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import Model.Owner;

public class ClientAccountPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label addressLabel;

    @FXML
    private Button changeOnClientPage;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Button homeOnClientPage;

    @FXML
    private Button recordOnClientPage;

    private Stage primaryStage;
    private Model model;

    @FXML
    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
        initializeUserDetails();
    }

    private void initializeUserDetails() {
        Owner currentUser = model.getCurrentOwner();
        if (currentUser != null) {
            String fullName = currentUser.getSurname() + " " + currentUser.getName() + " " + currentUser.getPatronymic();
            fullNameLabel.setText(fullName);
            addressLabel.setText(currentUser.getAddress());
        }
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

    @FXML
    private void changeData() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/ChangeData.fxml"));
        Parent root = fxmlLoader.load();

        ChangeData changeData = fxmlLoader.getController();
        changeData.setModel(model, primaryStage);

        Owner currentOwner = model.getCurrentOwner();
        changeData.setOwnerDetails(currentOwner);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) changeOnClientPage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }

    @FXML
    private void createAppointment() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/AppointmentPage.fxml"));
        Parent root = fxmlLoader.load();

        AppointmentPage appointmentPageController = fxmlLoader.getController();
        appointmentPageController.setModel(model, primaryStage);

        Owner currentOwner = model.getCurrentOwner();
        appointmentPageController.setOwnerDetails(currentOwner);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) recordOnClientPage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }
}

