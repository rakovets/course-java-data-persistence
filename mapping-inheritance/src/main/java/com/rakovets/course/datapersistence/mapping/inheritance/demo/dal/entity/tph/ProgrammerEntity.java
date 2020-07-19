package com.rakovets.course.datapersistence.mapping.inheritance.demo.dal.entity.tph;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "programmers")
@ToString(callSuper = true)
@DiscriminatorValue("programmer")
public class ProgrammerEntity extends EmployeeEntity {
    @Getter
    @Setter
    @Column(name = "programming_language")
    @Enumerated(EnumType.STRING)
    private ProgrammingLanguage programmingLanguage;
}
