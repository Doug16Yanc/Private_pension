package douglas.exception.plans;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class PlanNotFoundExceptionMapper implements ExceptionMapper<PlanNotFoundException> {
    @Override
    public Response toResponse(PlanNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Plano não encontrado em nossa base de dados.").build();
    }
}
