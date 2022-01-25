package by.rakovets.course.datapersistence.dao.practice.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String DATASOURCE_URL = "jdbc:mariadb://localhost:3306/02_dao";
    private static final String DATASOURCE_USER = "mariadb";
    private static final String DATASOURCE_PASSWORD = "1234567";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATASOURCE_URL, DATASOURCE_USER, DATASOURCE_PASSWORD);
    }
}
