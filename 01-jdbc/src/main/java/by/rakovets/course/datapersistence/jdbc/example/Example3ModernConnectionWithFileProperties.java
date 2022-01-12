package by.rakovets.course.datapersistence.jdbc.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Example3ModernConnectionWithFileProperties {
    public static final String DATABASE_PROPERTIES_PATH = "database.properties";

    public static void main(String[] args) {
        try {
            try (Connection connection = getConnection()) {
                System.out.println("Connection successful!");
            }
            try (Connection connection = getConnectionAsProperties()) {
                System.out.println("Connection successful!");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed!");
            System.out.println(ex);
        }
    }

    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(DATABASE_PROPERTIES_PATH)) {
            props.load(in);
        }
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");
        return DriverManager.getConnection(url, user, password);
    }

    public static Connection getConnectionAsProperties() throws SQLException, IOException {
        Properties props = new Properties();
        try (InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream(DATABASE_PROPERTIES_PATH)) {
            props.load(in);
        }
        return DriverManager.getConnection(props.getProperty("url"), props);
    }
}
