package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/veterinary_clinic";
    private static final String DB_USER = "veterinary_clinic";
    private static final String DB_PASSWORD = "veterinary_clinic";

    private Connection connection;

    public Model() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удается подключиться к базе данных", e);
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Метод для аутентификации владельца
    public boolean authenticateOwner(String phoneNumber, String password) {
        String query = "SELECT COUNT(*) FROM owners WHERE phone_number = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Метод для аутентификации врача
    public boolean authenticateDoctor(String phoneNumber, String password) {
        String query = "SELECT COUNT(*) FROM doctors WHERE phone_number = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Метод для регистрации владельца
    public boolean registerOwner(String surname, String name, String patronymic, String phoneNumber, String address, String password) {
        String query = "INSERT INTO owners (surname, name, patronymic, phone_number, address, password) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, surname);
            statement.setString(2, name);
            statement.setString(3, patronymic);
            statement.setString(4, phoneNumber);
            statement.setString(5, address);
            statement.setString(6, password);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Метод для получения списка приемов конкретного питомца
    public List<String> getAppointments(String ownerSurname, String ownerName, String ownerPatronymic, String animalName, String breedName) {
        String query = "SELECT " +
                "ap.date, " +
                "ap.time, " +
                "d.name AS doctor_name, " +
                "diag.status AS diagnosis_status, " +
                "dis.common_name AS common_disease_name " +
                "FROM appointments ap " +
                "JOIN animals a ON ap.animal_id = a.animal_id " +
                "JOIN breeds b ON a.breed_id = b.breed_id " +
                "JOIN owners o ON a.owner_id = o.owner_id " +
                "JOIN doctors d ON ap.doctor_id = d.doctor_id " +
                "LEFT JOIN diagnoses diag ON ap.diagnosis_id = diag.diagnosis_id " +
                "LEFT JOIN diseases dis ON diag.disease_id = dis.disease_id " +
                "WHERE " +
                "o.surname = ? AND " +
                "o.name = ? AND " +
                "o.patronymic = ? AND " +
                "a.name = ? AND " +
                "b.name = ? " +
                "ORDER BY ap.date, ap.time";

        List<String> appointments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ownerSurname);
            statement.setString(2, ownerName);
            statement.setString(3, ownerPatronymic);
            statement.setString(4, animalName);
            statement.setString(5, breedName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String appointment = String.format("Date: %s, Time: %s, Doctor: %s, Diagnosis: %s - %s",
                        resultSet.getDate("date"), resultSet.getTime("time"), resultSet.getString("doctor_name"),
                        resultSet.getString("diagnosis_status"), resultSet.getString("common_disease_name"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Метод для получения данных владельца
    public String[] getOwnerData(String phoneNumber, String password) {
        String query = "SELECT surname, name, patronymic, address FROM owners WHERE phone_number = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String surname = resultSet.getString("surname");
                String name = resultSet.getString("name");
                String patronymic = resultSet.getString("patronymic");
                String address = resultSet.getString("address");
                String fullName = surname + " " + name + " " + patronymic;
                System.out.println(fullName + ' ' + address);
                return new String[]{fullName, address};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Метод для получения данных врача
    public String[] getDoctorData(String phoneNumber, String password) {
        String query = "SELECT surname, name, patronymic, address FROM doctors WHERE phone_number = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String surname = resultSet.getString("surname");
                String name = resultSet.getString("name");
                String patronymic = resultSet.getString("patronymic");
                String address = resultSet.getString("address");
                String fullName = surname + " " + name + " " + patronymic;
                System.out.println(fullName + ' ' + address);
                return new String[]{fullName, address};
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getPetsData(String phoneNumber) {
        List<String> pets = new ArrayList<>();
        String query = "SELECT name FROM animals WHERE owner_id = (SELECT owner_id FROM owners WHERE phone_number = ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                pets.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }
}
