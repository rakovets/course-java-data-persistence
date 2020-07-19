package com.rakovets.course.datapersistence.mapping.association.hero.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "inventories")
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(name = "max_size")
    @Getter
    @Setter
    private int maxSize;

    @OneToMany(mappedBy = "inventory")
    @Getter
    @Setter
    private Set<Item> items = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "hero_id")
    @Getter
    @Setter
    private Hero hero;
}
