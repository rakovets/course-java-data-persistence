package com.rakovets.course.datapersistence.solution.jpa.dal.entity;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "message")
    private String message;

    public Message() {
        super();
    }

    public Message(String message) {
        super();
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
