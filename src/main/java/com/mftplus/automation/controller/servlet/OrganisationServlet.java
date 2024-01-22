package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Organisation;
import com.mftplus.automation.model.Section;
import com.mftplus.automation.service.impl.OrganisationService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/organisation.do")
public class OrganisationServlet extends HttpServlet {

    @PersistenceContext(unitName = "jk")
    private EntityManager entityManager;

    @Inject
    private OrganisationService service;
    @Inject
    private Section section;
    @Inject
    private Organisation organisation;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/organisation.jsp");

        try {
            List<Section> sectionPart=section.getSectionsPart();

            String title=req.getParameter("o_title");
            String name=req.getParameter("o_name");
            String address=req.getParameter("o_address");
            String phoneNumber=req.getParameter("o_phoneNumber");
            String description=req.getParameter("o_description");

            organisation=Organisation.builder().name(name)
                    .title(title).address(address).phoneNumber(phoneNumber)
                    .description(description).sectionList(sectionPart).build();


            service.save(organisation);

            req.getSession().setAttribute("organisation",organisation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

