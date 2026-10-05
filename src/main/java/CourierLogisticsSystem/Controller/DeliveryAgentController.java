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

}
