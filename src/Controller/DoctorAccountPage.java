package Controller;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import Model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import Model.Doctor;
import Model.Appointment;


public class DoctorAccountPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label addressLabel;

    @FXML
    private TableColumn<?, ?> breedRelevant;

    @FXML
    private Button changeOnDoctorPage;

    @FXML
    private Button confirm;

    @FXML
    private TableColumn<?, ?> dateRelevant;

    @FXML
    private TableColumn<?, ?> diagnosisRelevant;

    @FXML
    private Label fullNameLabel;

    @FXML
    private Button homeOnDoctorPage;

    @FXML
    private TableColumn<?, ?> ownerRelevant;

    @FXML
    private Button recordOnDoctorPage;

    @FXML
    private TableView<?> tableRelevant;

    @FXML
    private TableColumn<?, ?> timeRelevant;

    @FXML
    void changeData(ActionEvent event) {

    }

    @FXML
    void createAppointment(ActionEvent event) {

    }

    @FXML
    void goToHomePageAfterAuth(ActionEvent event) {

    }

    private Stage primaryStage;

    private Model model;

    @FXML
    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
        initializeUserDetails();
        loadCurrentAppointments();
    }

    @FXML
    private void initializeUserDetails() {
        Doctor currentUser = model.getCurrentDoctor();
        if (currentUser != null) {
            fullNameLabel.setText(currentUser.getName());
            addressLabel.setText(currentUser.getAddress());
        }
    }

    @FXML
    private void loadCurrentAppointments() {
//        dateRelevant.setCellValueFactory(new PropertyValueFactory<>("date"));
//        timeRelevant.setCellValueFactory(new PropertyValueFactory<>("time"));
//        breedRelevant.setCellValueFactory(new PropertyValueFactory<>("breed"));
//        ownerRelevant.setCellValueFactory(new PropertyValueFactory<>("owner"));
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

    @FXML
    private void changeData() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/ChangeData.fxml"));
        Parent root = fxmlLoader.load();

        ChangeData changeDataController = fxmlLoader.getController();
        changeDataController.setModel(model, primaryStage);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) changeOnDoctorPage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }

    @FXML
    void createAppointment() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/AppointmentPage.fxml"));
        Parent root = fxmlLoader.load();

        AppointmentPage appointmentPageController = fxmlLoader.getController();
        appointmentPageController.setModel(model, primaryStage);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        Stage currentStage = (Stage) recordOnDoctorPage.getScene().getWindow();
        currentStage.close();

        stage.show();
    }
}