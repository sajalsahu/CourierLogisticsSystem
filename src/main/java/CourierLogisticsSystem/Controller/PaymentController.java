package CourierLogisticsSystem.Controller;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Payment;
import CourierLogisticsSystem.Service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }
    public ResponseEntity<ResponseStructure<Payment>> getPaymentById(Integer id) {
        return new ResponseEntity<>(paymentService.getPaymentById(id),HttpStatus.OK);
    }
}
