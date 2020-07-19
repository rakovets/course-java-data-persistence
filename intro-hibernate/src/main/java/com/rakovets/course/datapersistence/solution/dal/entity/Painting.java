package com.rakovets.course.datapersistence.solution.dal.entity;

import javax.persistence.*;

@Entity
@Table(name = "painting")
public class Painting {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "paintingName")
    String name;

    @Column(name = "authorName")
    String authorName;

    public Painting() {
        super();
    }

    public Painting(String name, String authorName) {
        this.name = name;
        this.authorName = authorName;
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

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
