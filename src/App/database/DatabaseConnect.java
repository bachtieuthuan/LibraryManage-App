package App.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/quanlythuvien";
    private static final String USER = "admin";
    private static final String PASSWORD = "Itxhnv@!2023";

    public DatabaseConnect() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlythuvien", "admin", "Itxhnv@!2023");
        } catch (SQLException var1) {
            SQLException e = var1;
            e.printStackTrace();
            return null;
        }
    }
}