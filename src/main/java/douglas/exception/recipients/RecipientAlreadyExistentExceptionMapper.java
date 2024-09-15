package douglas.exception.recipients;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class RecipientAlreadyExistentExceptionMapper implements ExceptionMapper<RecipientAlreadyExistentException> {
    @Override
    public Response toResponse(RecipientAlreadyExistentException e) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Beneficiário não encontrado em nossa base de dados.").build();
    }
}
