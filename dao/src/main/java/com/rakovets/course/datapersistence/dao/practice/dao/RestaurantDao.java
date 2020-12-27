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
                    "INSERT INTO restaurants (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
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
                    "SELECT * FROM restaurants WHERE id = ?")) {
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
                    "INSERT INTO dishes (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, dish.getName());
                preparedStatement.executeUpdate();
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    dish.setId(generatedKeys.getLong(1));
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO restaurants_dishes (restaurant_id, dish_id) VALUES (?, ?)")) {
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
                    "SELECT res.id, res.name AS res_name, d.id AS dish_id, d.name AS dish_name, "
                            + "rev.id AS rev_id, rev.text FROM restaurants AS res "
                            + "LEFT JOIN restaurants_dishes AS rd ON res.id = rd.restaurant_id "
                            + "LEFT JOIN dishes AS d ON d.id = rd.dish_id "
                            + "LEFT JOIN reviews AS rev ON res.id = rev.restaurant_id "
                            + " WHERE res.id = ?")) {
                preparedStatement.setLong(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                Restaurant restaurant = new Restaurant(id);
                while (resultSet.next()) {
                    restaurant.setName(resultSet.getString("res_name"));
                    Dish dish = new Dish(resultSet.getLong("dish_id"), resultSet.getString("dish_name"));
                    restaurant.addDish(dish);
                    Review review = new Review(resultSet.getLong("rev_id"), resultSet.getString("text"), restaurant);
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
