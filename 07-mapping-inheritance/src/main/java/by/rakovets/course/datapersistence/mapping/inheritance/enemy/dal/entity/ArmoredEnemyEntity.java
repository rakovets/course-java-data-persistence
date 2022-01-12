package by.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


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
