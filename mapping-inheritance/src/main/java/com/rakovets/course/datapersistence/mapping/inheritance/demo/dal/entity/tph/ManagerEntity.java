package com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tph;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "managers")
@ToString(callSuper = true)
@DiscriminatorValue("manager")
public class ManagerEntity extends EmployeeEntity {
    @Getter
    @Setter
    @Column(name = "number_of_idle_hours", nullable = false)
    private Integer numberOfIdleHours;
}
