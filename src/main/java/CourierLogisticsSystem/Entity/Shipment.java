package CourierLogisticsSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long trackingNo;
    private String source;
    private String destination;
    private Double weight;
    private LocalDateTime shipmentDateTime;
    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_agent_id")
    private DeliveryAgent deliveryAgent;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<Package> packages;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<TrackingHistory> trackingHistory;

    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
    private Payment payment;
}
