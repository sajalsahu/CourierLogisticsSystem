package CourierLogisticsSystem.Service;

import CourierLogisticsSystem.DTO.ResponseStructure;
import CourierLogisticsSystem.Entity.Customer;
import CourierLogisticsSystem.ExceptionLayer.*;
import CourierLogisticsSystem.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public ResponseStructure<Customer> createCustomer(Customer customer){
        if(customerRepository.existsByEmail(customer.getEmail())){
            throw new DuplicateResourceException("Email already exists");
        }
        if(customerRepository.existsByPhone(customer.getPhone())){
            throw new DuplicateResourceException("Phone number already exists");
        }
        if (customer.getPhone() == null || String.valueOf(customer.getPhone()).length() != 10) {
            throw new InvalidFormatException("Phone number must be exactly 10 digits");
        }
        ResponseStructure<Customer> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CREATED.value());
        res.setMessage("Customer is created");
        res.setData(customerRepository.save(customer));
        return res;
    }

    public ResponseStructure<List<Customer>> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        ResponseStructure<List<Customer>> res = new ResponseStructure<>();
        if(customers.isEmpty()){
            throw new NoRecordAvailableException("Customer is not present");
        }
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("All Customers");
        res.setData(customers);
        return res;
    }

    public ResponseStructure<Customer> getById(Integer id){
        Optional<Customer> customer = customerRepository.findById(id);
        ResponseStructure<Customer> res = new ResponseStructure<>();
        if(customer.isPresent()){
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Customer is found");
            res.setData(customer.get());
            return res;
        }
        throw new IdNotFoundException("Id is not found");
    }

    public ResponseStructure<Customer> getByEmail(String email){
        Optional<Customer> customer = customerRepository.findByEmail(email);
        ResponseStructure<Customer> res = new ResponseStructure<>();
        if(customer.isPresent()){
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Customer is found");
            res.setData(customer.get());
            return res;
        }
        throw new EmailNotFoundException("Email is not found");
    }

    public ResponseStructure<Customer> getByPhone(Long phone){
        Optional<Customer> customer = customerRepository.findByPhone(phone);
        ResponseStructure<Customer> res = new ResponseStructure<>();
        if(customer.isPresent()){
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Customer is found");
            res.setData(customer.get());
            return res;
        }
        throw new NoRecordAvailableException("Phone number is not found");
    }


    public ResponseStructure<Customer> updateCustomer(Customer customer){
        ResponseStructure<Customer> res = new ResponseStructure<>();
        //case 1
        if(customer.getId() == null){
            throw new IdNotFoundException("Id is not found");
        }
        if(!customerRepository.existsById(customer.getId())) {
            throw new IdNotFoundException("Id is not found");
        }
        // email must not belong to some OTHER customer
        Optional<Customer> byEmail = customerRepository.findByEmail(customer.getEmail());
        if (byEmail.isPresent() && !byEmail.get().getId().equals(customer.getId())) {
            throw new DuplicateResourceException("Email already exists");
        }
        if (customer.getPhone() == null || String.valueOf(customer.getPhone()).length() != 10) {
            throw new InvalidFormatException("Phone number must be exactly 10 digits");
        }
        // phone must not belong to some OTHER customer
        Optional<Customer> byPhone = customerRepository.findByPhone(customer.getPhone());
        if (byPhone.isPresent() && !byPhone.get().getId().equals(customer.getId())) {
            throw new DuplicateResourceException("Phone number already exists");
        }
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage("Customer is updated");
        res.setData(customerRepository.save(customer));
        return res;
    }

    public ResponseStructure<Customer> deleteCustomer(Integer id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        ResponseStructure<Customer> res = new ResponseStructure<>();
        if(customerOptional.isPresent()){
            customerRepository.delete(customerOptional.get());
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("Customer record with id " + id + " has been deleted");
            res.setData(customerOptional.get());///
            return res;
        }
        throw new NoRecordAvailableException("Customer record with id " + id + " is not found");
    }

    public ResponseStructure<Page<Customer>> getByPaginationAndSorting(int pageNumber, int pageSize, String fieldName){
        Page<Customer> page = customerRepository.findAll(PageRequest.of(pageNumber,pageSize, Sort.by(fieldName).ascending()));
        ResponseStructure<Page<Customer>> res = new ResponseStructure<>();
        if(page.isEmpty()){
            throw new NoRecordAvailableException("Page is empty");
        }
        else{
            res.setStatusCode(HttpStatus.OK.value());
            res.setMessage("All Customers");
            res.setData(page);
            return res;
        }
    }
}
