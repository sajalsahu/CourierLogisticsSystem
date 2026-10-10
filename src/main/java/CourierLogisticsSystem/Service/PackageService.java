package CourierLogisticsSystem.Service;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Package;
import CourierLogisticsSystem.Entity.PackageType;
import CourierLogisticsSystem.ExceptionLayer.IdNotFoundException;
import CourierLogisticsSystem.ExceptionLayer.NoRecordAvailableException;
import CourierLogisticsSystem.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    public ResponseStructure<List<Package>> getAllPackages() {
        List<Package> packages = packageRepository.findAll();
        if (packages.isEmpty()) {
            throw new NoRecordAvailableException("No package record available");
        }
        ResponseStructure<List<Package>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("All packages found");
        res.setData(packages);
        return res;
    }
    public ResponseStructure<Package> getPackageById(Integer id) {
        Optional<Package> pack = packageRepository.findById(id);
        ResponseStructure<Package> res = new ResponseStructure<>();
        if(pack.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Package found");
            res.setData(pack.get());
            return res;
        }
        throw new IdNotFoundException("Package not found");
    }
    public ResponseStructure<List<Package>> getPackageByType(PackageType packageType) {
        List<Package> packages = packageRepository.findByPackageType(packageType);
        if (packages.isEmpty()) {
            throw new NoRecordAvailableException("No package found with type " + packageType);
        }
        ResponseStructure<List<Package>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Packages found");
        res.setData(packages);
        return res;
    }
}
