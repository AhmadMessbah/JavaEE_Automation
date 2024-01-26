package com.mftplus.automation.controller.api;

import com.mftplus.automation.model.FinancialDoc;
import com.mftplus.automation.service.impl.FinancialDocServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Path("/financialDoc")
public class FinancialDocServiceApi {
    @PersistenceContext(unitName = "automation")

    @Inject
    private FinancialDocServiceImpl financialDocService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(FinancialDoc financialDoc) {
        try {
            log.info("Save Financial Doc");
            financialDocService.save(financialDoc);
            return Response
                    .ok()
                    .entity(financialDoc)
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
    public Response edit(FinancialDoc financialDoc) {
        try {
            log.info("Edit Financial Doc");
            financialDocService.edit(financialDoc);
            return Response
                    .ok()
                    .entity(financialDoc)
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
            log.info("Remove By Id Financial Doc");
            financialDocService.removeById(id);
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
    @Path("/{docNumber}")
    public Response removeByDocNumber(@PathParam("docNumber") Long docNumber) {
        try {
            log.info("Remove By Doc Number Financial Doc");
            financialDocService.removeByDocNumber(docNumber);
            return Response
                    .ok()
                    .entity(docNumber)
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
            log.info("Find By Id Financial Doc");
            return Response
                    .ok()
                    .entity(financialDocService.findById(id))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{docNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDocNumber(@PathParam("docNumber") Long docNumber) {
        try {
            log.info("Find By Doc Number Financial Doc");
            return Response
                    .ok()
                    .entity(financialDocService.findByDocNumber(docNumber))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("Find All Financial Dod");
            return Response
                    .ok()
                    .entity(financialDocService.findAll())
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @GET
    @Path("/{dateTime}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDateTime(@PathParam("dateTime") String dateTime) {
        try {
            LocalDateTime dateTimePars = LocalDateTime.parse(dateTime);
            log.info("Find By DateTime Financial Doc");
            return Response
                    .ok()
                    .entity(financialDocService.findByDateTime(dateTimePars))
                    .build();
        } catch (Exception e) {
            return Response
                    .serverError()
                    .entity("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }
}
