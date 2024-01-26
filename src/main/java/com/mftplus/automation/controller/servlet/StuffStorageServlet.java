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
@WebServlet(name = "StuffStorageServlet", urlPatterns = "/StuffStorage.do")
public class StuffStorageServlet extends HttpServlet {

    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Inject
    private StuffStorageServiceImpl stuffStorageService;


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
            req.getRequestDispatcher("/StuffStorage.jsp").forward(req, resp);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Storage Servlet - Put");
        super.doPut(req, resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Storage Servlet - Get");
        try {
            req.getRequestDispatcher("/StuffStorage.jsp").forward(req,resp);
        }
        catch (Exception e){
            log.error("Stuff In Storage Not Found");
            System.out.println(e.getMessage());

        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Storage Servlet - delete");
        super.doDelete(req, resp);
    }
}