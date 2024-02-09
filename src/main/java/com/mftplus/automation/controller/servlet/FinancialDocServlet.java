package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.FinancialDoc;
import com.mftplus.automation.service.impl.FinancialDocServiceImpl;
import com.mftplus.automation.service.impl.FinancialTransactionServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@WebServlet(name = "financialDocServlet", urlPatterns = "/financialDoc.do")
public class FinancialDocServlet extends HttpServlet {

    @Inject
    private FinancialDocServiceImpl financialDocService;

    @Inject
    private FinancialDoc financialDoc;

    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long docNumber = Long.parseLong((req.getParameter("docNumber")));
            String faDateTime2 = req.getParameter("faDateTime");
            String description = req.getParameter("description");

            financialDoc = FinancialDoc
                    .builder()
                    .docNumber(docNumber)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .description(description)
                    .financialTransaction(financialTransactionService.findByTrackingCode(Integer.parseInt(req.getParameter("financialTransaction"))).get())
                    .deleted(false)
                    .build();

            financialDocService.save(financialDoc);

            log.info("FinancialDocServlet - FinancialDoc Saved");
            resp.sendRedirect("/financialDoc.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long docNumber = Long.parseLong((req.getParameter("docNumber")));
            String faDateTime2 = req.getParameter("faDateTime");
            String description = req.getParameter("description");

            financialDoc = FinancialDoc
                    .builder()
                    .docNumber(docNumber)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .description(description)
                    .financialTransaction(financialTransactionService.findByTrackingCode(Integer.parseInt(req.getParameter("financialTransaction"))).get())
                    .deleted(false)
                    .build();
            financialDocService.edit(financialDoc);

            log.info("FinancialDocServlet - FinancialDoc Edited");
            resp.sendRedirect("/financialDoc.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("financialDocList", financialDocService.findAll());
            req.getRequestDispatcher("/jsp/financialDoc.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id= Long.parseLong(req.getParameter("id"));
            financialDocService.removeById(id);

            log.info("FinancialDocServlet - FinancialDoc Removed");
            resp.sendRedirect("/financialDoc.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
