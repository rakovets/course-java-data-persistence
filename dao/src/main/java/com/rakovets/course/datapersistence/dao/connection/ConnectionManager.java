package com.rakovets.course.datapersistence.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String DATASOURCE_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String DATASOURCE_URL = "jdbc:mariadb://localhost:3306/music_store";
    private static final String DATASOURCE_USER = "root";
    private static final String DATASOURCE_PASSWORD = "1234";

    static {
        try {
            Class.forName(DATASOURCE_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Didn't found JDBC Driver: " + DATASOURCE_DRIVER);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATASOURCE_URL, DATASOURCE_USER, DATASOURCE_PASSWORD);
    }
}
