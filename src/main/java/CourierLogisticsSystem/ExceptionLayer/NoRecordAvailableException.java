package CourierLogisticsSystem.ExceptionLayer;

public class NoRecordAvailableException extends RuntimeException{
    public NoRecordAvailableException(String message){
        super(message);
    }
}
