package douglas.controller;

import douglas.domain.entity.Customer;
import douglas.domain.entity.Recipient;
import douglas.service.RecipientService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("customers/{id}/recipients")
public class RecipientController {

    private final RecipientService recipientService;

    public RecipientController(RecipientService recipientService) {
        this.recipientService = recipientService;
    }


    @GET
    public Response findAll(@PathParam("id") UUID id) {
        Customer customer = Customer.findById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(customer.recipients).build();
    }

    @POST
    @Transactional
    public Response createRecipient(@PathParam("id") UUID id, Recipient recipient) {
        Customer customer = Customer.findById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        try {
            customer.addRecipient(recipient);
            recipient.customer = customer;
            recipientService.create(recipient);
            return Response.status(Response.Status.CREATED).entity(recipient).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    @GET
    public Response findById(@PathParam("id") UUID id) {
        Customer customer = Customer.findById(id);
        if (customer == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(recipientService.findById(id)).build();
    }

    @DELETE
    @Path("/{recipientId}")
    @Transactional
    public Response deleteRecipient(@PathParam("id") UUID customerId, @PathParam("recipientId") UUID recipientId) {

        Recipient recipient = Recipient.findById(recipientId);
        if (recipient == null || !recipient.customer.id.equals(customerId)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        recipientService.deleteById(recipientId);
        return Response.noContent().build();
    }

}
