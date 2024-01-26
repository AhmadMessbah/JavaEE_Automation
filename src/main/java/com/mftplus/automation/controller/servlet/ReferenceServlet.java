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
        try {
            req.getSession().setAttribute("referenceList", referenceService.findAll());
            req.getRequestDispatcher("/jsp/reference.jsp").forward(req, resp);
            referenceService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("ReferenceServlet - Post");
        try {
                String letterId = req.getParameter("letterId");
                String refType = req.getParameter("refType");
                String faExpiration = req.getParameter("faExpiration");
                String paraph = req.getParameter("paraph");
                String explanation = req.getParameter("explanation");
                String status = req.getParameter("status");
                String validate = req.getParameter("validate");
                String priority = req.getParameter("priority");

                //todo referenceSenderId and referenceReceiverId

            if (letterId != null){

                Optional<Letter> letter = letterService.findById(Long.valueOf(letterId));

                if (letter.isPresent()){
                    LocalDateTime localDateTime = LocalDateTime.now();
                    Reference reference =
                            Reference
                                    .builder()
                                    .letter(letter.get())
                                    .refType(ReferenceType.valueOf(refType))
                                    .refDateAndTime(localDateTime)
                                    .paraph(paraph)
                                    .explanation(explanation)
                                    .status(Boolean.parseBoolean(status))
                                    .validate(Boolean.parseBoolean(validate))
                                    .priority(ReferencePriority.valueOf(priority))
                                    .deleted(false)
                                    .build();
                    referenceService.save(reference);
                    log.info("ReferenceServlet - Reference Saved");
                }

            }

        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
