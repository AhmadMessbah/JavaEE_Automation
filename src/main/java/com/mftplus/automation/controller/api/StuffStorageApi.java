package com.mftplus.automation.controller.api;
import com.mftplus.automation.model.StuffStorage;
import com.mftplus.automation.service.impl.StuffStorageServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/stuffStorage")
public class StuffStorageApi {

    @Inject
    private StuffStorageServiceImpl stuffStorageService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        log.info("ّFind All StuffStorage");
        try {
            return Response
                    .ok()
                    .entity(stuffStorageService.findAll())
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
    public Response findById(@PathParam("id") long id) {
        log.info("FindByID StuffStorage");
        try {
            return Response
                    .ok()
                    .entity(stuffStorageService.findById(id))
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .status(500)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(StuffStorage stuffStorage) {
        log.info("Save StuffStorage");
        try {
            stuffStorageService.save(stuffStorage);
            return Response
                    .accepted()
                    .entity(stuffStorage)
                    .build();
        } catch (Exception e) {
            log.error("Error Save StuffStorage");
            return Response
                    .status(200)
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
            log.info("Remove stuffStorage");
            stuffStorageService.removeById(id);
            return Response
                    .ok()
                    .entity(removeById(id))
                    .build();
        } catch (Exception e) {
            log.error("Error Remove StuffStorage");
            return Response
                    .status(200)
                    .entity(e.getMessage())
                    .build();
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(StuffStorage stuffStorage) {
        try {
            log.info("Edit StuffStorage");
            stuffStorageService.edit(stuffStorage);
            return Response
                    .accepted()
                    .entity(stuffStorage)
                    .build();
        } catch (Exception e) {
            log.error("Error Edit StuffStorage");
            return Response
                    .status(200)
                    .entity(e.getMessage())
                    .build();
        }
    }


}
