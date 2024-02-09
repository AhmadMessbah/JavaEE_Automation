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

@Slf4j
@WebServlet(name = "UserServlet" , urlPatterns = "/user.do")

public class UserServlet extends HttpServlet {

    @Inject
    private UserServiceImpl userService;
    @Inject
    private User user;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String role = req.getParameter("role");

           user = User
                   .builder()
                   .username(username)
                   .password(password)
                   .role(Role.valueOf(role))
                   .deleted(false)
                   .build();


            userService.save(user);
            log.info("User Saved");
            resp.sendRedirect("/user.do");
        }catch (Exception e){
            log.info("User - POST : " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("roles", Arrays.asList(Role.values()));
            req.getSession().setAttribute("userList", userService.findAll());
            req.getRequestDispatcher("/jsp/user.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("User - Get : " + e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        int id = Integer.parseInt(userId);

         try{
             userService.removeById((long) id);
         }catch (Exception e){
             System.out.println(e.getMessage());
             log.info("UserServlet - Error Delete User By Id");
             req.getSession().setAttribute("error", e.getMessage());
             req.getRequestDispatcher("/jsp/user.jsp").forward(req, resp);
         }

    }
}
