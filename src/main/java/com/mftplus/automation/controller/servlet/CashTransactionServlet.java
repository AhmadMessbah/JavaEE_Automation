package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.*;
import com.mftplus.automation.service.impl.CashTransactionServiceImp;
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
@WebServlet(name = "/cashTransactionServlet", urlPatterns = "/CashTransaction.do")
public class CashTransactionServlet extends HttpServlet {
    @PersistenceContext(unitName = "automation")

    @Inject
    private CashTransactionServiceImp cashTransactionService;

    @Inject
    private User user;

    @Inject
    private FinancialDoc financialDoc;

    @Inject
    private CashDesk cashDesk;

    @Inject
    private CashTransaction cashTransaction;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username=req.getParameter("username");
            String password=req.getParameter("password");

            user= User
                    .builder()
                    .username(username)
                    .password(password)
                    .build();

            long docNumber=Long.valueOf(req.getParameter("docNumber"));
            String faDateTime=req.getParameter("faDateTime");

            financialDoc=FinancialDoc
                    .builder()
                    .docNumber(docNumber)
                    .faDateTime(LocalDateTime.parse(faDateTime))
                    .build();

            String name2=req.getParameter("name");
            int cashDeskNumber=Integer.parseInt(req.getParameter("cashDeskNumber"));

            cashDesk=CashDesk
                    .builder()
                    .name(name2)
                    .cashDeskNumber(cashDeskNumber)
                    .cashier(user)
                    .build();

            int trackingCode=Integer.parseInt(req.getParameter("trackingCode"));
            long amount=Long.valueOf(req.getParameter("amount"));
            String description=req.getParameter("description");
            String faDateTime2=req.getParameter("faDateTime");

            cashTransaction=CashTransaction
                    .builder()
                    .cashDesk(cashDesk)
                    .trackingCode(trackingCode)
                    .amount(amount)
                    .description(description)
                    .payer(user)
                    .financialDoc(financialDoc)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .build();

            cashTransactionService.save(cashTransaction);

            log.info("CashTransactionServlet - CashTransaction Saved");
            req.getRequestDispatcher("/jsp/cashTransaction.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("CashTransactionServlet - Error Save CashTransaction");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/cashTransaction.jsp").forward(req, resp);
        }
    }
}
