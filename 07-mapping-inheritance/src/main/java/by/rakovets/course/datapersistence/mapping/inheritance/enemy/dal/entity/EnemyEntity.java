package by.rakovets.course.datapersistence.mapping.inheritance.enemy.dal.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;


@Entity
@Table(name = "enemies")
@ToString(callSuper = true)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "enemy_type")
public abstract class EnemyEntity extends BaseEntity {
    @Column(name = "name")
    @Getter
    @Setter
    private String name;
}
