package CourierLogisticsSystem.Controller;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Payment;
import CourierLogisticsSystem.Entity.PaymentStatus;
import CourierLogisticsSystem.Service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment() {
        return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Payment>> getPaymentById(Integer id) {
        return new ResponseEntity<>(paymentService.getPaymentById(id),HttpStatus.OK);
    }
    @PatchMapping("{id}/status")
    public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(@PathVariable Integer id, @RequestParam PaymentStatus status) {
        return new ResponseEntity<>(paymentService.updateStatus(id, status), HttpStatus.OK);
    }

}
