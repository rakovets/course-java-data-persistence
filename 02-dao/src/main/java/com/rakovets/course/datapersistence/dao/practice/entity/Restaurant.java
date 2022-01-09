package com.rakovets.course.datapersistence.dao.practice.entity;

import java.util.HashSet;
import java.util.Set;

public class Restaurant {
    private long id;
    private String name;
    private Set<Dish> dishes = new HashSet<>();
    private Set<Review> reviews = new HashSet<>();

    public Restaurant(long id) {
        this.id = id;
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
