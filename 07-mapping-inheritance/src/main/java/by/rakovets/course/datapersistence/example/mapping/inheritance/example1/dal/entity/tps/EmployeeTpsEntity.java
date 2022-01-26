package by.rakovets.course.datapersistence.example.mapping.inheritance.example1.dal.entity.tps;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;


@Entity
@Table(name = "tps_employees")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class EmployeeTpsEntity extends BaseEntity {
    @Column(name = "name")
    @Getter
    @Setter
    private String name;
}
