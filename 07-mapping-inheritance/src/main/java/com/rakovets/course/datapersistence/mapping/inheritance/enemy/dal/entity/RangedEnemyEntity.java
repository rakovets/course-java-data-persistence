package com.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


@Entity
//@Table(name = "ranged_enemies")
@ToString(callSuper = true)
//@PrimaryKeyJoinColumn(name = "enemy_id")
@DiscriminatorValue("ranged")
public class RangedEnemyEntity extends EnemyEntity {
    @Column(name = "weapon_type")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private WeaponType weaponType;

    @Column(name = "range_of_fire")
    @Getter
    @Setter
    private Integer rangeOfFire;
}
