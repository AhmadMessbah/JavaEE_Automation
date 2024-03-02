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

@Slf4j
@WebServlet(urlPatterns = "/person.do")
//@MultipartConfig(
//        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
//        maxFileSize = 1024 * 1024 * 10,      // 10 MB
//        maxRequestSize = 1024 * 1024 * 100   // 100 MB
//)
public class PersonServlet extends HttpServlet {
    @Inject
    private PersonServiceImpl personService;

    @Inject
    private Person person;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("genders", Arrays.asList(Gender.values()));
            req.getSession().setAttribute("personList", personService.findAll());
            req.getRequestDispatcher("/jsp/person.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("Person - GET : " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Part filePart = req.getPart("file");
//        String fileName = filePart.getSubmittedFileName();  // todo : attach_id
//        for (Part part : req.getParts()) {
//            part.write("c:\\root\\"+fileName);  // todo : set server path
//        }

        try {
            String name = req.getParameter("name");
            String family = req.getParameter("family");
            String nationalCode = req.getParameter("nationalCode");
            String gender = req.getParameter("gender");

            person = Person
                    .builder()
                    .name(name)
                    .family(family)
                    .nationalCode(nationalCode)
                    .gender(Gender.valueOf(gender))
                    .deleted(false)
                    .build();

            personService.save(person);
            log.info("Person Saved");
            resp.sendRedirect("/user.do");
        } catch (Exception e) {
            log.info("Person - POST : " + e.getMessage());
        }
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

