package CourierLogisticsSystem.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Shipment {
    private Integer id;
    private Long trackingNo;
    private String source;
    private String destination;
    private Double weight;
    private LocalDateTime shipmentDateTime;
    private LocalDate deliveryDate;
    private Status status;


}
