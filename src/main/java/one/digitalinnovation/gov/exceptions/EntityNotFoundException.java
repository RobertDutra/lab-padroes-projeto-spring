package one.digitalinnovation.gov.exceptions;

public class EntityNotFoundException extends Exception{

    String msg;

    public EntityNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
