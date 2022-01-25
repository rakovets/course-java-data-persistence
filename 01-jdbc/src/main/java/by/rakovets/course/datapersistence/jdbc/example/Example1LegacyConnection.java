package by.rakovets.course.datapersistence.jdbc.example;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Properties;

public class Example1LegacyConnection {
    private static final String DATASOURCE_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DATASOURCE_URL = "jdbc:mariadb://localhost:3306/01_jdbc";
    private static final String DATASOURCE_USER = "mariadb";
    private static final String DATASOURCE_PASSWORD = "1234567";

    public static void main(String[] args) {
        Driver driver = loadDriver();
        Properties properties = prepareProperties();
        try (Connection connection = driver.connect(DATASOURCE_URL, properties)) {
            System.out.println("Connection successful!");
        } catch (Exception e) {
            System.out.println("Connection failed!");
        }
    }

    private static Properties prepareProperties() {
        Properties properties = new Properties();
        properties.put("user", DATASOURCE_USER);
        properties.put("password", DATASOURCE_PASSWORD);
        return properties;
    }

    private static Driver loadDriver() {
        Driver driver;
        try {
            driver = (Driver) Class.forName(DATASOURCE_DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException
                | ClassNotFoundException e) {
            e.printStackTrace();
            String error = "Problem with JDBC Driver: " + DATASOURCE_DRIVER;
            System.out.println(error);
            throw new RuntimeException(error);
        }
        return driver;
    }
}
