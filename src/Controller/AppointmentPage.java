package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Model;

public class AppointmentPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField breedOnAppointmentPage;

    @FXML
    private Button createOnAppointmentPage;

    @FXML
    private DatePicker dateOnAppointmentPage;

    @FXML
    private Button lastOnAppointmentPage;

    @FXML
    private TextField nameDogOnAppointmentPage;

    @FXML
    private TextField nameOnAppointmentPage;

    @FXML
    private TextField patronymicOnAppointmentPage;

    @FXML
    private TextField surnameOnAppointmentPage;

    private Stage primaryStage;

    private Model model;


    @FXML
    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
    }

}
