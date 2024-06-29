package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Model;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Model;

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

    @FXML
    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
    }

//    @FXML
//    private void saveData() {
//        // Логика для сохранения данных
//    }
//
//    @FXML
//    private void cancelChange() throws IOException {
//        // Вернуться на страницу клиента
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/ClientAccountPage.fxml"));
//        Parent root = fxmlLoader.load();
//
//        ClientAccountPage clientAccountPageController = fxmlLoader.getController();
//        clientAccountPageController.setModel(model, primaryStage);
//
//        Stage stage = new Stage();
//        stage.setScene(new Scene(root));
//
//        Stage currentStage = (Stage) cancelButton.getScene().getWindow();
//        currentStage.close();
//
//        stage.show();
//    }
}
