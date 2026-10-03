package CourierLogisticsSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)  // nullable = false means it can't null in database.
    private PackageType packageType;

    private Boolean fragile;
    private String dimension;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id")
    @JsonIgnore
    private Shipment shipment;
}
