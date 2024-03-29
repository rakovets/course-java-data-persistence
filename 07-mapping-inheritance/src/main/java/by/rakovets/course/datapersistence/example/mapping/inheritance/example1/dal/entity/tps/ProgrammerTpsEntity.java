package by.rakovets.course.datapersistence.example.mapping.inheritance.example1.dal.entity.tps;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name = "tps_programmers")
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "employee_id")
public class ProgrammerTpsEntity extends EmployeeTpsEntity {
    @Getter
    @Setter
    @Column(name = "programming_language")
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;
}
