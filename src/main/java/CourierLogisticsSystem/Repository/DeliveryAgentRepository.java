package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Integer> {

    boolean existsByPhone(Long contactNo);
    boolean existsByVehicleNo(String vehicleNo);

    Optional<DeliveryAgent> findByVehicleNo(String vehicleNo);
    Optional<DeliveryAgent> findByPhone(Long contactNo);
    List<DeliveryAgent> findByRatingGreaterThanEqual(Double rating);
}
