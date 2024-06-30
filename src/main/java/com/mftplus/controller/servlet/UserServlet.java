package com.mftplus.controller.servlet;

import com.mftplus.controller.validation.BeanValidator;
import com.mftplus.model.Roles;
import com.mftplus.model.User;
import com.mftplus.service.impl.RolesServiceImpl;
import com.mftplus.service.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "UserServlet" , urlPatterns = "/user.do")
public class UserServlet extends HttpServlet {

    @Inject
    private UserServiceImpl userService;
    @Inject
    private RolesServiceImpl rolesService;
    @Inject
    private User user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("UserServlet - Get");
        try {
            req.getSession().setAttribute("userList", rolesService.findAll());
            req.getRequestDispatcher("/jsp/form/save/user-form.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("User - Get : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("UserServlet - Post");
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            //build user
            user = User.builder()
                    .username(username)
                    .password(password)
                    .deleted(false)
                    .build();

            //validate user
            BeanValidator<User> validator = new BeanValidator<>();
            String validationResult = validator.validate(user).toString();
            if (validationResult != null) {
                resp.setStatus(400);
                resp.getWriter().write(validationResult);
                return;
            }

            //check for duplicate username
            if (userService.findByUsername(username).isEmpty()) {
                //save user
                userService.save(user);

                //build role for user
                Roles userRole = Roles.builder()
                        .user(user)
                        .role("user")
                        .deleted(false)
                        .build();
                if (rolesService.findByUsernameAndRoleName(user.getUsername(), "user").isEmpty()) {
                    rolesService.save(userRole);
                    log.info("New user role saved");
                }

                req.getSession().removeAttribute("duplicateUsername");
                resp.sendRedirect("/user.do");

            } else {
                String errorMessage = "نام کاربری تکراری است!";
                req.getSession().setAttribute("duplicateUsername", errorMessage);
                resp.sendRedirect("/user.do");
            }
        } catch (Exception e) {
            log.error("User - POST : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}