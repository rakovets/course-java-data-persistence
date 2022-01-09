package com.rakovets.course.datapersistence.mapping.association.hero.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
@AllArgsConstructor
@ToString
public class EquipmentSet {
    @Getter
    @Setter
    @Column(name = "melee_weapon")
    private String meleeWeapon;

    @Getter
    @Setter
    @Column(name = "ranged_weapon")
    private String rangedWeapon;
}
