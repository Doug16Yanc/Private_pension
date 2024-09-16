package douglas.exception.customers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class CustomerAlreadyExistentExceptionMapper implements ExceptionMapper<CustomerAlreadyExistentException> {

    @Override
    public Response toResponse(CustomerAlreadyExistentException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
    }
}
