package by.rakovets.course.datapersistence.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Example5Transaction {
    private static final String DATASOURCE_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DATASOURCE_URL = "jdbc:mariadb://localhost:3306/library";
    private static final String DATASOURCE_USER = "root";
    private static final String DATASOURCE_PASSWORD = "1234";

    public static void main(String[] args) {
        try {
            Class.forName(DATASOURCE_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Didn't found JDBC Driver: " + DATASOURCE_DRIVER);
        }
        addBooks("Agile", "Scrum");
    }

    private static void addBooks(String... books) {
        try (Connection connection =
                     DriverManager.getConnection(DATASOURCE_URL, DATASOURCE_USER, DATASOURCE_PASSWORD)) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement("INSERT INTO book(title) VALUES (?)")) {
                for (String book : books) {
                    preparedStatement.setString(1, book);
                    preparedStatement.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }
}
