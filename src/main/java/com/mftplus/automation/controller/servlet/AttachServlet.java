package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Attach;
import com.mftplus.automation.service.AttachServiceImpl;
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
@WebServlet(name = "attachServlet", urlPatterns = "/attach.do")
public class AttachServlet extends HttpServlet {
    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Inject
    private AttachServiceImpl attachService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("AttachServlet - GET");
        request.getRequestDispatcher("/jsp/attach.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("AttachServlet - Post");
        try {
            Attach attach =
                    Attach
                            .builder()
                            .title(request.getParameter("title"))
                            .build();
            attachService.save(attach);
            log.info("AttachServlet - Attach Saved");
            request.getRequestDispatcher("/jsp/attach.jsp").forward(request, response);
        } catch (Exception e) {
            log.info("AttachServlet - Error Save Attach");
            request.getSession().setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/jsp/attach.jsp").forward(request, response);
        }
    }
}
