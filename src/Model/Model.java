package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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


}
