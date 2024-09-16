package douglas.exception.customers;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException() {
        super("Cliente não encontrado, tente novamente!");
    }
}
