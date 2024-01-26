package com.mftplus.automation.controller.api;

import com.mftplus.automation.model.Reference;
import com.mftplus.automation.model.enums.ReferencePriority;
import com.mftplus.automation.service.impl.ReferenceServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Path("/reference")
public class ReferenceApi {
    @Inject
    private ReferenceServiceImpl referenceService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws Exception{
        try {
            return Response.ok().entity(referenceService.findAll()).build();
        } catch (Exception e) {
            return Response.status(500).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) throws Exception{
        try {
            return Response.ok().entity(referenceService.findById(id)).build();
        } catch (Exception e) {
            return Response.status(204).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Path("/{referenceSenderOrReceiver}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByReferenceSenderOrReceiver(@PathParam("referenceSenderOrReceiver") Long id) throws Exception{
        try {
            log.info("FindByReferenceSenderOrReceiver");
            return Response
                    .ok()
                    .entity(referenceService.findByReferenceSenderOrReceiver(id))
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
    @Path("/{refDate}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRefDate(@PathParam("refDate") String refDateAndTime) throws Exception{
        try {
            log.info("FindByRefDate");
            return Response
                    .ok()
                    .entity(referenceService.findByRefDate(LocalDateTime.parse(refDateAndTime)))
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
    @Path("/{letterId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByLetterId(@PathParam("letterId") Long letterId) throws Exception{
        try {
            log.info("FindByLetterId");
            return Response
                    .ok()
                    .entity(referenceService.findByLetterId(letterId))
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
    @Path("/{validate}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByValidate(@PathParam("validate") Boolean validate) throws Exception{
        try {
            log.info("FindByValidate");
            return Response
                    .ok()
                    .entity(referenceService.findByValidate(validate))
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
    @Path("/{paraph}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByParaph(@PathParam("paraph") String paraph) throws Exception{
        try {
            log.info("FindByParaph");
            return Response
                    .ok()
                    .entity(referenceService.findByParaph(paraph))
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
    @Path("/{priority}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPriority(@PathParam("priority") ReferencePriority priority) throws Exception{
        try {
            log.info("FindByPriority");
            return Response
                    .ok()
                    .entity(referenceService.findByPriority(priority))
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
    @Path("/{status}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByStatus(@PathParam("status") Boolean status) throws Exception{
        try {
            log.info("FindByStatus");
            return Response
                    .ok()
                    .entity(referenceService.findByStatus(status))
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Reference reference) throws Exception{
        try {
            referenceService.save(reference);
            return Response.ok().entity(reference).build();
        } catch (Exception e) {
            return Response.status(500).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Reference reference) throws Exception{
        try {
            referenceService.edit(reference);
            return Response.ok().entity(reference).build();
        } catch (Exception e) {
            return Response.status(500).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeById(@PathParam("id") Long id) throws Exception{
        log.info("Reference Delete api : " + id);
        try {
            referenceService.removeById(id);
            return Response.ok().entity(id).build();
        } catch (Exception e) {
            return Response.status(204).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

}
