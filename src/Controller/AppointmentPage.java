package Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import Model.Model;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import Model.Owner;

public class AppointmentPage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> breed;

    @FXML
    private Button createOnAppointmentPage;

    @FXML
    private DatePicker dateOnAppointmentPage;

    @FXML
    private ComboBox<String> doctor;

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

    @FXML
    private ComboBox<LocalTime> timeOnAppointmentPage;

    private Stage primaryStage;
    private Model model;

    @FXML
    public void setModel(Model model, Stage primaryStage) {
        this.model = model;
        this.primaryStage = primaryStage;
        initializeTimeComboBox();
        initializeBreedComboBox();
        initializeDoctorComboBox();
    }

    public void setOwnerDetails(Owner owner) {
        nameOnAppointmentPage.setText(owner.getName());
        surnameOnAppointmentPage.setText(owner.getSurname());
        patronymicOnAppointmentPage.setText(owner.getPatronymic());
    }

    private void initializeTimeComboBox() {
        timeOnAppointmentPage.setItems(FXCollections.observableArrayList(
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                LocalTime.of(12, 0),
                LocalTime.of(13, 0),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                LocalTime.of(16, 0),
                LocalTime.of(17, 0)
        ));
    }

    private void initializeBreedComboBox() {
        try {
            List<String> breeds = model.fetchAllBreeds();
            breed.setItems(FXCollections.observableArrayList(breeds));
        } catch (SQLException e) {
            showAlert("Ошибка", "Произошла ошибка при загрузке списка пород: " + e.getMessage());
        }
    }

    private void initializeDoctorComboBox() {
        try {
            List<String> doctors = model.fetchAllDoctors();
            doctor.setItems(FXCollections.observableArrayList(doctors));
        } catch (SQLException e) {
            showAlert("Ошибка", "Произошла ошибка при загрузке списка пород: " + e.getMessage());
        }
    }

    @FXML
    private void goBackToClientAccountPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ClientAccountPage.fxml"));
        Parent root = loader.load();
        ClientAccountPage controller = loader.getController();
        controller.setModel(model, primaryStage);

        Stage currentStage = (Stage) lastOnAppointmentPage.getScene().getWindow();
        currentStage.close();

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    private void createAppointment() {
        String name = nameOnAppointmentPage.getText();
        String surname = surnameOnAppointmentPage.getText();
        String patronymic = patronymicOnAppointmentPage.getText();
        String nameDog = nameDogOnAppointmentPage.getText();
        String selectedBreed = breed.getValue();
        String selectedDoctor = doctor.getValue();
        LocalDate date = dateOnAppointmentPage.getValue();
        LocalTime time = timeOnAppointmentPage.getValue();

        if (name.isEmpty() || surname.isEmpty() || patronymic.isEmpty() || nameDog.isEmpty() || selectedBreed == null || selectedDoctor == null || date == null || time == null) {
            showAlert("Ошибка", "Пожалуйста, заполните все поля.");
            return;
        }

        try {
            int animalId = model.fetchAnimalId(nameDog, selectedBreed, name, surname, patronymic);

            if (animalId == -1) {
                animalId = model.createAnimal(nameDog, selectedBreed, name, surname, patronymic);
                if (animalId == -1) {
                    showAlert("Ошибка", "Не удалось создать запись о животном. Попробуйте снова.");
                    return;
                }
            }

            boolean success = model.createAppointment(name, surname, patronymic, nameDog, selectedBreed, selectedDoctor, date, time);
            if (success) {
                showAlert("Успех", "Запись успешно создана.");
            } else {
                showAlert("Ошибка", "Не удалось создать запись о приеме. Попробуйте снова.");
            }
        } catch (SQLException e) {
            showAlert("Ошибка", "Произошла ошибка при доступе к базе данных: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
