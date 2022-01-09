package com.rakovets.course.datapersistence.query.hql.dal.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "employees")
@ToString(callSuper = true, exclude = "payments")
@NoArgsConstructor
public class Employee extends IdentifiableEntity {
    public Employee(String firstName, String lastName, LocalDate birthday, Gender gender, Organization organization) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.organization = organization;
    }

    @Column(name = "first_name")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "date_of_birth")
    @Getter
    @Setter
    private LocalDate birthday;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    @Getter
    @Setter
    private Organization organization;

    @OneToMany(mappedBy = "receiver")
    @Getter
    @Setter
    private Set<Payment> payments = new HashSet<>();

    @Transient
    public String fullName() {
        return firstName + " " + lastName;
    }
}
