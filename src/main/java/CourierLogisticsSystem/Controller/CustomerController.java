package CourierLogisticsSystem.Controller;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Customer;
import CourierLogisticsSystem.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<ResponseStructure<Customer>> createCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseStructure<List<Customer>>>  getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerById(@PathVariable int id){
        return new ResponseEntity<>(customerService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerByEmail(@PathVariable String email){
        return new ResponseEntity<>(customerService.getByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/contact/{phone}")
    public ResponseEntity<ResponseStructure<Customer>> getCustomerByPhone(@PathVariable Long phone){
        return new ResponseEntity<>(customerService.getByPhone(phone), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(customerService.updateCustomer(customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<Customer>> deleteCustomer(@PathVariable int id){
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<ResponseStructure<Page<Customer>>> getCustomerByPage(@RequestParam int page, @RequestParam int size, @RequestParam String sort){
        return new ResponseEntity<>(customerService.getByPaginationAndSorting(page, size, sort), HttpStatus.OK);
    }


}
