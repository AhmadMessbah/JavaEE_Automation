package com.mftplus.automation.controller.api;

import com.github.mfathi91.time.PersianDate;
import com.mftplus.automation.model.Letter;
import com.mftplus.automation.service.impl.LetterServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Path("/letter")
public class LetterApi {
    @Inject
    private LetterServiceImpl letterService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Letter letter) throws Exception{
        try {
            letterService.save(letter);
            return Response.ok().entity(letter).build();
        } catch (Exception e) {
            return Response.status(500).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(Letter letter) throws Exception{
        try {
            letterService.edit(letter);
            return Response.ok().entity(letter).build();
        } catch (Exception e) {
            return Response.status(500).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeById(@PathParam("id") Long id) throws Exception{
        log.info("Letter Delete api : " + id);
        try {
            letterService.removeById(id);
            return Response.ok().entity(id).build();
        } catch (Exception e) {
            return Response.status(204).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            return Response.ok().entity(letterService.findAll()).build();
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
            return Response.ok().entity(letterService.findById(id)).build();
        } catch (Exception e) {
            return Response.status(204).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Path("/{title}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByTitle(@PathParam("title") String title) throws Exception{
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByContext(@PathParam("context") String context) throws Exception{
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByDate(@PathParam("date") String faDate) throws Exception{
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRegisterNumber(@PathParam("registerNumber") Long registerNumber) throws Exception{
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByRegisterDate(@PathParam("registerDate") String dateTime) throws Exception{
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findBySenderNameAndTitle(@PathParam("senderNameAndTitle") String senderName,String senderTitle) throws Exception{
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findBySectionId(@PathParam("sectionId") Long sectionId) throws Exception{
        try {
            return Response.ok().entity(letterService.findById(sectionId)).build();
        } catch (Exception e) {
            return Response.status(204).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }


}
