package CourierLogisticsSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDateTime;

    @OneToOne
    @JoinColumn(name ="shipment_id")  // Payment owns the foreign key (shipment_id), which matches the mappedBy = "shipment" we wrote in Shipment.
    @JsonIgnore    //@JsonIgnore avoids the infinite JSON loop for now.
    private Shipment shipment;
}
