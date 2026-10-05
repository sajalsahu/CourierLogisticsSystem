package CourierLogisticsSystem.Controller;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Warehouse;
import CourierLogisticsSystem.Service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Warehouse>> createWarehouse(@RequestBody Warehouse warehouse) {
        return new ResponseEntity<>(warehouseService.createWarehouse(warehouse), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Warehouse>>> getAllWarehouse() {
        return new ResponseEntity<>(warehouseService.getAllWarehouses(), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Warehouse>> getWarehouseById(@PathVariable Integer id) {
        return new ResponseEntity<>(warehouseService.getWarehouseById(id), HttpStatus.OK);
    }
    @GetMapping("/location/{location}")
    public ResponseEntity<ResponseStructure<List<Warehouse>>> getWarehouseByLocation(@PathVariable String location) {
        return new ResponseEntity<>(warehouseService.getByLocation(location), HttpStatus.OK);
    }
    @GetMapping("/capacity/{capacity}")
    public ResponseEntity<ResponseStructure<List<Warehouse>>> getWarehouseByCapacity(@PathVariable Integer capacity) {
        return new ResponseEntity<>(warehouseService.getByCapacityGreaterThan(capacity), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ResponseStructure<Warehouse>>  updateWarehouse(@RequestBody Warehouse warehouse) {
        return new ResponseEntity<>(warehouseService.updateWarehouse(warehouse), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Warehouse>> deleteWarehouse(@PathVariable Integer id) {
        return new ResponseEntity<>(warehouseService.deleteWarehouse(id), HttpStatus.OK);
    }
}
