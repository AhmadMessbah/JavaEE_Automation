package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.CompositeKey;
import com.mftplus.automation.model.Roles;
import com.mftplus.automation.model.User;
import com.mftplus.automation.service.impl.RolesServiceImpl;
import com.mftplus.automation.service.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class InitialServlet extends HttpServlet {
    @Inject
    private UserServiceImpl userService;
    @Inject
    private RolesServiceImpl rolesService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            log.info("InitialServlet - Get");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

   @Override
    public void init() throws ServletException {
        log.info("InitialServlet - Init");
        super.init();
//creating admin-admin123 and user-user123
        try {
            User user =
                    User
                            .builder()
                            .username("admin")
                            .password("admin123")
                            .deleted(false)
                            .build();
            if (userService.findByUsername("admin").isEmpty()){
            userService.save(user);
            log.info("admin user saved");
            }
            CompositeKey compositeKey =
                    CompositeKey
                            .builder()
                            .roleName("admin")
                            .user(user)
                            .build();
            Roles role =
                    Roles
                            .builder()
                            .compositeKey(compositeKey)
                            .deleted(false)
                            .build();
            if (rolesService.findById(compositeKey).isEmpty()){
               rolesService.save(role);
               log.info("admin role saved");
            }

            User user1 =
                    User
                            .builder()
                            .username("user")
                            .password("user123")
                            .deleted(false)
                            .build();
            if (userService.findByUsername("user").isEmpty()){
                userService.save(user1);
                log.info("user saved");
            }
            CompositeKey compositeKey1 =
                    CompositeKey
                            .builder()
                            .roleName("user")
                            .user(user1)
                            .build();
            Roles role1 =
                    Roles
                            .builder()
                            .compositeKey(compositeKey1)
                            .deleted(false)
                            .build();
            if (rolesService.findById(compositeKey1).isEmpty()){
                rolesService.save(role1);
                log.info("user role saved");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
