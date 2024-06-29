package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Owner;
import Model.Doctor;

public class ChangeData {

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
    private TextField patronymicOnRegistrationPage;

    @FXML
    private TextField phoneNumberOnRegistrationPage;

    @FXML
    private TextField surnameOnRegistrationPage;

    private Model model;
    private Stage primaryStage;
//    private boolean isOwner;

    @FXML
    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
    }

    public void setOwnerDetails(Owner owner) {
        nameOnRegistrationPage.setText(owner.getName());
        surnameOnRegistrationPage.setText(owner.getSurname());
        patronymicOnRegistrationPage.setText(owner.getPatronymic());
        phoneNumberOnRegistrationPage.setText(owner.getPhoneNumber());
        addressOnRegistrationPage.setText(owner.getAddress());
    }

    @FXML
    private void openRegistrationPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientAccountPage.fxml"));
        Parent root = loader.load();
        ClientAccountPage controller = loader.getController();
        controller.setModel(model, primaryStage);

        Stage currentStage = (Stage) lastOnRegistrationPage.getScene().getWindow();
        currentStage.close();

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    private void saveUserData() throws SQLException {
        Owner owner = model.getCurrentOwner();
        owner.setSurname(surnameOnRegistrationPage.getText());
        owner.setName(nameOnRegistrationPage.getText());
        owner.setPatronymic(patronymicOnRegistrationPage.getText());
        owner.setPhoneNumber(phoneNumberOnRegistrationPage.getText());
        owner.setAddress(addressOnRegistrationPage.getText());
        model.updateOwner(owner);
    }

    @FXML
    private void loginOnRegistrationPageClicked() throws SQLException, IOException {
        saveUserData();
        openRegistrationPage();
    }



//    public void setDoctorDetails(Doctor doctor) {
//        nameOnRegistrationPage.setText(doctor.getName());
//        surnameOnRegistrationPage.setText(doctor.getSurname());
//        patronymicOnRegistrationPage.setText(doctor.getPatronymic());
//        phoneNumberOnRegistrationPage.setText(doctor.getPhoneNumber());
//        addressOnRegistrationPage.setText(doctor.getAddress());
//    }

//    @FXML
//    private void openRegistrationPage() throws IOException {
//        if (isOwner) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientAccountPage.fxml"));
//            Parent root = loader.load();
//            ClientAccountPage controller = loader.getController();
//            controller.setModel(model, primaryStage);
//
//            Stage currentStage = (Stage) lastOnRegistrationPage.getScene().getWindow();
//            currentStage.close();
//
//            primaryStage.setScene(new Scene(root, 1280, 720));
//            primaryStage.show();
//        } else {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DoctorAccountPage.fxml"));
//            Parent root = loader.load();
//            DoctorAccountPage controller = loader.getController();
//            controller.setModel(model, primaryStage);
//
//            Stage currentStage = (Stage) lastOnRegistrationPage.getScene().getWindow();
//            currentStage.close();
//
//            primaryStage.setScene(new Scene(root, 1280, 720));
//            primaryStage.show();
//        }
//    }

//    @FXML
//    private void saveUserData() throws SQLException {
//        if (isOwner) {
//            Owner owner = model.getCurrentOwner();
//            owner.setSurname(surnameOnRegistrationPage.getText());
//            owner.setName(nameOnRegistrationPage.getText());
//            owner.setPatronymic(patronymicOnRegistrationPage.getText());
//            owner.setPhoneNumber(phoneNumberOnRegistrationPage.getText());
//            owner.setAddress(addressOnRegistrationPage.getText());
//            model.updateOwner(owner);
//        } else {
//            Doctor doctor = model.getCurrentDoctor();
//            doctor.setName(nameOnRegistrationPage.getText());
//            doctor.setSurname(surnameOnRegistrationPage.getText());
//            doctor.setPatronymic(patronymicOnRegistrationPage.getText());
//            doctor.setPhoneNumber(phoneNumberOnRegistrationPage.getText());
//            doctor.setAddress(addressOnRegistrationPage.getText());
//            model.updateDoctor(doctor);
//        }
//    }
}
