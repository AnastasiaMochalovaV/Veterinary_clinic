package Model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/veterinary_clinic";
    private static final String DB_USER = "veterinary_clinic";
    private static final String DB_PASSWORD = "veterinary_clinic";

    private Connection connection;
    private Owner currentOwner;
    private Doctor currentDoctor;

    private Owner owner = new Owner();
    private Doctor doctor = new Doctor();

    public Model() {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удается подключиться к базе данных", e);
        }
    }

    // Методы для работы с текущим пользователем
    public Owner getCurrentOwner() {
        return currentOwner;
    }

    public void setCurrentOwner(Owner currentOwner) {
        this.currentOwner = currentOwner;
    }

    public Doctor getCurrentDoctor() {
        return currentDoctor;
    }

    public void setCurrentDoctor(Doctor currentDoctor) {
        this.currentDoctor = currentDoctor;
    }

    public Owner getOwnerById(int ownerId) {
        String query = "SELECT surname, name, patronymic, phone_number, address FROM owners WHERE owner_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ownerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Owner owner = new Owner();
                owner.setOwnerId(ownerId);
                owner.setSurname(resultSet.getString("surname"));
                owner.setName(resultSet.getString("name"));
                owner.setPatronymic(resultSet.getString("patronymic"));
                owner.setPhoneNumber(resultSet.getString("phone_number"));
                owner.setAddress(resultSet.getString("address"));
                return owner;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Doctor getDoctorById(int doctorId) {
        String query = "SELECT name, address, phone_number FROM doctors WHERE doctor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, doctorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(doctorId);
                doctor.setName(resultSet.getString("name"));
                doctor.setAddress(resultSet.getString("address"));
                doctor.setPhoneNumber(resultSet.getString("phone_number"));
                return doctor;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Метод для получения id владельца по номеру телефона и паролю
    public int getOwnerId(String phoneNumber, String password) {
        String query = "SELECT owner_id FROM owners WHERE phone_number = ? AND password = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("owner_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // Метод для получения id врача по номеру телефона и паролю
    public int getDoctorId(String phoneNumber, String password) {
        String query = "SELECT doctor_id FROM doctors WHERE phone_number = ? AND password = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, phoneNumber);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("doctor_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // Метод для регистрации владельца
    public boolean registerOwner(Owner owner) {
        String query = "INSERT INTO owners (surname, name, patronymic, phone_number, address, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, owner.getSurname());
            statement.setString(2, owner.getName());
            statement.setString(3, owner.getPatronymic());
            statement.setString(4, owner.getPhoneNumber());
            statement.setString(5, owner.getAddress());
            statement.setString(6, owner.getPassword());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int ownerId = generatedKeys.getInt(1);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Метод для обновления данных владельца
    public void updateOwner(Owner owner) throws SQLException {
        String query = "UPDATE owners SET surname = ?, name = ?, patronymic = ?, phone_number = ?, address = ? WHERE owner_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, owner.getSurname());
            statement.setString(2, owner.getName());
            statement.setString(3, owner.getPatronymic());
            statement.setString(4, owner.getPhoneNumber());
            statement.setString(5, owner.getAddress());
            statement.setInt(6, owner.getOwnerId());
            statement.executeUpdate();
        }
    }

    // Метод для обновления данных доктора
    public void updateDoctor(Doctor doctor) throws SQLException {
        String query = "UPDATE doctors SET name = ?, phone_number = ?, address = ? WHERE doctor_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctor.getName());
            statement.setString(2, doctor.getPhoneNumber());
            statement.setString(3, doctor.getAddress());
            statement.setInt(4, doctor.getDoctorId());
            statement.executeUpdate();
        }
    }

    // Метод для получения списка всех врачей
    public List<String> fetchAllDoctors() throws SQLException {
        List<String> doctors = new ArrayList<>();
        String query = "SELECT name FROM doctors";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                doctors.add(resultSet.getString("name"));
            }
        }
        return doctors;
    }

    public List<String> fetchAllBreeds() throws SQLException {
        List<String> breeds = new ArrayList<>();
        String query = "SELECT name FROM breeds";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                breeds.add(resultSet.getString("name"));
            }
        }
        return breeds;
    }

    // Метод для получения идентификатора врача по имени
    private int getDoctorIdByName(String doctorName) throws SQLException {
        String query = "SELECT doctor_id FROM doctors WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, doctorName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("doctor_id");
            }
        }
        throw new SQLException("Врач с именем " + doctorName + " не найден в базе данных.");
    }

    // Метод для создания приема
    public boolean createAppointment(String ownerName, String ownerSurname, String ownerPatronymic,
                                     String animalName, String breed, String doctorName, LocalDate date, LocalTime time) throws SQLException {
        int animalId = fetchAnimalId(animalName, breed, ownerName, ownerSurname, ownerPatronymic);
        if (animalId == -1) {
            throw new SQLException("Животное не найдено в базе данных.");
        }

        int doctorId = getDoctorIdByName(doctorName); // Получаем идентификатор врача по имени

        // Insert appointment record
        String insertAppointmentQuery = "INSERT INTO appointments (date, time, animal_id, doctor_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement insertAppointmentStatement = connection.prepareStatement(insertAppointmentQuery)) {
            insertAppointmentStatement.setDate(1, Date.valueOf(date));
            insertAppointmentStatement.setTime(2, Time.valueOf(time));
            insertAppointmentStatement.setInt(3, animalId);
            insertAppointmentStatement.setInt(4, doctorId);

            int rowsAffected = insertAppointmentStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public int fetchAnimalId(String name, String breed, String ownerName, String ownerSurname, String ownerPatronymic) throws SQLException {
        String query = "SELECT a.animal_id " +
                "FROM animals a " +
                "JOIN breeds b ON a.breed_id = b.breed_id " +
                "JOIN owners o ON a.owner_id = o.owner_id " +
                "WHERE a.name = ? AND b.name = ? AND o.name = ? AND o.surname = ? AND o.patronymic = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, breed);
            statement.setString(3, ownerName);
            statement.setString(4, ownerSurname);
            statement.setString(5, ownerPatronymic);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("animal_id");
            }
        }
        return -1;
    }

    public int createAnimal(String name, String breed, String ownerName, String ownerSurname, String ownerPatronymic) throws SQLException {
        // First, check if the breed exists in the database and get its ID
        int breedId = getBreedId(breed);
        if (breedId == -1) {
            throw new SQLException("Не удалось найти породу животного в базе данных.");
        }

        // Then, insert the animal record
        String insertAnimalQuery = "INSERT INTO animals (name, breed_id, owner_id) VALUES (?, ?, ?)";
        try (PreparedStatement insertAnimalStatement = connection.prepareStatement(insertAnimalQuery, Statement.RETURN_GENERATED_KEYS)) {
            insertAnimalStatement.setString(1, name);
            insertAnimalStatement.setInt(2, breedId);
            insertAnimalStatement.setInt(3, getOwnerId(ownerName, ownerSurname, ownerPatronymic)); // getOwnerId method defined below

            int rowsAffected = insertAnimalStatement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Не удалось создать запись о животном.");
            }

            ResultSet generatedKeys = insertAnimalStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Return the newly generated animal_id
            } else {
                throw new SQLException("Не удалось получить animal_id для новой записи.");
            }
        }
    }

    // Create a new appointment
    public boolean createAppointment(String ownerName, String ownerSurname, String ownerPatronymic,
                                     String animalName, String breed, LocalDate date, LocalTime time) throws SQLException {
        int animalId = fetchAnimalId(animalName, breed, ownerName, ownerSurname, ownerPatronymic);
        if (animalId == -1) {
            throw new SQLException("Животное не найдено в базе данных.");
        }

        // Get current doctor (for simplicity, assuming you have a method to get current doctor details)
        int doctorId = getCurrentDoctorId();

        // Optionally, fetch diagnosis_id based on some criteria, or set it to NULL
        Integer diagnosisId = null; // Assuming not always required

        // Insert appointment record
        String insertAppointmentQuery = "INSERT INTO appointments (date, time, animal_id, doctor_id, diagnosis_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement insertAppointmentStatement = connection.prepareStatement(insertAppointmentQuery)) {
            insertAppointmentStatement.setDate(1, Date.valueOf(date));
            insertAppointmentStatement.setTime(2, Time.valueOf(time));
            insertAppointmentStatement.setInt(3, animalId);
            insertAppointmentStatement.setInt(4, doctorId);
            if (diagnosisId != null) {
                insertAppointmentStatement.setInt(5, diagnosisId);
            } else {
                insertAppointmentStatement.setNull(5, Types.INTEGER);
            }

            int rowsAffected = insertAppointmentStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    // Helper method to get breed_id based on breed name
    private int getBreedId(String breedName) throws SQLException {
        String query = "SELECT breed_id FROM breeds WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, breedName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("breed_id");
            }
        }
        return -1; // Breed not found
    }

    // Helper method to get owner_id based on owner details
    private int getOwnerId(String ownerName, String ownerSurname, String ownerPatronymic) throws SQLException {
        String query = "SELECT owner_id FROM owners WHERE name = ? AND surname = ? AND patronymic = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ownerName);
            statement.setString(2, ownerSurname);
            statement.setString(3, ownerPatronymic);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("owner_id");
            }
        }
        return -1; // Owner not found
    }

    // Dummy method to get current doctor id (replace with actual implementation as per your application)
    private int getCurrentDoctorId() {
        // Example: return currentDoctor.getDoctorId();
        return 1; // Dummy return value
    }
}
