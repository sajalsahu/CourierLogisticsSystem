package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(Long phone);

    boolean existsByEmail(String email);
    boolean existsByPhone(Long phone);
}
