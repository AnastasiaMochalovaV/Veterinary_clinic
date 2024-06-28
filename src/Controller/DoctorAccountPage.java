package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class DoctorAccountPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<?> chooseDogOnDoctorPage;

    @FXML
    private Button homeOnDoctorPage;

    @FXML
    void initialize() {
        assert chooseDogOnDoctorPage != null : "fx:id=\"chooseDogOnDoctorPage\" was not injected: check your FXML file 'DoctorAccountPage.fxml'.";
        assert homeOnDoctorPage != null : "fx:id=\"homeOnDoctorPage\" was not injected: check your FXML file 'DoctorAccountPage.fxml'.";

    }

}