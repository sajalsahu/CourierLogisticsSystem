package CourierLogisticsSystem.Service;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Payment;
import CourierLogisticsSystem.Entity.PaymentStatus;
import CourierLogisticsSystem.ExceptionLayer.IdNotFoundException;
import CourierLogisticsSystem.ExceptionLayer.NoRecordAvailableException;
import CourierLogisticsSystem.ExceptionLayer.OperationNotAllowedException;
import CourierLogisticsSystem.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public ResponseStructure<List<Payment>> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        ResponseStructure<List<Payment>> res = new ResponseStructure<>();
        if(payments.isEmpty()) {
            throw new NoRecordAvailableException("No payment record available");
        }
        res.setStatusCode(HttpStatus.OK.value());
        res.setData(payments);
        res.setMessage("Success");
        return res;
    }
    public ResponseStructure<Payment> getPaymentById(Integer id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        ResponseStructure<Payment> res = new ResponseStructure<>();
        if(payment.isPresent()) {
            res.setStatusCode(HttpStatus.OK.value());
            res.setData(payment.get());
            res.setMessage("Success");
            return res;
        }
        throw  new IdNotFoundException("Payment id is not found");
    }
    public ResponseStructure<Payment> updateStatus(Integer id, PaymentStatus newStatus) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()) {
            Payment payment = optional.get();
            PaymentStatus current = getPaymentStatus(newStatus, payment);
            // rule: a completed payment cannot go back to pending or failed
            if (current == PaymentStatus.COMPLETED
                    && (newStatus == PaymentStatus.PENDING || newStatus == PaymentStatus.FAILED)) {
                throw new OperationNotAllowedException("Completed payment cannot be changed to " + newStatus);
            }

            payment.setPaymentStatus(newStatus);
            if (newStatus == PaymentStatus.COMPLETED) {
                payment.setPaymentDateTime(LocalDateTime.now());   // time the money was received
            }

            ResponseStructure<Payment> res = new ResponseStructure<>();
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Payment status updated successfully");
            res.setData(paymentRepository.save(payment));
            return res;
        }
        throw new IdNotFoundException("Payment id is not found");
    }

    private static PaymentStatus getPaymentStatus(PaymentStatus newStatus, Payment payment) {
        PaymentStatus current = payment.getPaymentStatus();

        // rule: a refunded payment is closed
        if (current == PaymentStatus.REFUNDED) {
            throw new OperationNotAllowedException("Payment is already refunded, status cannot be changed");
        }
        // rule: only a completed payment can be refunded
        if (newStatus == PaymentStatus.REFUNDED && current != PaymentStatus.COMPLETED) {
            throw new OperationNotAllowedException("Only a completed payment can be refunded");
        }
        return current;
    }
    public ResponseStructure<Payment> deletePayment(Integer id) {
        Optional<Payment> optional = paymentRepository.findById(id);
        if (optional.isPresent()) {
            Payment payment = optional.get();

            // rule: only failed or refunded payments can be deleted
            if (payment.getPaymentStatus() != PaymentStatus.FAILED
                    && payment.getPaymentStatus() != PaymentStatus.REFUNDED) {
                throw new OperationNotAllowedException(
                        "Only FAILED or REFUNDED payments can be deleted");
            }

            paymentRepository.delete(payment);
            ResponseStructure<Payment> res = new ResponseStructure<>();
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Payment deleted successfully");
            res.setData(payment);
            return res;
        }
        throw new IdNotFoundException("Payment id is not found");
    }
}
