package douglas.controller;

import douglas.domain.entity.Customer;
import douglas.domain.entity.Plan;
import douglas.service.PlanService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("customers/{id}/plans")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GET
    public Response findAll(@PathParam("id") UUID id) {
        Customer customer = Customer.findById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer.plans).build();
    }

    @POST
    @Transactional
    public Response createRecipient(@PathParam("id") UUID id, Plan plan) {
        Customer customer = Customer.findById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            plan.customer = customer;
            planService.create(plan);
            return Response.status(Response.Status.CREATED).entity(plan).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    public Response findById(@PathParam("id") Long id) {
        Customer customer = Customer.findById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(planService.findById(id)).build();
    }

    @DELETE
    @Path("/{planId}")
    @Transactional
    public Response deleteRecipient(@PathParam("id") UUID customerId, @PathParam("planId") Long planId) {

        Plan plan = Plan.findById(planId);
        if (plan == null || !plan.customer.id.equals(customerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        planService.deleteById(planId);
        return Response.noContent().build();
    }

}
