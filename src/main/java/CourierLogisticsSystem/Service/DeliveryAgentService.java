package CourierLogisticsSystem.Service;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.DeliveryAgent;
import CourierLogisticsSystem.ExceptionLayer.DuplicateResourceException;
import CourierLogisticsSystem.ExceptionLayer.IdNotFoundException;
import CourierLogisticsSystem.ExceptionLayer.NoRecordAvailableException;
import CourierLogisticsSystem.Repository.DeliveryAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryAgentService {
    @Autowired
    private DeliveryAgentRepository deliveryAgentRepository;

    public ResponseStructure<DeliveryAgent> creatAgent(DeliveryAgent deliveryAgent) {
        if (deliveryAgentRepository.existsByPhone(deliveryAgent.getPhone())) {
            throw new DuplicateResourceException("Contact number already exists");
        }
        if (deliveryAgentRepository.existsByVehicleNo(deliveryAgent.getVehicleNo())) {
            throw new DuplicateResourceException("Vehicle number already exists");
        }
        ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Agent created successfully");
        res.setData(deliveryAgentRepository.save(deliveryAgent));
        return res;
    }
    public ResponseStructure<List<DeliveryAgent>> getAllAgent(){
        List<DeliveryAgent> deliveryAgents = deliveryAgentRepository.findAll();
        if (deliveryAgents.isEmpty()) {
            throw new NoRecordAvailableException("No agent record available");
        }
        ResponseStructure<List<DeliveryAgent>> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("All agents found");
        res.setData(deliveryAgents);
        return res;
    }
    public ResponseStructure<DeliveryAgent> getById(Integer id){
        Optional<DeliveryAgent> deliveryAgent = deliveryAgentRepository.findById(id);
        ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
        if(deliveryAgent.isPresent()){
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Agent found successfully");
            res.setData(deliveryAgent.get());
            return res;
        }
        throw new IdNotFoundException("Agent id is not found");
    }
    public ResponseStructure<DeliveryAgent> getByPhone(Long phone){
        Optional<DeliveryAgent> agent= deliveryAgentRepository.findByPhone(phone);
        ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
        if(agent.isPresent()){
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Agent found successfully");
            res.setData(agent.get());
            return res;
        }
        throw new NoRecordAvailableException("Phone number is not found");
    }
    public ResponseStructure<DeliveryAgent> getByVehicleNo(String vehicleNo){
        Optional<DeliveryAgent> agent = deliveryAgentRepository.findByVehicleNo(vehicleNo);
        ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
        if(agent.isPresent()){
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Agent found successfully");
            res.setData(agent.get());
            return res;
        }
        throw new NoRecordAvailableException("Vehicle number is not found");
    }
    public ResponseStructure<List<DeliveryAgent>> getByRatingGreaterThanEqual(Double rating){
        List<DeliveryAgent> deliveryAgents = deliveryAgentRepository.findByRatingGreaterThanEqual(rating);
        ResponseStructure<List<DeliveryAgent>> res = new ResponseStructure<>();
        if(deliveryAgents.isEmpty()){
            res.setStatusCode(HttpStatus.NO_CONTENT.value());
            res.setMessage("Agent not found");
            res.setData(deliveryAgents);
            return res;
        }
        throw new NoRecordAvailableException("Agent not found");
    }
    public ResponseStructure<DeliveryAgent> updateAgent(DeliveryAgent deliveryAgent) {
        if (deliveryAgent.getId() == null || !deliveryAgentRepository.existsById(deliveryAgent.getId())) {
            throw new IdNotFoundException("Agent id is not found");
        }
        // phone must not belong to some OTHER agent
        Optional<DeliveryAgent> byPhone = deliveryAgentRepository.findByPhone(deliveryAgent.getPhone());
        if (byPhone.isPresent() && !byPhone.get().getId().equals(deliveryAgent.getId())) {
            throw new DuplicateResourceException("Contact number already exists");
        }
        // vehicle number must not belong to some OTHER agent
        Optional<DeliveryAgent> byVehicle = deliveryAgentRepository.findByVehicleNo(deliveryAgent.getVehicleNo());
        if (byVehicle.isPresent() && !byVehicle.get().getId().equals(deliveryAgent.getId())) {
            throw new DuplicateResourceException("Vehicle number already exists");
        }
        ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Agent updated successfully");
        res.setData(deliveryAgentRepository.save(deliveryAgent));
        return res;
    }
    public ResponseStructure<DeliveryAgent> deleteAgent(Integer id) {
        Optional<DeliveryAgent> deliveryAgent = deliveryAgentRepository.findById(id);
        ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
        if(deliveryAgent.isPresent()){
            deliveryAgentRepository.delete(deliveryAgent.get());
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Agent deleted successfully");
            res.setData(deliveryAgent.get());
            return res;
        }
        throw new NoRecordAvailableException("Agent id is not found");
    }
    public ResponseStructure<DeliveryAgent> updateAvailability(Integer id,Boolean status) {
        Optional<DeliveryAgent> agent = deliveryAgentRepository.findById(id);
        ResponseStructure<DeliveryAgent> res = new ResponseStructure<>();
        if (agent.isPresent()) {
            DeliveryAgent deliveryAgent = agent.get();
            deliveryAgent.setAvailabilityStatus(status);
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Agent availability updated successfully");
            res.setData(deliveryAgentRepository.save(agent.get()));
            return res;
        }
        throw new NoRecordAvailableException("Agent id is not found");
    }
}
