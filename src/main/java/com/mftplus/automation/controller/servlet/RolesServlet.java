package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.CompositeKey;
import com.mftplus.automation.model.Roles;
import com.mftplus.automation.model.User;
import com.mftplus.automation.service.impl.RolesServiceImpl;
import com.mftplus.automation.service.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/roles.do")
public class RolesServlet extends HttpServlet {
    @Inject
    private RolesServiceImpl rolesService;
    @Inject
    private UserServiceImpl userService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("RolesServlet - Get");

        try {
            //todo
//            req.getSession().setAttribute("roleTypes", Arrays.asList(Role.values()));
            req.getSession().setAttribute("rolesList", rolesService.findAll());
            req.getRequestDispatcher("/jsp/roles.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("RolesServlet - Post");
        try {
            String roleName = req.getParameter("roleName");
            String username = req.getParameter("username");

            if (username != null){
                Optional<User> user = userService.findByUsername(username);
                System.out.println(user);

                if (user.isPresent()){
                    CompositeKey compositeKey =
                            CompositeKey
                                    .builder()
                                    .roleName(roleName)
                                    .user(user.get())
                                    .build();
                    System.out.println(compositeKey);
                    Roles role =
                            Roles
                                    .builder()
                                    .compositeKey(compositeKey)
                                    .build();
                    System.out.println(role);
                    rolesService.save(role);
                    log.info("RolesServlet - role saved");
                    resp.sendRedirect("/roles.do");
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
