package com.mftplus.automation.controller.api;

import com.github.mfathi91.time.PersianDate;
import com.mftplus.automation.model.Letter;
import com.mftplus.automation.service.impl.LetterServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import jakarta.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Path("/letter")
public class LetterApi {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Inject
    private LetterServiceImpl letterService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            log.info("FindAllLetter");
            return Response
                    .ok()
                    .entity(letterService.findAll())
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
            log.info("FindByIdLetter");
            return Response
                    .ok()
                    .entity(letterService.findById(id))
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
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByTitle(@PathParam("title") String title) {
        try {
            log.info("FindByTitleLetter");
            return Response
                    .ok()
                    .entity(letterService.findByTitle(title))
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
    @Path("/{context}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByContext(@PathParam("context") String context) {
        try {
            log.info("FindByContextLetter");
            return Response
                    .ok()
                    .entity(letterService.findByContext(context))
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
    @Path("/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDate(@PathParam("date") String faDate) {
        try {
            LocalDate date = PersianDate.parse(faDate).toGregorian();
            log.info("FindByDateLetter");
            return Response
                    .ok()
                    .entity(letterService.findByDate(date))
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
    @Path("/{registerNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRegisterNumber(@PathParam("registerNumber") Long registerNumber) {
        try {
            log.info("FindByRegisterNumberLetter");
            return Response
                    .ok()
                    .entity(letterService.findByRegisterNumber(registerNumber))
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
    @Path("/{registerDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRegisterDate(@PathParam("registerDate") String dateTime) {
        try {
            log.info("FindByRegisterDateLetter");
            return Response
                    .ok()
                    .entity(letterService.findByRegisterDate(LocalDateTime.parse(dateTime)))
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
    @Path("/{senderNameAndTitle}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findBySenderNameAndTitle(@PathParam("senderNameAndTitle") String senderName,String senderTitle) {
        try {
            log.info("FindBySenderNameAndTitle");
            return Response
                    .ok()
                    .entity(letterService.findBySenderNameAndTitle(senderName,senderTitle))
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
    @Path("/{sectionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findBySectionId(@PathParam("sectionId") Long sectionId) {
        try {
            log.info("FindBySectionId");
            return Response
                    .ok()
                    .entity(letterService.findBySectionId(sectionId))
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
    public Response save(Letter letter) {
        try {
            log.info("Save Letter");
            letterService.save(letter);
            return Response
                    .ok()
                    .entity(letter)
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
    public Response edit(Letter letter) {
        try {
            log.info("Edit Letter");
            letterService.edit(letter);
            return Response
                    .ok()
                    .entity(letter)
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
            log.info("RemoveById Letter");
            letterService.removeById(id);
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
    public Response delete(Letter letter) {
        try {
            log.info("Remove Letter");
            letterService.remove(letter);
            return Response
                    .ok()
                    .entity(letter)
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
