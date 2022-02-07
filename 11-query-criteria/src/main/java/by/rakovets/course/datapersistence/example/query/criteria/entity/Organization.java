package by.rakovets.course.datapersistence.example.query.criteria.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
