package com.rakovets.course.datapersistence.query.hql.dal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "organizations")
@ToString(callSuper = true, exclude = "employees")
@NoArgsConstructor
public class Organization extends IdentifiableEntity {
    public Organization(String name) {
        this.name = name;
    }

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @OneToMany(mappedBy = "organization")
    @Getter
    @Setter
    private Set<Employee> employees = new HashSet<>();
}
