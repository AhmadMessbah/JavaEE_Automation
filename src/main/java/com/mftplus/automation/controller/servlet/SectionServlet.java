package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Organisation;
import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.FinancialDocType;
import com.mftplus.automation.service.OrganisationService;
import com.mftplus.automation.service.SectionService;
import com.mftplus.automation.service.impl.OrganisationServiceImpl;
import com.mftplus.automation.service.impl.SectionServiceImpl;
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
import java.util.Arrays;
import java.util.List;

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
