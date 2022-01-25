package by.rakovets.course.datapersistence.jdbc.example;

import java.sql.*;

public class Example6SqlInjection {
    private static final String DATASOURCE_URL = "jdbc:mariadb://localhost:3306/01_jdbc";
    private static final String DATASOURCE_USER = "mariadb";
    private static final String DATASOURCE_PASSWORD = "1234567";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DATASOURCE_URL, DATASOURCE_USER, DATASOURCE_PASSWORD)) {
            // Statement
            Statement statement = connection.createStatement();

            // SQL injection
            String clientInputTitle = "t' OR true OR title = 't";

            // Main code
            String sqlToDatabase =
                    "SELECT * FROM book WHERE title = '" + clientInputTitle + "'";

            ResultSet resultSetBySqlInjection =
                    statement.executeQuery(sqlToDatabase);
            while (resultSetBySqlInjection.next()) {
                System.out.println(mapToBook(resultSetBySqlInjection));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
    }

    private static Book mapToBook(ResultSet resultSetById) throws SQLException {
        Book book = new Book();

        book.setId(resultSetById.getInt("book_id"));
        book.setAuthor(resultSetById.getString("author"));
        book.setTitle(resultSetById.getString("title"));
        book.setGenre(resultSetById.getString("genre"));
        return book;
    }
}
