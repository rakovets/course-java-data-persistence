package by.rakovets.course.datapersistence.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;

public class Example2ModernConnection {
    private static final String DATASOURCE_URL = "jdbc:mariadb://localhost:3306/01_jdbc";
    private static final String DATASOURCE_USER = "mariadb";
    private static final String DATASOURCE_PASSWORD = "1234567";

    public static void main(String[] args) {
        try (Connection connection =
                     DriverManager.getConnection(DATASOURCE_URL, DATASOURCE_USER, DATASOURCE_PASSWORD)) {
            System.out.println("Connection successful!");
        } catch (Exception e) {
            System.out.println("Connection failed!");
        }
    }
}
