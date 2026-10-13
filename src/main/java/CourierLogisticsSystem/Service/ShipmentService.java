package CourierLogisticsSystem.Service;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Shipment;
import CourierLogisticsSystem.ExceptionLayer.IdNotFoundException;
import CourierLogisticsSystem.ExceptionLayer.NoRecordAvailableException;
import CourierLogisticsSystem.Repository.CustomerRepository;
import CourierLogisticsSystem.Repository.ShipmentRepository;
import CourierLogisticsSystem.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ShipmentService shipmentService;


    public ResponseStructure<Shipment> createShipment(Shipment shipment){
        ResponseStructure<Shipment> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Shipment created successfully");
        res.setData(shipment);
        return res;
    }
    public ResponseStructure<List<Shipment>> getAllShipments(){
        List<Shipment> shipments = shipmentRepository.findAll();
        ResponseStructure<List<Shipment>> res = new ResponseStructure<>();
        if(shipments.isEmpty()){
            throw new NoRecordAvailableException("Shipment not found");
        }
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Shipment found successfully");
        res.setData(shipments);
        return res;
    }
    public ResponseStructure<Shipment> getShipmentById(Integer id){
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        ResponseStructure<Shipment> res = new ResponseStructure<>();
        if(shipment.isPresent()){
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Shipment found successfully");
            res.setData(shipment.get());
            return res;
        }
        throw new IdNotFoundException("Shipment not found");
    }
    public ResponseStructure<Shipment> getByTrackingNo(Long trackingNo){
        Optional<Shipment> shipment = shipmentRepository.findByTrackingNo(trackingNo);
        ResponseStructure<Shipment> res = new ResponseStructure<>();
        if(shipment.isPresent()){
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Shipment found successfully");
            res.setData(shipment.get());
            return res;
        }
        throw new NoRecordAvailableException("Tracking number not found");
    }
}
