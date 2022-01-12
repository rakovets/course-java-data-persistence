package by.rakovets.course.datapersistence.example.mapping.basic.dal.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;


@Embeddable
public class EquipmentSet {
    @Column(name = "melee_weapon")
    private String meleeWeapon;

    @Column(name = "ranged_weapon")
    private String rangedWeapon;

    public EquipmentSet() {
    }

    public EquipmentSet(String meleeWeapon, String rangedWeapon) {
        this.meleeWeapon = meleeWeapon;
        this.rangedWeapon = rangedWeapon;
    }

    public String getMeleeWeapon() {
        return meleeWeapon;
    }

    public void setMeleeWeapon(String meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public String getRangedWeapon() {
        return rangedWeapon;
    }

    public void setRangedWeapon(String rangedWeapon) {
        this.rangedWeapon = rangedWeapon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentSet that = (EquipmentSet) o;
        return Objects.equals(meleeWeapon, that.meleeWeapon) &&
                Objects.equals(rangedWeapon, that.rangedWeapon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meleeWeapon, rangedWeapon);
    }

    @Override
    public String toString() {
        return "EquipmentSet{" +
                "meleeWeapon='" + meleeWeapon + '\'' +
                ", rangedWeapon='" + rangedWeapon + '\'' +
                '}';
    }
}
