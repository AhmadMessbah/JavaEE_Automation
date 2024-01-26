package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Organisation;
import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.FinancialDocType;
import com.mftplus.automation.service.OrganisationService;
import com.mftplus.automation.service.SectionService;
import com.mftplus.automation.service.impl.SectionServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = "/section.do")
public class SectionServlet extends HttpServlet {

    @PersistenceContext(unitName = "")

    @Inject
    private SectionServiceImpl service;
    @Inject
    private Section section;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getRequestDispatcher("/jsp/section.jsp").forward(req,resp);
//            req.getSession().setAttribute("org", OrganisationService);
            req.getSession().setAttribute("sectionList", service.findAll());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String duty=req.getParameter("duty");
        String title=req.getParameter("title");
        String phoneNumber=req.getParameter("phoneNumber");

        req.getRequestDispatcher("/section.jsp");

        try {
            section=Section.builder()
                    .duty(duty).title(title).phoneNumber(phoneNumber).build();

            service.save(section);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
