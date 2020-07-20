package com.rakovets.course.datapersistence.solution.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "message")

public class Messages {
    private Integer id;
    private String text;
}
