package douglas.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class CustomerAlreadyExistentExceptionMapper implements ExceptionMapper<CustomerAlreadyExistentExpection> {

    @Override
    public Response toResponse(CustomerAlreadyExistentExpection exception) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Cliente já cadastrado em nossa base de dados.").build();
    }
}
