package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Stuff;
import com.mftplus.automation.service.impl.SectionServiceImpl;
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
    @Inject
    private Stuff stuff;

    @Inject
    private SectionServiceImpl sectionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("StuffList",stuffService.findAll());
            req.getSession().setAttribute("sectionList",sectionService.findAll());

            req.getRequestDispatcher("/jsp/stuff.jsp").forward(req, resp);
            log.info("Stuff - Servlet-Get");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }




//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String stuffId=req.getParameter("id");
//        int id=Integer.parseInt(stuffId);
//        try {
//
//            stuffService.findById((long) id);
//             stuffService.edit(Stuff
//                    .builder()
//                    .name(req.getParameter("name"))
//                    .brand(req.getParameter("brand"))
//                    .model(req.getParameter("model"))
//                    .price(Long.valueOf(req.getParameter("price")))
//                    .build());
//            stuffService.edit(stuff);
//            log.info("StuffServlet - Stuff Edit");
//            req.getRequestDispatcher("/stuff.jsp").forward(req, resp);
//
//            log.info("StuffServlet - Put");
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String name=req.getParameter("name");
//        String brand=req.getParameter("brand");
//        String model=req.getParameter("model");
//        long price= Long.parseLong(req.getParameter("price"));
//        try {
//            Stuff stuff = Stuff
//                    .builder()
//                    .name(name)
//                    .brand(brand)
//                    .model(model)
//                    .price(price)
//                    .section(sectionService.findByTitle(req.getParameter("section")).get(   ))
//                    .build();
//            System.out.println("Stuff Post");
//            stuffService.save(stuff);
//            log.info("StuffServlet - Stuff Saved");
//            req.getRequestDispatcher("/stuff.jsp").forward(req, resp);
//        } catch (Exception e) {
//            log.info("StuffServlet - Error Save Stuff : " + e.getMessage());
//            req.getSession().setAttribute("error", e.getMessage());
//            req.getRequestDispatcher("/jsp/stuff.jsp").forward(req, resp);
//        }
//    }



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

             Stuff stuff = Stuff
                    .builder()
                    .name(req.getParameter("name"))
                    .brand(req.getParameter("brand"))
                    .model(req.getParameter("model"))
                    .price(Long.valueOf(req.getParameter("price")))
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
        String stuffId=req.getParameter("id");
        int id=Integer.parseInt(stuffId);
        try {
            stuffService.removeById((long) id);
            log.info("StuffServlet - Delete");
        } catch (Exception e) {
            log.error(e.getMessage());
            req.getSession().setAttribute("error",e.getMessage());
            req.getRequestDispatcher("/jsp/stuff.jsp").forward(req,resp);
        }
    }
}


