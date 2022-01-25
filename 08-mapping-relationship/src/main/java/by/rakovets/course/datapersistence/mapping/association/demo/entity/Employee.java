package by.rakovets.course.datapersistence.mapping.association.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "employees")
@NoArgsConstructor
@ToString(exclude = {"address"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    @Getter
    @Setter
    @OneToOne(mappedBy = "employee")
    private Address address;

    @ManyToMany(mappedBy = "employees")
    @Getter
    @Setter
    private Set<Meeting> meetings = new HashSet<>();
}
