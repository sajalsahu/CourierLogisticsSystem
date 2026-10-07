package CourierLogisticsSystem.Repository;

import CourierLogisticsSystem.Entity.Customer;
import CourierLogisticsSystem.Entity.Package;
import CourierLogisticsSystem.Entity.PackageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Integer> {
    List<Package> findByPackageType(PackageType packageType);
}
