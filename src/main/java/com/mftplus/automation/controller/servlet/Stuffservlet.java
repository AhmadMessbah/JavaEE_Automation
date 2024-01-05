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
@WebServlet(name = "stuf",urlPatterns = "/stuff.do")
public class Stuffservlet extends HttpServlet {

    @Inject
    private StuffServiceImpl stuffService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/stuff.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("StuffServlet - Post");
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

        }catch(Exception e){
            log.info("StuffServlet - Error Save Stuff");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/attach.jsp").forward(req, resp);

        }
    }
}
