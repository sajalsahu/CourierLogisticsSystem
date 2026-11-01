package CourierLogisticsSystem.Controller;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Shipment;
import CourierLogisticsSystem.Service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Shipment>>> getAll() {
        return new ResponseEntity<>(shipmentService.getAllShipments(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Shipment>> getById(@PathVariable Integer id) {
        return new ResponseEntity<>(shipmentService.getById(id), HttpStatus.OK);
    }
}
