package com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tpc;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name = "programmers")
@ToString(callSuper = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
        @AttributeOverride(name = "name", column = @Column(name = "name"))
})
public class ProgrammerEntity extends EmployeeEntity {
    @Getter
    @Setter
    @Column(name = "programming_language")
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;
}
