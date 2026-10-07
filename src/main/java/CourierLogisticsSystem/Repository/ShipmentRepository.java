package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
    Optional<Shipment> findByTrackingNo(Long trackingNo);
    boolean  existsByTrackingNo(Long trackingNo);

}
