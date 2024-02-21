package com.mftplus.automation.controller.api;
import com.mftplus.automation.model.Stuff;
import com.mftplus.automation.service.impl.StuffServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/stuff")
public class StuffApi {

    @Inject
    private StuffServiceImpl stuffService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        log.info("Find All Stuff");
        try {

            return Response
                    .ok()
                    .entity(stuffService.findAll())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .status(500)
                    .entity(e.getMessage())
                    .build();
        }
    }


    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id")long id) {
        try {
            log.info("FindBy ID");
            return Response
                    .ok()
                    .entity(stuffService.findById(id))
                    .build();
        } catch (Exception e) {
            return Response
                    .status(500)
                    .entity(e.getMessage())
                    .build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Stuff stuff) {
        try {
            log.info("Save Stuff");
           stuffService.save(stuff);
            return Response
                    .ok()
                    .entity(stuff)
                    .build();
        } catch (Exception e) {
            log.info("Save Error");
            return Response.
                    serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeById(@PathParam("id")long id) {
        try {
            log.info("Remove Stuff");
            stuffService.removeById(id);
            return Response
                    .ok()
                    .entity(removeById(id))
                    .build();
        } catch (Exception e) {
            log.error("Error Remove Stuff");
            return Response
                    .status(200)
                    .entity(e.getMessage())
                    .build();
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Stuff stuff) {
        try {
            System.out.println("masoooooooooooood");
            stuffService.findById(stuff.getId());
            stuffService.edit(stuff);
            log.info("Edit Stuff");
            return Response
                    .ok()
                    .entity(stuff)
                    .build();
        } catch (Exception e) {
            log.error("Edit Error");
            return Response.
                    serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }
}
