package com.mftplus.automation.controller.api;

import com.mftplus.automation.model.Attach;
import com.mftplus.automation.service.impl.AttachServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/attach")
public class AttachApi {

    @Inject
    private AttachServiceImpl attachService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("FindAllAttach");
            return Response
                    .ok()
                    .entity(attachService.findAll())
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
            log.info("FindByIdAttach");
            return Response
                    .ok()
                    .entity(attachService.findById(id))
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Attach attach) {
        try {
            log.info("Save Attach");
            attachService.save(attach);
            return Response
                    .ok()
                    .entity(attach)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Attach attach) {
        try {
            log.info("Edit Attach");
            attachService.edit(attach);
            return Response
                    .ok()
                    .entity(attach)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Long id) {
        try {
            log.info("Remove Attach");
            attachService.removeById(id);
            return Response
                    .ok()
                    .entity(id)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }
}
    

