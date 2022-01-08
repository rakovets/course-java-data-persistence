package com.rakovets.course.datapersistence.dao.practice.dao;

import com.rakovets.course.datapersistence.dao.practice.connection.ConnectionManager;
import com.rakovets.course.datapersistence.dao.practice.entity.Dish;
import com.rakovets.course.datapersistence.dao.practice.entity.Restaurant;
import com.rakovets.course.datapersistence.dao.practice.entity.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class RestaurantDao {
    private static final Object LOCK = new Object();
    private static RestaurantDao INSTANCE = null;

    public static RestaurantDao getInstance() {
        if (INSTANCE == null) {
            synchronized (LOCK) {
                if (INSTANCE == null) {
                    INSTANCE = new RestaurantDao();
                }
            }
        }
        return INSTANCE;
    }

    public Optional<Restaurant> save(Restaurant restaurant) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO restaurant (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, restaurant.getName());
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    restaurant.setId(generatedKeys.getLong(1));
                    return Optional.of(restaurant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Restaurant> getById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM restaurant WHERE restaurant_id = ?")) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return Optional.of(new Restaurant(id, resultSet.getString("name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Restaurant> addDish(Restaurant restaurant, Dish dish) {
        try (Connection connection = ConnectionManager.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO dish (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, dish.getName());
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    dish.setId(generatedKeys.getLong(1));
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO restaurant_dish_link (restaurant_id, dish_id) VALUES (?, ?)")) {
                preparedStatement.setLong(1, restaurant.getId());
                preparedStatement.setLong(2, dish.getId());
                preparedStatement.executeUpdate();
            }
            connection.commit();
            restaurant.addDish(dish);
            return Optional.of(restaurant);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Restaurant> getFullInfo(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT res.restaurant_id, res.name AS res_name, d.dish_id, d.name AS dish_name, "
                            + "rev.review_id , rev.content FROM restaurant AS res "
                            + "LEFT JOIN restaurant_dish_link AS rd ON res.restaurant_id = rd.restaurant_id "
                            + "LEFT JOIN dish AS d ON d.dish_id = rd.dish_id "
                            + "LEFT JOIN review AS rev ON res.restaurant_id = rev.restaurant_id "
                            + " WHERE res.restaurant_id = ?")) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                Restaurant restaurant = new Restaurant(id);
                while (resultSet.next()) {
                    restaurant.setName(resultSet.getString("res_name"));
                    Dish dish = new Dish(resultSet.getLong("dish_id"), resultSet.getString("dish_name"));
                    restaurant.addDish(dish);
                    Review review = new Review(resultSet.getLong("review_id"), resultSet.getString("content"), restaurant);
                    restaurant.addReview(review);
                }
                return Optional.of(restaurant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
