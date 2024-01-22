package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Organisation;
import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.User;
import com.mftplus.automation.service.impl.SectionService;
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

@WebServlet(urlPatterns = "/section.do")
public class SectionServlet extends HttpServlet {

    @PersistenceContext(unitName = "")
/*    private EntityManager entityManager;*/

    @Inject
    private SectionService service;
    @Inject
    private Section section;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            service.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/section.jsp");

        try {
            List<Section> sectionPart=section.getSectionsPart();
            List<User>userList=section.getUsers();

            String title=req.getParameter("title");
            String duty=req.getParameter("duty");
            Organisation organisation=new Organisation();

            section=Section.builder().sectionsPart(sectionPart)
                    .organisation(organisation).users(userList)
                    .duty(duty).title(title).build();

            service.save(section);

            req.getSession().setAttribute("section",section);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
