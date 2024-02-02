package com.mftplus.automation.controller.api;

import com.mftplus.automation.model.Bank;
import com.mftplus.automation.service.impl.BankServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/bank")
public class BankApi {

    @Inject
    private BankServiceImpl  bankService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("Find All Bank");
            return Response
                    .ok()
                    .entity(bankService.findAll())
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
            log.info("Find By Id Bank");
            return Response
                    .ok()
                    .entity(bankService.findById(id))
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
    public Response save(Bank bank) {
        try {
            log.info("Save Bank");
            bankService.save(bank);
            return Response
                    .ok()
                    .entity(bank)
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
    public Response edit(Bank bank) {
        try {
            log.info("Edit Bank");
            bankService.edit(bank);
            return Response
                    .ok()
                    .entity(bank)
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
            log.info("Remove By Id Bank");
            bankService.removeById(id);
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
    @Path("/{accountNumber}")
    public Response removeByAccountNumber(@PathParam("accountNumber") String accountNumber) {
        try {
            log.info("Remove By Account Number Bank");
            bankService.removeByAccountNumber(accountNumber);
            return Response.
                    ok()
                    .entity(accountNumber)
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
            log.info("Find By Name Bank");
            return Response
                    .ok()
                    .entity(bankService.findByName(name))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{branchCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByBranchCode(@PathParam("branchCode") int branchCode) {
        try {
            log.info("Find By Branch Code Bank");
            return Response
                    .ok()
                    .entity(bankService.findByBranchCode(branchCode))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{branchName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByBranchName(@PathParam("branchName") String branchName) {
        try {
            log.info("Find By Branch Name Bank");
            return Response
                    .ok()
                    .entity(bankService.findByBranchName(branchName))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{accountType}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByAccountType(@PathParam("accountType") String accountType) {
        try {
            log.info("Find By Account Type Bank");
            return Response
                    .ok()
                    .entity(bankService.findByAccountType(accountType))
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
    public Response findByAccountOwner(@PathParam("username") String username) {
        try {
            log.info("Find By Account Owner Bank");
            return Response
                    .ok()
                    .entity(bankService.findByAccountOwner(username))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByAccountNumber(@PathParam("accountNumber") String accountNumber) {
        try {
            log.info("Find By Account Number Bank");
            return Response
                    .ok()
                    .entity(bankService.findByAccountNumber(accountNumber))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
