package CourierLogisticsSystem.Service;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Warehouse;
import CourierLogisticsSystem.ExceptionLayer.DuplicateResourceException;
import CourierLogisticsSystem.ExceptionLayer.IdNotFoundException;
import CourierLogisticsSystem.ExceptionLayer.InvalidFormatException;
import CourierLogisticsSystem.ExceptionLayer.NoRecordAvailableException;
import CourierLogisticsSystem.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    public ResponseStructure<Warehouse> createWarehouse(Warehouse warehouse) {
        if (warehouseRepository.existsByContactNo(warehouse.getContactNo())) {
            throw new DuplicateResourceException("Contact number already exists");
        }
        ResponseStructure<Warehouse> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Warehouse created successfully");
        res.setData(warehouseRepository.save(warehouse));
        return res;
    }

    public ResponseStructure<List<Warehouse>> getAllWarehouses() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        ResponseStructure<List<Warehouse>> res = new ResponseStructure<>();
        if(warehouses.isEmpty()) {
            throw new NoRecordAvailableException("No warehouse record available");
        }
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Warehouses found");
        res.setData(warehouses);
        return res;
    }
    public ResponseStructure<Warehouse> getWarehouseById(Integer id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        ResponseStructure<Warehouse> res = new ResponseStructure<>();
        if(warehouse.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Warehouse found");
            res.setData(warehouse.get());
            return res;
        }
        throw new IdNotFoundException("Id not found");
    }
    public ResponseStructure<List<Warehouse>> getByLocation(String location) {
        List<Warehouse> warehouses = warehouseRepository.findByLocationIgnoreCase(location);
        ResponseStructure<List<Warehouse>> res = new ResponseStructure<>();
        if (warehouses.isEmpty()) {
            throw new NoRecordAvailableException("No warehouse found at location: " + location);
        }
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Warehouses found");
        res.setData(warehouses);
        return res;
    }
    public ResponseStructure<List<Warehouse>> getByCapacityGreaterThan(Integer capacity) {
        List<Warehouse> warehouses = warehouseRepository.findByCapacityGreaterThan(capacity);
        if (warehouses.isEmpty()) {
            throw new NoRecordAvailableException("No warehouse found with capacity greater than " + capacity);
        }
        ResponseStructure<List<Warehouse>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Warehouses found");
        res.setData(warehouses);
        return res;
    }
    public ResponseStructure<Warehouse> updateWarehouse(Warehouse warehouse) {
        if (warehouse.getId() == null || !warehouseRepository.existsById(warehouse.getId())) {
            throw new IdNotFoundException("Id not found");
        }
        // contact number must not belong to some OTHER warehouse
        Optional<Warehouse> byContact = warehouseRepository.findByContactNo(warehouse.getContactNo());
        if (byContact.isPresent() && !byContact.get().getId().equals(warehouse.getId())) {
            throw new DuplicateResourceException("Contact number already exists");
        }
        ResponseStructure<Warehouse> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Warehouse updated successfully");
        res.setData(warehouseRepository.save(warehouse));   // return the NEW saved data
        return res;
    }

    public ResponseStructure<Warehouse> deleteWarehouse(Integer id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        ResponseStructure<Warehouse> res = new ResponseStructure<>();
        if(warehouse.isPresent()) {
            warehouseRepository.delete(warehouse.get());
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Warehouse deleted");
            res.setData(warehouse.get());
            return res;
        }
        throw new IdNotFoundException("Id not found");
    }
}
