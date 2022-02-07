package by.rakovets.course.datapersistence.example.cache.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "employees")
@ToString(callSuper = true, exclude = {"payments", "organization"})
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    @ManyToOne(fetch = FetchType.LAZY)
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
