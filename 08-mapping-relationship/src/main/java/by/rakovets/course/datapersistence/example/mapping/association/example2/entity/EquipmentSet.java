package by.rakovets.course.datapersistence.example.mapping.association.example2.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


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
