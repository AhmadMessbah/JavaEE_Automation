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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(CashDesk cashDesk) throws Exception {
        try {
            cashDeskService.save(cashDesk);
            return Response
                    .ok()
                    .entity(cashDesk)
                    .build();
        } catch (Exception e) {
            log.error("Error saving CashDesk: {}", e.getMessage());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(CashDesk cashDesk) throws Exception {
        try {
            cashDeskService.edit(cashDesk);
            return Response
                    .ok()
                    .entity(cashDesk)
                    .build();
        } catch (Exception e) {
            log.error("Error editing CashDesk: {}", e.getMessage());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeById(@PathParam("id") Long id) throws Exception {
        log.info("Remove By Id Cash Desk: {}", id);
        try {
            cashDeskService.removeById(id);
            return Response
                    .ok()
                    .entity(id)
                    .build();
        } catch (Exception e) {
            log.error("Error removing CashDesk by Id: {}", e.getMessage());
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            return Response
                    .ok()
                    .entity(cashDeskService.findAll())
                    .build();
        } catch (Exception e) {
            log.error("Error finding all CashDesks: {}", e.getMessage());
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) throws Exception {
        try {
            return Response
                    .ok()
                    .entity(cashDeskService.findById(id))
                    .build();
        } catch (Exception e) {
            log.error("Error finding CashDesk by Id: {}", e.getMessage());
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/name/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByName(@PathParam("name") String name) throws Exception {
        try {
            log.info("Find By Name CashDesk");
            return Response
                    .ok()
                    .entity(cashDeskService.findByName(name))
                    .build();
        } catch (Exception e) {
            log.error("Error finding CashDesk by Name: {}", e.getMessage());
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/number/{cashDeskNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCashDeskNumber(@PathParam("cashDeskNumber") int cashDeskNumber) {
        try {
            log.info("Find By Cash Desk Number CashDesk");
            return Response
                    .ok()
                    .entity(cashDeskService.findByCashDeskNumber(cashDeskNumber))
                    .build();
        } catch (Exception e) {
            log.error("Error finding CashDesk by Number: {}", e.getMessage());
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/cashier/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCashier(@PathParam("username") String username) {
        try {
            log.info("Find By Cashier CashDesk");
            return Response
                    .ok()
                    .entity(cashDeskService.findByCashier(username))
                    .build();
        } catch (Exception e) {
            log.error("Error finding CashDesk by Cashier: {}", e.getMessage());
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
