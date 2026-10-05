package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    Optional<Warehouse> findByContactNo(Long contactNo);

    boolean existsByContactNo(Long contactNo);
    List<Warehouse> findByCapacityGreaterThan(Integer capacity);
    List<Warehouse> findByLocationIgnoreCase(String location);

}


