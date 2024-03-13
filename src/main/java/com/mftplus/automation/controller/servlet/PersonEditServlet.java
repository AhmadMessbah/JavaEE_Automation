package com.mftplus.automation.controller.servlet;


import com.mftplus.automation.model.Person;
import com.mftplus.automation.model.enums.Gender;
import com.mftplus.automation.service.impl.PersonServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/personEdit.do")
public class PersonEditServlet extends HttpServlet {
    @Inject
    private PersonServiceImpl personService;
    @Inject
    private Person person;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("PersonEditServlet - Get");
        try {
            if (req.getParameter("id") == null) {
                resp.sendRedirect("/person.do");
            } else {
                long id = Long.parseLong(req.getParameter("id"));
                Optional<Person> person = personService.findById(id);
                if (person.isPresent()) {
                    req.getSession().setAttribute("person", person.get());
                }else {
                    log.error("person is not available");
                }

                req.getSession().setAttribute("gender", Arrays.asList(Gender.values()));
                req.getRequestDispatcher("/jsp/person-edit.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("PersonEditServlet - Put");
        try {
            long id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String family = req.getParameter("family");
            String nationalCode = req.getParameter("nationalCode");
            String gender = req.getParameter("gender");

            person = Person.
                    builder().
                    id(id).
                    name(name).
                    family(family).
                    nationalCode(nationalCode).
                    gender(Gender.valueOf(gender)).
                    deleted(false).
                    build();

            personService.edit(person);
            log.info("PersonEditServlet - Person Edited");
            resp.sendRedirect("/person.do");

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
