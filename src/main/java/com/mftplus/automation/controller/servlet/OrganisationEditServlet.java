package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Organisation;
import com.mftplus.automation.service.impl.OrganisationServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/organisation-edit.do")
public class OrganisationEditServlet extends HttpServlet {
    @Inject
    private OrganisationServiceImpl organisationService;
    @Inject
    private Organisation organisation;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("organisationEditServlet -> Get");
        try {
            if (req.getParameter("id") == null) {
                resp.sendRedirect("/organisation.do");
            } else {
                long id = Long.parseLong(req.getParameter("id"));
                Optional<Organisation> organisation = organisationService.findById(id);
                if (organisation.isPresent()) {
                    req.getSession().setAttribute("organisation", organisation.get());
                }else {
                    log.error("organisation is not available");
                }
                req.getSession().setAttribute("name",organisation.get().getName());
                req.getSession().setAttribute("address", organisation.get().getAddress());
                req.getSession().setAttribute("phoneNumber", organisation.get().getPhoneNumber());
                req.getRequestDispatcher("/jsp/organisation-edit.jsp").forward(req,resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            long id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String name = req.getParameter("name");
            String address = req.getParameter("address");
            String phoneNumber = req.getParameter("phoneNumber");
            String description = req.getParameter("description");

            organisation = Organisation
                    .builder()
                    .id(id)
                    .name(name)
                    .title(title)
                    .address(address)
                    .phoneNumber(phoneNumber)
                    .description(description)
                    .deleted(false)
                    .build();

            organisationService.edit(organisation);
                resp.sendRedirect("/organisation.do");

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
    }

