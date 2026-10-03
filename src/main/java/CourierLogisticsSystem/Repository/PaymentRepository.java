package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
