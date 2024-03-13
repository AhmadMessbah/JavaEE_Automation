package com.mftplus.automation.controller.servlet;


import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.Role;
import com.mftplus.automation.service.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/userEdit.do")
public class UserEditServlet extends HttpServlet {
    @Inject
    private UserServiceImpl userService;
    @Inject
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("UserEditServlet - Get");
        try {
            if (req.getParameter("username") == null) {
                resp.sendRedirect("/user.do");
            } else {
                String username = req.getParameter(user.getUsername());
                Optional<User> user = userService.findByUsername(username);
                if (user.isPresent()) {
                    req.getSession().setAttribute("user", user.get());
                }else {
                    log.error("user is not available");
                }

                req.getSession().setAttribute("role", Arrays.asList(Role.values()));
                req.getRequestDispatcher("/jsp/user-edit.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("UserEditServlet - Put");
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String role = req.getParameter("role");

            user = User.
                    builder().
                    username(username).
                    password(password).
                    role(Role.valueOf(role)).
                    deleted(false).
                    build();

            userService.edit(user);
            log.info("UserEditServlet - User Edited");
            resp.sendRedirect("/user.do");

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
