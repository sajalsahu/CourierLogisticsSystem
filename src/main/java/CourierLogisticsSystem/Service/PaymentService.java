package CourierLogisticsSystem.Service;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Payment;
import CourierLogisticsSystem.ExceptionLayer.NoRecordAvailableException;
import CourierLogisticsSystem.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
