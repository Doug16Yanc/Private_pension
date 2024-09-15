package douglas.exception.customers;

public class CustomerAlreadyExistentException extends RuntimeException{
    public CustomerAlreadyExistentException() {
        super("Customer with the provided CPF already exists.");
    }
}
