package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Section;
import com.mftplus.automation.service.impl.OrganisationServiceImpl;
import com.mftplus.automation.service.impl.SectionServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet(urlPatterns = "/section.do")
@Slf4j
public class SectionServlet extends HttpServlet {
    @Inject
    private SectionServiceImpl service;
    @Inject
    private OrganisationServiceImpl organisationService;
    @Inject
    private Section section;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            /*req.getSession().setAttribute("org", organisationService.findByName());*/
            req.getSession().setAttribute("sectionList", service.findAll());
            req.getRequestDispatcher("/jsp/section.jsp").forward(req,resp);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title=req.getParameter("title");
        String duty=req.getParameter("duty");
        String phoneNumber=req.getParameter("phoneNumber");

        req.getRequestDispatcher("/jsp/section.jsp");

        try {
            section=Section.builder()
                    .duty(duty)
                    .title(title)
                    .phoneNumber(phoneNumber).build();


            service.save(section);
            log.info("Section Save");
            resp.sendRedirect("/section.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
