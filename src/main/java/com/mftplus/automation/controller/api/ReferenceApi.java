package com.mftplus.automation.controller.api;

import com.mftplus.automation.model.Reference;
import com.mftplus.automation.model.enums.ReferencePriority;
import com.mftplus.automation.service.impl.ReferenceServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Path("/reference")
public class ReferenceApi {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Inject
    private ReferenceServiceImpl referenceService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("FindAllReference");
            return Response
                    .ok()
                    .entity(referenceService.findAll())
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
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Long id) {
        try {
            log.info("FindByIdReference");
            return Response
                    .ok()
                    .entity(referenceService.findById(id))
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
    @Path("/{referenceSenderOrReceiver}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByReferenceSenderOrReceiver(@PathParam("referenceSenderOrReceiver") Long id) {
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRefDate(@PathParam("refDate") String refDateAndTime) {
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByLetterId(@PathParam("letterId") Long letterId) {
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByValidate(@PathParam("validate") Boolean validate) {
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByParaph(@PathParam("paraph") String paraph) {
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPriority(@PathParam("priority") ReferencePriority priority) {
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByStatus(@PathParam("status") Boolean status) {
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
    public Response save(Reference reference) {
        try {
            log.info("Save Reference");
            referenceService.save(reference);
            return Response
                    .ok()
                    .entity(reference)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Reference reference) {
        try {
            log.info("Edit Reference");
            referenceService.edit(reference);
            return Response
                    .ok()
                    .entity(reference)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response removeById(@PathParam("id") Long id) {
        try {
            log.info("RemoveById Reference");
            referenceService.removeById(id);
            return Response
                    .ok()
                    .entity(id)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Response
                    .serverError()
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(Reference reference) {
        try {
            log.info("Remove Reference");
            referenceService.remove(reference);
            return Response
                    .ok()
                    .entity(reference)
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
