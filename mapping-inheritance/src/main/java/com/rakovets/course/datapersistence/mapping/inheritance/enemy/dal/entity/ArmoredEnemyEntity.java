package com.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
//@Table(name = "armored_enemies")
@ToString(callSuper = true)
//@PrimaryKeyJoinColumn(name = "enemy_id")
@DiscriminatorValue("armored")
public class ArmoredEnemyEntity extends EnemyEntity {
    @Column(name = "armor_type")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ArmorType armorType;

    @Column(name = "armor_durability")
    @Getter
    @Setter
    private Integer armorDurability;
}
