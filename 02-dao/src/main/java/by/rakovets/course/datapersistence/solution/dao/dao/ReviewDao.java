package by.rakovets.course.datapersistence.solution.dao.dao;

import by.rakovets.course.datapersistence.solution.dao.connection.ConnectionManager;
import by.rakovets.course.datapersistence.solution.dao.entity.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewDao {
    private static final Object LOCK = new Object();
    private static ReviewDao INSTANCE = null;

    public static ReviewDao getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new ReviewDao();
                }
            }
        }
        return INSTANCE;
    }

    public void save(Review review) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO review (content, restaurant_id) VALUES (?, ?);")) {
                preparedStatement.setString(1, review.getText());
                preparedStatement.setLong(2, review.getRestaurant().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
