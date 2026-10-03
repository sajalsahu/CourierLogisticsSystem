package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.Payment;
import CourierLogisticsSystem.Entity.TrackingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingHistoryRepository extends JpaRepository<TrackingHistory, Integer> {
}
