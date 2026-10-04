package CourierLogisticsSystem.ExceptionLayer;

import CourierLogisticsSystem.DTO.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoRecordAvailableException.class)
    public ResponseEntity<ResponseStructure<String>> handleNRE(NoRecordAvailableException e){
        ResponseStructure<String> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setMessage(e.getMessage());
        res.setData("No data available");
        return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleINFE(IdNotFoundException e){
        ResponseStructure<String> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setMessage(e.getMessage());
        res.setData("No data available");
        return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ResponseStructure<String>> handleENFE(EmailNotFoundException e){
        ResponseStructure<String> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.NOT_FOUND.value());
        res.setMessage(e.getMessage());
        res.setData("Email not available");
        return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ResponseStructure<String>> handleDRE(DuplicateResourceException e){
        ResponseStructure<String> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.CONFLICT.value());
        res.setMessage(e.getMessage());
        res.setData("Duplicate data");
        return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ResponseStructure<String>> handleIFE(InvalidFormatException e){
        ResponseStructure<String> res = new ResponseStructure<>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setMessage(e.getMessage());
        res.setData("Validation failed");
        return new ResponseEntity<ResponseStructure<String>>(res, HttpStatus.BAD_REQUEST);
    }
}
