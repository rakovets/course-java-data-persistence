package com.rakovets.course.datapersistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcExample {
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
        try (Connection connection = DriverManager.getConnection(DATASOURCE_URL, DATASOURCE_USER, DATASOURCE_PASSWORD)) {
            // Statement
            Statement statement = connection.createStatement();

            // Read all books
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            while (resultSet.next()) {
                printBook(resultSet);
            }

            // Read book by id
            int id = 1;
            resultSet = statement.executeQuery("SELECT * FROM books WHERE id = " + id);
            while (resultSet.next()) {
                printBook(resultSet);
            }

            // PrepareStatement
            // Create new book
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO books (title, number_of_pages, genre) VALUES (?, ?, ?)");
            preparedStatement.setString(1, "Clean Architecture");
            preparedStatement.setInt(2, 450);
            preparedStatement.setString(3, "Development");
            int count = preparedStatement.executeUpdate();
            System.out.printf("SQL Query apply for %d items", count);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }

    private static void printBook(ResultSet resultSet) throws SQLException {
        System.out.printf("{\n\t\"id\": %d,\n\t\"title\": \"%s\",\n\t\"number_of_pages\": %d,\n\t\"genre\": \"%s\"\n}\n",
                resultSet.getInt("id"), resultSet.getString("title"),
                resultSet.getInt("number_of_pages"), resultSet.getString("genre"));
    }
}
