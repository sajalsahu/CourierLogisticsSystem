package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.Shipment;
import CourierLogisticsSystem.Entity.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShipmentRepository extends JpaRepository<Shipment, Integer> {
    Optional<Shipment> findByTrackingNo(Long trackingNo);
    boolean  existsByTrackingNo(Long trackingNo);
    List<Shipment> findByCustomerId(Integer customerId);
    List<Shipment> findByWarehouseId(Integer warehouseId);
    List<Shipment> findByDeliveryAgentId(Integer agentId);

    boolean existsByCustomerIdAndStatusNotIn(Integer customerId, List<ShipmentStatus> statuses);
    boolean existsByWarehouseId(Integer warehouseId);
    boolean existsByDeliveryAgentId(Integer agentId);


    List<Shipment> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source, String destination);
    List<Shipment> findByDeliveryDate(LocalDate deliveryDate);
}
