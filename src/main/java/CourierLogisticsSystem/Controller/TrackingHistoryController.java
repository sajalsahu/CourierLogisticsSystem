package CourierLogisticsSystem.Controller;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.TrackingHistory;
import CourierLogisticsSystem.Service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tracking-history")
public class TrackingHistoryController {
    @Autowired
    private TrackingService trackingService;

    @GetMapping
    public ResponseEntity<ResponseStructure<List<TrackingHistory>>> getAllTrackingHistory() {
        return new ResponseEntity<>(trackingService.getAllTrackingHistoy(), HttpStatus.OK);
    }
    public ResponseEntity<ResponseStructure<TrackingHistory>> getTrackingHistoryById(Integer id) {
        return new ResponseEntity<>(trackingService.getTrackingHistoryById(id), HttpStatus.OK);
    }
}
