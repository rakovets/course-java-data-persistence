package by.rakovets.course.datapersistence.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Example4Jdbc {
    private static final String DATASOURCE_URL = "jdbc:mariadb://localhost:3306/jdbc";
    private static final String DATASOURCE_USER = "mariadb";
    private static final String DATASOURCE_PASSWORD = "1234567";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DATASOURCE_URL, DATASOURCE_USER, DATASOURCE_PASSWORD)) {
            // PrepareStatement
            // Create new book
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO book(author, title, genre) VALUES (?, ?, ?)");
            preparedStatement.setString(1, "Robert Martin");
            preparedStatement.setString(2, "Clean Architecture");
            preparedStatement.setString(3, "Development");
            int count = preparedStatement.executeUpdate();
            System.out.printf("SQL Query apply for %d items\n", count);

            Statement insertStatement = connection.createStatement();

            String sqlInsert =
                    String.format("INSERT INTO book(author, title, genre) VALUES (\"%s\", \"%s\", \"%s\")",
                    "Robert Martin", "Clean Architecture", "Development");

            int insertCount = insertStatement.executeUpdate(sqlInsert);
            System.out.printf("SQL Query apply for %d items\n", insertCount);

            // Statement
            Statement statement = connection.createStatement();

            // Read book by id
            int id = 1;
            ResultSet resultSetById = statement.executeQuery("SELECT * FROM book WHERE book_id = " + id);
            while (resultSetById.next()) {
                Book book = mapToBook(resultSetById);
                System.out.println(book);
            }

            // Read all books
            ResultSet resultSetAll = statement.executeQuery("SELECT * FROM book");
            while (resultSetAll.next()) {
                printBook(resultSetAll);
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }

    private static Book mapToBook(ResultSet resultSetById) throws SQLException{
        Book book = new Book();

        book.setId(resultSetById.getInt("book_id"));
        book.setAuthor(resultSetById.getString("author"));
        book.setTitle(resultSetById.getString("title"));
        book.setGenre(resultSetById.getString("genre"));
        return book;
    }

    private static void printBook(ResultSet resultSet) throws SQLException {
        System.out.printf("{\n\t\"book_id\": %d,\n\t\"author\": \"%s\",\n\t\"title\": %s,\n\t\"genre\": \"%s\"\n}\n",
                resultSet.getInt("book_id"),
                resultSet.getString("author"),
                resultSet.getString("title"),
                resultSet.getString("genre"));
    }
}
