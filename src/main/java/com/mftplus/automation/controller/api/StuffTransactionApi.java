package com.mftplus.automation.controller.api;

import com.mftplus.automation.model.StuffTransaction;
import com.mftplus.automation.service.impl.StuffTransactionServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;
@Slf4j
@Path("stuffTransaction")
public class StuffTransactionApi {

    @Inject
    private StuffTransactionServiceImpl stuffTransactionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(){
        log.info("Find All StuffTransaction Service Api");
        try {
            return Response
                    .ok()
                    .entity(stuffTransactionService.findAll())
                    .build();
        }catch (Exception e){
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
    public Response FindById(@PathParam("id")long id){
        try {
            log.info("FindById StuffTransaction Service Api");
            return Response
                    .ok()
                    .entity(stuffTransactionService.findById(id))
                    .build();
        }catch (Exception e){
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
    public Response save(StuffTransaction stuffTransaction){
        try {
            log.info("Save StuffTransactionApi");
            stuffTransactionService.save(stuffTransaction);
            return Response
                    .accepted()
                    .entity(stuffTransaction)
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
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
    public Response remove(@PathParam("id") Long id){
        try {
            stuffTransactionService.removeById(id);
            return Response
                    .ok()
                    .entity(id)
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
            return Response
                    .status(200)
                    .entity(e.getMessage())
                    .build();
        }
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(StuffTransaction stuffTransaction){
        try {
            log.info("Edit StuffTransactionApi");
            stuffTransactionService.edit(stuffTransaction);
            return Response
                    .accepted()
                    .entity(stuffTransaction)
                    .build();
        }catch (Exception e){
            log.error(e.getMessage());
            return Response
                    .status(200)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
