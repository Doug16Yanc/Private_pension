package douglas.exception.customers;

public class CustomerAlreadyExistentException extends RuntimeException{
    public CustomerAlreadyExistentException() {
        super("Já há um cliente cadastrado com esse CPF no banco de dados.");
    }
}
