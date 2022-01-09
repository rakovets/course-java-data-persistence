package com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tph;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@ToString(callSuper = true)
@DiscriminatorValue("manager")
public class ManagerTphEntity extends EmployeeTphEntity {
    @Getter
    @Setter
    @Column(name = "number_of_idle_hours")
    private Integer numberOfIdleHours;
}
