package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.*;
import com.mftplus.automation.service.impl.CheckTransactionServiceImp;
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
@WebServlet(name = "/checkTransactionServlet", urlPatterns = "/checkTransactio.do")
public class    CheckTransactionServlet extends HttpServlet {
    @PersistenceContext(unitName = "automation")

    @Inject
    private CheckTransactionServiceImp checkTransactionService;

    @Inject
    private User user;

    @Inject
    private CashDesk cashDesk;

    @Inject
    private FinancialDoc financialDoc;

    @Inject
    private CheckTransaction checkTransaction;

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

            String name=req.getParameter("name");
            int cashDeskNumber=Integer.parseInt(req.getParameter("cashDeskNumber"));

            cashDesk=CashDesk
                    .builder()
                    .name(name)
                    .cashDeskNumber(cashDeskNumber)
                    .cashier(user)
                    .build();

            long docNumber=Long.valueOf(req.getParameter("docNumber"));
            String faDateTime=req.getParameter("faDateTime");

            financialDoc=FinancialDoc
                    .builder()
                    .docNumber(docNumber)
                    .faDateTime(LocalDateTime.parse(faDateTime))
                    .build();

            String checkNumber=req.getParameter("checkNumber");
            String faCheckDueDate=req.getParameter("faCheckDueDate");
            int trackingCode=Integer.parseInt(req.getParameter("trackingCode"));
            long amount=Long.valueOf(req.getParameter("amount"));
            String description=req.getParameter("description");
            String faDateTime2=req.getParameter("faDateTime");

            checkTransaction=CheckTransaction
                    .builder()
                    .checkNumber(checkNumber)
                    .faCheckDueDate(LocalDateTime.parse(faCheckDueDate))
                    .cashDesk(cashDesk)
                    .trackingCode(trackingCode)
                    .amount(amount)
                    .description(description)
                    .payer(user)
                    .financialDoc(financialDoc)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .build();

            checkTransactionService.save(checkTransaction);

            log.info("CheckTransactionServlet - CheckTransaction Saved");
            req.getRequestDispatcher("/jsp/checkTransaction.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("CheckTransactionServlet - Error Save CheckTransaction");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/checkTransaction.jsp").forward(req, resp);
        }
    }
}
