package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.Customer;
import CourierLogisticsSystem.Entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Integer> {

}
