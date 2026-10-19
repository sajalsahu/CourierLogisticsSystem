package CourierLogisticsSystem.Controller;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Package;
import CourierLogisticsSystem.Service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/package")
public class PackageController {
    @Autowired
    private PackageService packageService;

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Package>>>getAllPackages() {
        return new ResponseEntity<>(packageService.getAllPackages(), HttpStatus.OK);
    }

}
