package douglas.controller;

import douglas.domain.entity.Customer;
import douglas.domain.entity.Plan;
import douglas.service.PlanService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/customers/{customerId}/plans")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GET
    public Response findAll(@PathParam("customerId") UUID id) {
        Customer customer = Customer.findById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer.plans).build();
    }

    @POST
    @Transactional
    public Response createRecipient(Plan plan) {
        try {
            planService.create(plan);
            return Response.status(Response.Status.CREATED).entity(plan).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{planId}")
    public Response findById(@PathParam("planId") Long planId) {
        return Response.ok(planService.findById(planId)).build();
    }

    @DELETE
    @Path("/{planId}")
    @Transactional
    public Response deleteRecipient(@PathParam("planId") Long planId) {

        planService.deleteById(planId);
        return Response.noContent().build();
    }

}
