package CourierLogisticsSystem.Service;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.TrackingHistory;
import CourierLogisticsSystem.ExceptionLayer.NoRecordAvailableException;
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

    public ResponseStructure<List<TrackingHistory>> getAllTrackingHistoy(){
        List<TrackingHistory>  trackingHistories = trackingHistoryRepository.findAll();
        ResponseStructure<List<TrackingHistory>> res = new ResponseStructure<>();
        if(trackingHistories.isEmpty()){
            throw new NoRecordAvailableException("No history found");
        }
        res.setStatusCode(HttpStatus.OK.value());
        res.setData(trackingHistories);
        res.setMessage("Success");
        return res;
    }
    public ResponseStructure<TrackingHistory> getTrackingHistoryById(Integer id){
        Optional<TrackingHistory> trackingHistory = trackingHistoryRepository.findById(id);
        ResponseStructure<TrackingHistory> res = new ResponseStructure<>();
        if(trackingHistory.isEmpty()){
            throw new NoRecordAvailableException("No history found");
        }
        res.setStatusCode(HttpStatus.OK.value());
        res.setData(trackingHistory.get());
        res.setMessage("Success");
        return res;
    }

}
