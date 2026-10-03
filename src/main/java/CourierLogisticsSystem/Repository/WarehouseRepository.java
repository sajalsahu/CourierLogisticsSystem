package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    
}

