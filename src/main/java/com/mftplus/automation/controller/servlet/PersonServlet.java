package com.mftplus.automation.controller.servlet;
import com.mftplus.automation.model.Person;
import com.mftplus.automation.model.enums.Gender;
import com.mftplus.automation.service.impl.PersonServiceImpl;
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
@WebServlet(urlPatterns = "/person.do")
public class PersonServlet extends HttpServlet {
    @PersistenceContext(unitName="automation")
    private EntityManager entityManager;
    @Inject
    private PersonServiceImpl personService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String family = req.getParameter("family");
        String gender = req.getParameter("gender");
        try {
            Person person = Person.builder().
                    name(name).
                    family(family).
                    gender(Gender.valueOf(gender)).
                    build();
            personService.save(person);
            log.info("UserServlet - Person Saved");
            req.getRequestDispatcher("/jsp/person.jsp").forward(req, resp);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.info("PersonServlet - Error Save Person");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/person.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            personService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String personId = req.getParameter("id");
        int id = Integer.parseInt(personId);

        try{ personService.removeById((long) id);

        }catch (Exception e){
            System.out.println(e.getMessage());
            log.info("PersonServlet - Error Delete Person By Id");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/person.jsp").forward(req, resp);
        }
    }
    }

