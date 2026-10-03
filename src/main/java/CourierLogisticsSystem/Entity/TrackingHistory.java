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
public class TrackingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String location;
    private String remarks;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipmentStatus shipmentStatus;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)  //LAZY: Hibernate loads only the Shipment.
    @JoinColumn(name = "shipment_id")
    @JsonIgnore
    private Shipment shipment;
}
