package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryAgentRepository extends JpaRepository<DeliveryAgent, Integer> {

}
