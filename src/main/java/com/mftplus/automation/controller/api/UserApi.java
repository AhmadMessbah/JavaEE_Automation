package com.mftplus.automation.controller.api;

import com.mftplus.automation.model.User;
import com.mftplus.automation.service.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Path("/user")
public class UserApi {
    @Inject
    private UserServiceImpl userService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(User user) throws Exception {
        try {
            userService.save(user);
            return Response.ok().entity(user).build();
        } catch (Exception e) {
            return Response.status(500).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws Exception {
        try {
            return Response.ok().entity(userService.findAll()).build();
        } catch (Exception e) {
            return Response.status(500).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

//    @GET
//    @Path("/{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response findById(@PathParam("id") Long id) throws Exception {
//        try {
//            return Response.ok().entity(userService.findById(id)).build();
//        } catch (Exception e) {
//            return Response.status(204).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
//        }
//    }

    @GET
    @Path("/findByUsername/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByUsername(@PathParam("username") String name) {
        try {
            return Response.ok().entity(userService.findByUsername(String.valueOf(name))).build();
        } catch (Exception e) {
            return Response.status(204).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response edit(User user) throws Exception {
        try {
            userService.edit(user);
            return Response.ok().entity(user).build();
        } catch (Exception e) {
            return Response.status(500).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }

    @DELETE
    @Path("/{username}")
    public Response remove(@PathParam("username") String username) {
        log.info("User Delete api : " + username);
        try {
            userService.removeByUsername(username);
            return Response.ok().entity(username).build();
        } catch (Exception e) {
            return Response.status(204).entity("{\"message\": \"" + e.getMessage() + "\"}").build();
        }
    }
}

