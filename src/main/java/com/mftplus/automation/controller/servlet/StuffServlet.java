package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Stuff;
import com.mftplus.automation.service.impl.SectionServiceImpl;
import com.mftplus.automation.service.impl.StuffServiceImpl;
import com.mftplus.automation.service.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet(name = "stuff", urlPatterns = "/stuff.do")
@Slf4j
public class StuffServlet extends HttpServlet {
    @Inject
    private StuffServiceImpl stuffService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private SectionServiceImpl sectionService;
    
    @Inject
    private Stuff stuff;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("StuffList", stuffService.findAll());
            req.getSession().setAttribute("sectionList", sectionService.findAll());

            req.getRequestDispatcher("/jsp/stuff.jsp").forward(req, resp);
            log.info("Stuff - Servlet-Get");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        String price = req.getParameter("price");
        String status = req.getParameter("status");
        req.getRequestDispatcher("jsp/stuff.jsp");
        try {
//        String fileName = null;
//        Part filePart = req.getPart("file");
//        if (filePart.getSize()>0) {
//            fileName = filePart.getSubmittedFileName();
//            for (Part part : req.getParts()) {
//                part.write(fileName);
//            }
//            resp.getWriter().print("The file uploaded successfully.");
//        }

             stuff = Stuff
                    .builder()
                    .section(sectionService.findByTitle(req.getParameter("section")).get())
                    .name(name)
                    .brand(brand)
                    .price(price)
                    .model(model)
                    .status(status)
                    .deleted(false)
                    .build();
            stuffService.save(stuff);
            System.out.println(stuff);
            log.info("StuffServlet - Stuff Saved");
            resp.sendRedirect("/stuff.do");
        } catch (Exception e) {
            log.info("StuffServlet - Error Save Stuff : " + e.getMessage());
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/stuff.jsp").forward(req, resp);
        }
    }





    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            Stuff stuff = Stuff
                    .builder()
                    .name(req.getParameter("name"))
                    .brand(req.getParameter("brand"))
                    .model(req.getParameter("model"))
                    .price(req.getParameter("price"))
                    .build();
            stuffService.edit(stuff);
            log.info("StuffServlet - Stuff Edit");
            req.getRequestDispatcher("/jsp/stuff.jsp").forward(req, resp);

            log.info("StuffServlet - Put");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuffId = req.getParameter("id");
        int id = Integer.parseInt(stuffId);
        try {
            stuffService.removeById((long) id);
            log.info("StuffServlet - Delete");
        } catch (Exception e) {
            log.error(e.getMessage());
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/stuff.jsp").forward(req, resp);
        }
    }
}


