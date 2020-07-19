package com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tps;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "managers")
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "employee_id")
public class ManagerEntity extends EmployeeEntity {
    @Getter
    @Setter
    @Column(name = "number_of_idle_hours", nullable = false)
    private Integer numberOfIdleHours;
}
