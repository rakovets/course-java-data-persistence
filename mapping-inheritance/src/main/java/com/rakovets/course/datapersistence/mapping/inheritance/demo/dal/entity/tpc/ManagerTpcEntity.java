package com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tpc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "tpc_managers")
@ToString(callSuper = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
        @AttributeOverride(name = "name", column = @Column(name = "name"))
})
public class ManagerTpcEntity extends EmployeeTpcEntity {
    @Getter
    @Setter
    @Column(name = "number_of_idle_hours", nullable = false)
    private Integer numberOfIdleHours;
}
