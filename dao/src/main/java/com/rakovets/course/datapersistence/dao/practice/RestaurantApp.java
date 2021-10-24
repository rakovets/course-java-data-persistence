package com.rakovets.course.datapersistence.dao.practice;

import com.rakovets.course.datapersistence.dao.practice.dao.RestaurantDao;
import com.rakovets.course.datapersistence.dao.practice.dao.ReviewDao;
import com.rakovets.course.datapersistence.dao.practice.entity.Dish;
import com.rakovets.course.datapersistence.dao.practice.entity.Restaurant;
import com.rakovets.course.datapersistence.dao.practice.entity.Review;

import java.util.Optional;

public class RestaurantApp {
    public static void main(String[] args) {
        Optional<Restaurant> restaurantOptional
                = RestaurantDao.getInstance().save(new Restaurant("Vasilki"));
        if (restaurantOptional.isPresent()) {
            System.out.println(restaurantOptional.get());
        }

        Optional<Restaurant> restaurant = RestaurantDao.getInstance().getById(1L);
        if (restaurant.isPresent()) {
            Optional<Restaurant> restaurantWithDish
                    = RestaurantDao.getInstance().addDish(restaurant.get(), new Dish("Мексиканская"));
            if (restaurantWithDish.isPresent()) {
                System.out.println(restaurantWithDish.get());
            }
        }

        restaurant = RestaurantDao.getInstance().getById(1L);
        if (restaurant.isPresent()) {
            ReviewDao.getInstance().save(new Review("Ничё так", restaurant.get()));
        }

        Optional<Restaurant> fullInfo = RestaurantDao.getInstance().getFullInfo(1L);
        System.out.println(fullInfo.get().getDishes().size());
    }
}
