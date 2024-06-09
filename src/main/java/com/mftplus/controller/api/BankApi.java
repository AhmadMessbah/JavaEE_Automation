package com.mftplus.controller.api;

import com.mftplus.model.Bank;
import com.mftplus.model.enums.AccountType;
import com.mftplus.service.impl.BankServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@Path("/bank")
public class BankApi {

    @Inject
    private BankServiceImpl bankService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Bank bank) throws Exception {
        try {
            bankService.save(bank);
            return Response
                    .ok()
                    .entity(bank)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(500)
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @PUT
    @Path("/edit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Bank bank) throws Exception {
        try {
            bankService.edit(bank);
            return Response
                    .ok()
                    .entity(bank)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(500)
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeById(@PathParam("id") Long id) throws Exception {
        log.info("Remove By Id Bank: " + id);
        try {
            bankService.removeById(id);
            return Response
                    .ok()
                    .entity(id)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(204)
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
                    .entity(bankService.findAll())
                    .build();
        } catch (Exception e) {
            return Response
                    .status(500)
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
                    .entity(bankService.findById(id))
                    .build();
        } catch (Exception e) {
            return Response
                    .status(204)
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
            log.info("Find By Name Bank");
            return Response
                    .ok()
                    .entity(bankService.findByName(name))
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
    @Path("/branchCode/{branchCode}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByBranchCode(@PathParam("branchCode") Long branchCode) {
        try {
            log.info("Find By Branch Code Bank");
            return Response
                    .ok()
                    .entity(bankService.findByBranchCode(branchCode))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/branchName/{branchName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByBranchName(@PathParam("branchName") String branchName){
        try {
            log.info("Find By Branch Name Bank");
            return Response
                    .ok()
                    .entity(bankService.findByBranchName(branchName))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/accountNumber/{accountNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByAccountNumber(@PathParam("accountNumber") String accountNumber) {
        try {
            log.info("Find By Account Number Bank");
            return Response
                    .ok()
                    .entity(bankService.findByAccountNumber(accountNumber))
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
