package com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tph;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@ToString(callSuper = true)
@DiscriminatorValue("manager")
public class ManagerTphEntity extends EmployeeTphEntity {
    @Getter
    @Setter
    @Column(name = "number_of_idle_hours")
    private Integer numberOfIdleHours;
}
