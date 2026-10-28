package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
    Optional<Shipment> findByTrackingNo(Long trackingNo);
    boolean  existsByTrackingNo(Long trackingNo);
    List<Shipment> findByCustomerId(Integer customerId);
    List<Shipment> findByWarehouseId(Integer warehouseId);
    List<Shipment> findByDeliveryAgentId(Integer agentId);
}
