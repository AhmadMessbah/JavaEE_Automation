package com.mftplus.automation.controller.api;

import com.mftplus.automation.service.impl.CashTransactionServiceImp;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/cashTransaction")
public class CashTransactionApi {
    @PersistenceContext(unitName = "automation")

    @Inject
    private CashTransactionServiceImp cashTransactionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("Find All Cash Transaction");
            return Response
                    .ok()
                    .entity(cashTransactionService.findAll())
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
            log.info("Find By Id Cash Transaction");
            return Response
                    .ok()
                    .entity(cashTransactionService.findById(id))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(CashTransaction cashTransaction) {
        try {
            log.info("Save Cash Transaction");
            cashTransactionService.save(cashTransaction);
            return Response
                    .ok()
                    .entity(cashTransaction)
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(CashTransaction cashTransaction) {
        try {
            log.info("Edit Cash Transaction");
            cashTransactionService.edit(cashTransaction);
            return Response
                    .ok()
                    .entity(cashTransaction)
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeById(@PathParam("id") Long id) {
        try {
            log.info("Remove By Id Cash Transaction");
            cashTransactionService.removeById(id);
            return Response
                    .ok()
                    .entity(id)
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{cashDeskNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCashDeskNumber(@PathParam("cashDeskNumber") int cashDeskNumber) {
        try {
            log.info("Find By CashDeskNumber Cash Transaction");
            return Response
                    .ok()
                    .entity(cashTransactionService.findByCashDeskNumber(cashDeskNumber))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
