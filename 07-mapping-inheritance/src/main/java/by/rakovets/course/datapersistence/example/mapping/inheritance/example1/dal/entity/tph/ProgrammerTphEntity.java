package by.rakovets.course.datapersistence.example.mapping.inheritance.example1.dal.entity.tph;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity
@ToString(callSuper = true)
@DiscriminatorValue("programmer")
public class ProgrammerTphEntity extends EmployeeTphEntity {
    @Getter
    @Setter
    @Column(name = "programming_language")
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;
}
