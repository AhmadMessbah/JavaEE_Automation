package com.mftplus.automation.controller.api;

import com.mftplus.automation.model.CheckPayment;
import com.mftplus.automation.service.impl.CheckPaymentServiceImp;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Path("/checkTransaction")
public class CheckPaymentApi {

    @Inject
    private CheckPaymentServiceImp checkPaymentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("Find All Check Paymen");
            return Response
                    .ok()
                    .entity(checkPaymentService.findAll())
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
            log.info("Find By Id Check Paymen");
            return Response
                    .ok()
                    .entity(checkPaymentService.findById(id))
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
    public Response save(CheckPayment checkPayment) {
        try {
            log.info("Save Check Paymen");
            checkPaymentService.save(checkPayment);
            return Response
                    .ok()
                    .entity(checkPayment)
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
    public Response edit(CheckPayment checkPayment) {
        try {
            log.info("Edit Check Paymen");
            checkPaymentService.edit(checkPayment);
            return Response
                    .ok()
                    .entity(checkPayment)
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
            log.info("Remove By Id Check Paymen");
            checkPaymentService.removeById(id);
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

    @DELETE
    @Path("/{checkNumber}")
    public Response removeByCheckNumber(@PathParam("checkNumber") String checkNumber) {
        try {
            log.info("Remove By CheckNumber Check Paymen");
            checkPaymentService.removeByCheckNumber(checkNumber);
            return Response
                    .ok()
                    .entity(checkNumber)
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{checkNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCheckNumber(@PathParam("checkNumber") String checkNumber) {
        try {
            log.info("Find By Check Number Check Paymen");
            return Response
                    .ok()
                    .entity(checkPaymentService.findByCheckNumber(checkNumber))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{checkDueDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCheckDueDate(@PathParam("checkDueDate") String checkDueDate) {
        try {
            LocalDateTime dateTimePars = LocalDateTime.parse(checkDueDate);
            log.info("Find By CheckDueDate Check Paymen");
            return Response
                    .ok()
                    .entity(checkPaymentService.findByCheckDueDate(dateTimePars))
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
            log.info("Find By CashDeskNumber Check Paymen");
            return Response
                    .ok()
                    .entity(checkPaymentService.findByCashDeskNumber(cashDeskNumber))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
