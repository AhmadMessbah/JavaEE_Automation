package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.FinancialDoc;
import com.mftplus.automation.service.impl.FinancialDocServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
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
    @PersistenceContext(unitName = "automation")

    @Inject
    private FinancialDocServiceImpl financialDocService;

    @Inject
    private FinancialDoc financialDoc;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long docNumber=Long.valueOf(req.getParameter("docNumber"));
            String faDateTime=req.getParameter("faDateTime");

            financialDoc=FinancialDoc
                    .builder()
                    .docNumber(docNumber)
                    .faDateTime(LocalDateTime.parse(faDateTime))
                    .build();

            financialDocService.save(financialDoc);

            log.info("FinancialDocServlet - FinancialDoc Saved");
            req.getRequestDispatcher("/jsp/financialDoc.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("FinancialDocServlet - Error Save FinancialDoc");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/financialDoc.jsp").forward(req, resp);
        }
    }
}
