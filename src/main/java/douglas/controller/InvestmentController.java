package douglas.controller;

import douglas.domain.entity.Customer;
import douglas.domain.entity.Investment;
import douglas.domain.entity.Plan;
import douglas.service.InvestmentService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Path("customers/{id}/plans/{id}/investments")
public class InvestmentController {

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @GET
    public Response findAll(@PathParam("id") UUID customerId, @PathParam("id") Long planId) {
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
    public Response createRecipient(@PathParam("id") UUID customerId, @PathParam("id") Long planId, Investment investment) {
        Customer customer = Customer.findById(customerId);
        Plan plan = Plan.findById(planId);
        if (customer == null && plan == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            investment.plan = plan;
            investmentService.create(investment);
            return Response.status(Response.Status.CREATED).entity(investment).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    @GET
    public Response findById(@PathParam("id")  UUID customerId, @PathParam("id") Long planId, @PathParam("id") Long investmentId) {
        Customer customer = Customer.findById(customerId);
        Plan plan = Plan.findById(planId);
        if (customer == null && plan == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(investmentService.findById(investmentId)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteInvestment(@PathParam("id") UUID customerId,  @PathParam("id") Long planId, @PathParam("id") Long investmentId) {

        Customer customer = Customer.findById(customerId);
        Plan plan = Plan.findById(planId);
        if (customer == null && plan == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        investmentService.deleteById(investmentId);
        return Response.noContent().build();
    }
}
