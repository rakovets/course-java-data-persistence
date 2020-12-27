package com.rakovets.course.datapersistence.mapping.association.hero.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "heroes")
@NoArgsConstructor
@ToString
public class Hero {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Getter
    @Setter
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @Getter
    @Setter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "meleeWeapon", column = @Column(name = "first_melee_weapon")),
            @AttributeOverride(name = "rangedWeapon", column = @Column(name = "first_ranged_weapon"))
    })
    private EquipmentSet firstEquipmentSet;

    @Getter
    @Setter
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "meleeWeapon", column = @Column(name = "second_melee_weapon")),
            @AttributeOverride(name = "rangedWeapon", column = @Column(name = "second_ranged_weapon"))
    })
    private EquipmentSet secondEquipmentSet;

    @OneToOne(mappedBy = "hero")
    @Getter
    @Setter
    private Inventory inventory;

    @ManyToMany(mappedBy = "heroes")
    @Getter
    @Setter
    private Set<Quest> quests = new HashSet<>();
}
