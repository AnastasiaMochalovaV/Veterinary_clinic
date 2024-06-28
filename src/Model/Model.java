package Model;

import java.sql.*;

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

    // Метод для аутентификации владельца
    public boolean authenticateOwner(String login, String password) {
        String query = "SELECT COUNT(*) FROM owners WHERE login = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
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
    public boolean authenticateDoctor(String login, String password) {
        String query = "SELECT COUNT(*) FROM doctors WHERE login = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, login);
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
}
