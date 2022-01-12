package by.rakovets.course.datapersistence.dao.practice.entity;

public class Review {
    private long id;
    private String text;
    private Restaurant restaurant;

    public Review(String text, Restaurant restaurant) {
        this.text = text;
        this.restaurant = restaurant;
    }

    public Review(long id, String text, Restaurant restaurant) {
        this.id = id;
        this.text = text;
        this.restaurant = restaurant;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (id != review.id) return false;
        return text.equals(review.text);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
