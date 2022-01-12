package by.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tps;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "tps_managers")
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "employee_id")
public class ManagerTpsEntity extends EmployeeTpsEntity {
    @Getter
    @Setter
    @Column(name = "number_of_idle_hours", nullable = false)
    private Integer numberOfIdleHours;
}
