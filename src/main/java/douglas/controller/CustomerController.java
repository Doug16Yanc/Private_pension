package douglas.controller;

import douglas.domain.entity.Customer;
import douglas.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
                            @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
        var customers = customerService.findAll(page, pageSize);

        return Response.ok(customers).build();
    }

    @POST
    @Transactional
    public Response createUser(Customer customer) {
        return Response.ok(customerService.create(customer)).build();
    }
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") UUID id) {
        return Response.ok(customerService.findById(id)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") UUID id) {
        customerService.deleteById(id);
        return Response.noContent().build();
    }
}
