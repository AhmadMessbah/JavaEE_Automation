package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.StuffStorage;
import com.mftplus.automation.service.impl.StuffStorageServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;


@Slf4j
@WebServlet(name = "StuffStorageServlet", urlPatterns = "/stuffStorage.do")
public class StuffStorageServlet extends HttpServlet {

    @Inject
    private StuffStorageServiceImpl stuffStorageService;
    //--------------------------------------------------------------------------------------------------------------//
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            StuffStorage stuffStorage = StuffStorage
                    .builder()
                    .name(req.getParameter("name"))
                    .count(Integer.parseInt(req.getParameter("count")))
                    .build();
            stuffStorageService.save(stuffStorage);
            log.info("Stuff Storage Servlet - Post");
            req.getRequestDispatcher("/jsp/stuffStorage.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    //--------------------------------------------------------------------------------------------------------------//
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            log.info("Stuff Storage Servlet - Put");
        } catch (Exception e) {
            log.error("Stuff In Storage Not Found");
        }
    }
    //--------------------------------------------------------------------------------------------------------------//
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("StuffStorage",stuffStorageService.findAll());
            req.getRequestDispatcher("/jsp/stuffStorage.jsp").forward(req, resp);
            log.info("Stuff Storage - Servlet - Get");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
//--------------------------------------------------------------------------------------------------------------//
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            log.info("Stuff Storage Servlet - delete");
        } catch (Exception e) {
            log.error("Stuff In Storage Not Found");
        }
    }
}
//--------------------------------------------------------------------------------------------------------------//