package by.rakovets.course.datapersistence.mapping.association.hero.entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    @Getter
    @Setter
    private Inventory inventory;
}
