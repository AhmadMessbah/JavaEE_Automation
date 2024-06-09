package com.mftplus.controller.api;

import com.mftplus.model.CashDesk;
import com.mftplus.service.impl.CashDeskServiceImp;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/cashDesk")
public class CashDeskApi {

    @Inject
    private CashDeskServiceImp cashDeskService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("Find All CashDesk");
            return Response
                    .ok()
                    .entity(cashDeskService.findAll())
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
            log.info("Find By Id CashDesk");
            return Response
                    .ok()
                    .entity(cashDeskService.findById(id))
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
    public Response save(CashDesk cashDesk) {
        try {
            log.info("Save CashDesk");
            cashDeskService.save(cashDesk);
            return Response
                    .ok()
                    .entity(cashDesk)
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
            log.info("Remove By Id Cash Desk");
            cashDeskService.removeById(id);
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
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("name") String name) {
        try {
            log.info("Find By name CashDesk");
            return Response
                    .ok()
                    .entity(cashDeskService.findByName(name))
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
            log.info("Find By cash Desk Number CashDesk");
            return Response
                    .ok()
                    .entity(cashDeskService.findByCashDeskNumber(cashDeskNumber))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCashier(@PathParam("username") String username) {
        try {
            log.info("Find By Cashier CashDesk");
            return Response
                    .ok()
                    .entity(cashDeskService.findByCashier(username))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}