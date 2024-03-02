package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Letter;
import com.mftplus.automation.model.Reference;
import com.mftplus.automation.model.enums.ReferencePriority;
import com.mftplus.automation.model.enums.ReferenceType;
import com.mftplus.automation.service.impl.LetterServiceImpl;
import com.mftplus.automation.service.impl.ReferenceServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@WebServlet (urlPatterns = "/reference.do")
public class ReferenceServlet extends HttpServlet {
    @Inject
    private ReferenceServiceImpl referenceService;

    @Inject
    private LetterServiceImpl letterService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("ReferenceServlet - GET");

        try {
            req.getSession().setAttribute("refTypes", Arrays.asList(ReferenceType.values()));
            req.getSession().setAttribute("priorities", Arrays.asList(ReferencePriority.values()));
            req.getSession().setAttribute("referenceList", referenceService.findAll());
            req.getSession().setAttribute("letterIdRef",req.getParameter("letterIdRef"));
            req.getRequestDispatcher("/jsp/reference.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("ReferenceServlet - Post");
        try {
                String letterId = req.getParameter("letterIdRef");
                String refType = req.getParameter("r_refType");
                String priority = req.getParameter("priority");
                String faExpiration = req.getParameter("r_expiration");
                String paraph = req.getParameter("paraph");
                String explanation = req.getParameter("explanation");
                String status = req.getParameter("status");
                boolean validate = req.getParameter("validate") != null && req.getParameter("validate").equals("on");

            if (letterId != null){

                Optional<Letter> letter = letterService.findById(Long.valueOf(letterId));

                if (letter.isPresent()){
                    Reference reference =
                            Reference
                                    .builder()
                                    .letterId(letter.get())
                                    .refDateAndTime(LocalDateTime.now())
                                    .paraph(paraph)
                                    .explanation(explanation)
                                    .status(Boolean.parseBoolean(status))
                                    .validate(validate)
                                    .priority(ReferencePriority.valueOf(priority))
                                    .refType(ReferenceType.valueOf(refType))
                                    .faExpiration(faExpiration)
                                    .deleted(false)
                                    .build();
                    reference.setFaExpiration(faExpiration);
                    referenceService.save(reference);
                    log.info("ReferenceServlet - Reference Saved");
                resp.sendRedirect("/reference.do");
                }
            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
//todo letterId
//todo referenceSenderId
//todo referenceReceiverId
//todo status and validate
