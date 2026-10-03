package CourierLogisticsSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Long phone;
    @Column(unique = true, nullable = false)
    private String vehicleNo;
    private Boolean availabilityStatus = true;
    private Double rating;

    @OneToMany(mappedBy = "deliveryAgent", cascade={CascadeType.ALL})
    @JsonIgnore
    private List<Shipment> shipments;
}
