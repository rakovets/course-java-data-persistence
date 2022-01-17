package by.rakovets.course.datapersistence.example.mapping.basic.dal.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@Table(name = "hero")
public class HeroEntity {
    @Id
    @Column(name = "hero_id")
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "attack_duration")
    private String attackDuration;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    private ZonedDateTime creationTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "meleeWeapon", column = @Column(name = "first_melee_weapon")),
            @AttributeOverride(name = "rangedWeapon", column = @Column(name = "first_ranged_weapon"))
    })
    private EquipmentSet firstEquipmentSet;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "meleeWeapon", column = @Column(name = "second_melee_weapon")),
            @AttributeOverride(name = "rangedWeapon", column = @Column(name = "second_ranged_weapon"))
    })
    private EquipmentSet secondEquipmentSet;
}
