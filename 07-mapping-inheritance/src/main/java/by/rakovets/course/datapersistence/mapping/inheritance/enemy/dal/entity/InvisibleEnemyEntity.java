package by.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
//@Table(name = "invisible_enemies")
@ToString(callSuper = true)
//@PrimaryKeyJoinColumn(name = "enemy_id")
@DiscriminatorValue("invisible")
public class InvisibleEnemyEntity extends EnemyEntity {
    @Column(name = "invisibility_duration")
    @Getter
    @Setter
    private Integer invisibilityDuration;
}
