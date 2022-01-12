package by.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tpc;

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
@Table(name = "tpc_employees")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class EmployeeTpcEntity extends BaseEntity {
    @Column(name = "name")
    @Getter
    @Setter
    private String name;
}
