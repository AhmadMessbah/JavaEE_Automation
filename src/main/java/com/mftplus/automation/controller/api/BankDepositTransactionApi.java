package com.mftplus.automation.controller.api;

import com.mftplus.automation.model.BankDepositTransaction;
import com.mftplus.automation.service.impl.BankDepositTransactionServiceImp;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/bankDepositTransaction")
public class BankDepositTransactionApi {
    @PersistenceContext(unitName = "automation")

    @Inject
    private BankDepositTransactionServiceImp bankDepositTransactionService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("Find All Bank Deposit Transaction Deposit Transaction");
            return Response
                    .ok()
                    .entity(bankDepositTransactionService.findAll())
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
            log.info("Find By Id Bank Deposit Transaction Deposit Transaction");
            return Response
                    .ok()
                    .entity(bankDepositTransactionService.findById(id))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{depositCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDepositCode(@PathParam("depositCode") String depositCode) {
        try {
            log.info("Find By Id Bank Deposit Transaction Deposit Code");
            return Response
                    .ok()
                    .entity(bankDepositTransactionService.findByDepositCode(depositCode))
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
    public Response findByBankInvolved(@PathParam("accountNumber") String accountNumber) {
        try {
            log.info("Find By Id Bank Deposit Transaction Bank Involved");
            return Response
                    .ok()
                    .entity(bankDepositTransactionService.findByBankInvolved(accountNumber))
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
    public Response save(BankDepositTransaction bankDepositTransaction) {
        try {
            log.info("Save Bank Deposit Transaction");
            bankDepositTransactionService.save(bankDepositTransaction);
            return Response
                    .ok()
                    .entity(bankDepositTransaction)
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
    public Response edit(BankDepositTransaction bankDepositTransaction) {
        try {
            log.info("Edit Bank Deposit Transaction");
            bankDepositTransactionService.edit(bankDepositTransaction);
            return Response
                    .ok()
                    .entity(bankDepositTransaction)
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
            log.info("Remove By Id Bank Deposit Transaction");
            bankDepositTransactionService.removeById(id);
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
}
