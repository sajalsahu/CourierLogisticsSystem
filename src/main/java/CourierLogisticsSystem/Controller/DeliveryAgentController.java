package CourierLogisticsSystem.Controller;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.DeliveryAgent;
import CourierLogisticsSystem.Service.DeliveryAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveryAgent")
public class DeliveryAgentController {
    @Autowired
    private DeliveryAgentService deliveryAgentService;

    @PostMapping
    public ResponseEntity<ResponseStructure<DeliveryAgent>> createCustomer(@RequestBody DeliveryAgent deliveryAgent){
        return new ResponseEntity<>(deliveryAgentService.creatAgent(deliveryAgent), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAllAgents(){
        return new ResponseEntity<>(deliveryAgentService.getAllAgent(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<DeliveryAgent>> getAgentById(@PathVariable Integer id){
        return new ResponseEntity<>(deliveryAgentService.getById(id), HttpStatus.OK);
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<ResponseStructure<DeliveryAgent>> getAgentByPhone(@PathVariable Long phone){
        return new ResponseEntity<>(deliveryAgentService.getByPhone(phone), HttpStatus.OK);
    }
    @GetMapping("/vehicle/{vehicleNo}")
    public ResponseEntity<ResponseStructure<DeliveryAgent>> getAgentByVehicleNo(@PathVariable String vehicleNo){
        return new ResponseEntity<>(deliveryAgentService.getByVehicleNo(vehicleNo), HttpStatus.OK);
    }
    @GetMapping("/rating/{rating")
    public ResponseEntity<ResponseStructure<List<DeliveryAgent>>> getAgentByRating(@PathVariable Double rating){
        return new ResponseEntity<>(deliveryAgentService.getByRatingGreaterThanEqual(rating), HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ResponseStructure<DeliveryAgent>> updateAgent(@RequestBody DeliveryAgent deliveryAgent){
        return new ResponseEntity<>(deliveryAgentService.updateAgent(deliveryAgent), HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<ResponseStructure<DeliveryAgent>> deleteAgent(@PathVariable Integer id){
        return new ResponseEntity<>(deliveryAgentService.deleteAgent(id), HttpStatus.OK);
    }
    @PatchMapping("{id}/availability")
    public ResponseEntity<ResponseStructure<DeliveryAgent>> updateAgentAvailability(@PathVariable Integer id, @RequestBody Boolean availability){
        return new ResponseEntity<>(deliveryAgentService.updateAvailability(id, availability), HttpStatus.OK);
    }
}
