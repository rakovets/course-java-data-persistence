package by.rakovets.course.datapersistence.example.cache.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;


@Entity
@Table(name = "payments")
@ToString(callSuper = true)
@NoArgsConstructor
public class Payment extends IdentifiableEntity {
    public Payment(BigDecimal amount, Employee receiver) {
        this.amount = amount;
        this.receiver = receiver;
    }

    @Column(name = "amount")
    @Getter
    @Setter
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    @Getter
    @Setter
    private Employee receiver;
}
