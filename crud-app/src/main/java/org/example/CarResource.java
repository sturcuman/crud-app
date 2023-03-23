package org.example;

import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/cars")
public class CarResource {

    private static final Logger logger = Logger.getLogger(CarResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<CarEntity> cars = CarEntity.listAll();
        return Response.ok(cars).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") Long id) {
        return CarEntity.findByIdOptional(id)
                .map(car -> Response.ok(car).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    @Path("type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByType(@PathParam("type") String type) {
        List<CarEntity> cars = CarEntity.list("SELECT c FROM CarEntity c WHERE c.type = ?1 ORDER BY id " +
                "DESC", type);
        return Response.ok(cars).build();
    }

    @GET
    @Path("model/{model}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByModel(@PathParam("model") String model) {
        return CarEntity.find("model", model)
                .singleResultOptional()
                .map(car -> Response.ok(car).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());

    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(CarEntity car) {
        CarEntity.persist(car);
        if (car.isPersistent()) {
            return Response.created(URI.create("/cars" + car.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@PathParam("id") Long id) {
        boolean deleted = CarEntity.deleteById(id);

        if (deleted) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, CarEntity car) {
        CarEntity obj = CarEntity.findById(id);
        obj.setType(car.getType());
        obj.setModel(car.getModel());
        obj.setColor(car.getColor());
        obj.setMaxSpeed(car.getMaxSpeed());
        return Response.status(200).build();
    }
}