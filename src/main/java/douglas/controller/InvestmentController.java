package douglas.controller;

import douglas.domain.entity.Customer;
import douglas.domain.entity.Investment;
import douglas.domain.entity.Plan;
import douglas.service.InvestmentService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Path("/customers/{customerId}/plans/{planId}/investments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GET
    public Response findAll(@PathParam("customerId") Long customerId, @PathParam("planId") Long planId) {
        Customer customer = Customer.findById(customerId);
        Plan plan = Plan.findById(planId);
        if (customer == null && plan == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Investment> investments = customer.plans.stream()
                .flatMap(p -> plan.investments.stream())
                .collect(Collectors.toList());

        return Response.ok(investments).build();
    }

    @POST
    @Transactional
    public Response createInvestment(Investment investment) {
        try {
            investmentService.create(investment);
            return Response.ok(investmentService.create(investment)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    @GET
    @Path("/{investmentId}")
    public Response findById(@PathParam("investmentId") Long investmentId) {
        return Response.ok(investmentService.findById(investmentId)).build();
    }

    @DELETE
    @Path("/{investmentId}")
    @Transactional
    public Response deleteInvestment(@PathParam("investmentId") Long investmentId) {

        investmentService.deleteById(investmentId);
        return Response.noContent().build();
    }
}
