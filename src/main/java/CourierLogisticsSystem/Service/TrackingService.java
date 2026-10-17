package CourierLogisticsSystem.Service;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.TrackingHistory;
import CourierLogisticsSystem.ExceptionLayer.NoRecordAvailableException;
import CourierLogisticsSystem.Repository.ShipmentRepository;
import CourierLogisticsSystem.Repository.TrackingHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackingService {
    @Autowired
    private TrackingHistoryRepository trackingHistoryRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;

    public ResponseStructure<List<TrackingHistory>> getAllTrackingHistoy(){
        List<TrackingHistory>  trackingHistories = trackingHistoryRepository.findAll();
        ResponseStructure<List<TrackingHistory>> res = new ResponseStructure<>();
        if(trackingHistories.isEmpty()){
            throw new NoRecordAvailableException("No tracking history records available");
        }
        res.setStatusCode(HttpStatus.OK.value());
        res.setData(trackingHistories);
        res.setMessage("Tracking history retrieved successfully");
        return res;
    }
    public ResponseStructure<TrackingHistory> getTrackingHistoryById(Integer id){
        Optional<TrackingHistory> trackingHistory = trackingHistoryRepository.findById(id);
        ResponseStructure<TrackingHistory> res = new ResponseStructure<>();
        if(trackingHistory.isEmpty()){
            throw new NoRecordAvailableException("Tracking history ID not found");
        }
        res.setStatusCode(HttpStatus.OK.value());
        res.setData(trackingHistory.get());
        res.setMessage("Tracking history found successfully");
        return res;
    }
    public ResponseStructure<TrackingHistory> getByTrackingNumber(String name){

    }

}
