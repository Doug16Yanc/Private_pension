package douglas.exception.investments;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class InvestmentNotFoundExceptionMapper implements ExceptionMapper<InvestmentNotFoundException> {

        @Override
        public Response toResponse(InvestmentNotFoundException exception) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Investimento não encontrado em nossa base de dados.").build();
        }
}
