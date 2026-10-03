package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Integer> {
}
