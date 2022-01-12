package by.rakovets.course.datapersistence.example.mapping.basic.dal.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "hero")
public class HeroEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public EquipmentSet getFirstEquipmentSet() {
        return firstEquipmentSet;
    }

    public void setFirstEquipmentSet(EquipmentSet firstEquipmentSet) {
        this.firstEquipmentSet = firstEquipmentSet;
    }

    public EquipmentSet getSecondEquipmentSet() {
        return secondEquipmentSet;
    }

    public void setSecondEquipmentSet(EquipmentSet secondEquipmentSet) {
        this.secondEquipmentSet = secondEquipmentSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroEntity heroEntity = (HeroEntity) o;
        return Objects.equals(name, heroEntity.name) &&
                gender == heroEntity.gender &&
                Objects.equals(creationTime, heroEntity.creationTime) &&
                Objects.equals(firstEquipmentSet, heroEntity.firstEquipmentSet) &&
                Objects.equals(secondEquipmentSet, heroEntity.secondEquipmentSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, creationTime, firstEquipmentSet, secondEquipmentSet);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", creationTime=" + creationTime +
                ", firstEquipmentSet=" + firstEquipmentSet +
                ", secondEquipmentSet=" + secondEquipmentSet +
                '}';
    }
}
