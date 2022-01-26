package by.rakovets.course.datapersistence.solution.dao;

import by.rakovets.course.datapersistence.solution.dao.dao.RestaurantDao;
import by.rakovets.course.datapersistence.solution.dao.dao.ReviewDao;
import by.rakovets.course.datapersistence.solution.dao.entity.Dish;
import by.rakovets.course.datapersistence.solution.dao.entity.Restaurant;
import by.rakovets.course.datapersistence.solution.dao.entity.Review;

import java.util.Optional;

public class RestaurantApplication {
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
