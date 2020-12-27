package com.rakovets.course.datapersistence.dao.example.dao;

import com.rakovets.course.datapersistence.dao.example.connection.ConnectionManager;
import com.rakovets.course.datapersistence.dao.example.entity.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ArtistDao {
    private static final Object LOCK = new Object();
    private static ArtistDao INSTANCE = null;

    public static ArtistDao getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new ArtistDao();
                }
            }
        }
        return INSTANCE;
    }

    public Artist save(Artist artist) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO artists (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, artist.getName());
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    artist.setId(generatedKeys.getLong(1));
                    return artist;
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        return null;
    }

    public Artist findById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM artists WHERE id = ?")) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new Artist(id, resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
        }
        return null;
    }
}
