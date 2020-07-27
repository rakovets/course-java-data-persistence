package com.rakovets.course.datapersistence.solution.hibernate.dal.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "painting")
public class Painting {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "authorName")
    private String nameAuthor;

    public Painting() {
        super();
    }

    public Painting(String name, String nameAuthor) {
        this.name = name;
        this.nameAuthor = nameAuthor;
    }

    public Painting(int id, String name, String nameAuthor) {
        this.id = id;
        this.name = name;
        this.nameAuthor = nameAuthor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nameAuthor='" + nameAuthor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Painting painting = (Painting) o;
        return Objects.equals(name, painting.name) &&
                Objects.equals(nameAuthor, painting.nameAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nameAuthor);
    }
}
