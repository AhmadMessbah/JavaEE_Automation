package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Stuff;
import com.mftplus.automation.service.impl.StuffServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@WebServlet(name = "stuff", urlPatterns = "/stuff.do")
public class StuffServlet extends HttpServlet {
    @Inject
    private StuffServiceImpl stuffService;
    //--------------------------------------------------------------------------------------------------------------//
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Stuff stuff = Stuff
                    .builder()
                    .name(req.getParameter("name"))
                    .brand(req.getParameter("brand"))
                    .model(req.getParameter("model"))
                    .price(Long.valueOf(req.getParameter("price")))
                    .build();
            stuffService.save(stuff);
            log.info("StuffServlet - Stuff Saved");
            req.getRequestDispatcher("/stuff.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("StuffServlet - Error Save Stuff");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/stuff.jsp").forward(req, resp);
        }
    }
    //--------------------------------------------------------------------------------------------------------------//
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            log.info("StuffServlet - Put");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    //--------------------------------------------------------------------------------------------------------------//
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("StuffList",stuffService.findAll());
            req.getRequestDispatcher("/jsp/stuff.jsp").forward(req, resp);
            log.info("Stuff - Servlet-Get");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    //--------------------------------------------------------------------------------------------------------------//
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            log.info("StuffServlet - Delete");
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }
}
//--------------------------------------------------------------------------------------------------------------//