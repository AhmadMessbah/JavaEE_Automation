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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user=
                    User
                            .builder()
                            .username(req.getParameter("username"))
                            .password(req.getParameter("password"))
                            .build();
            Bank bank =
                    Bank
                            .builder()
                            .name(req.getParameter("name"))
                            .accountNumber(req.getParameter("accountNumber"))
                            .branchCode(Integer.parseInt(req.getParameter("branchCode")))
                            .branchName(req.getParameter("branchName"))
                            .accountType(req.getParameter("accountType"))
                            .accountBalance(Long.valueOf(req.getParameter("accountBalance")))
                            .accountOwner(user)
                            .build();
            FinancialDoc financialDoc=FinancialDoc
                    .builder()
                    .docNumber(Long.valueOf(req.getParameter("docNumber")))
                    .faDateTime(LocalDateTime.parse(req.getParameter("faDateTime")))
                    .build();
            BankDepositTransaction bankDepositTransaction=BankDepositTransaction
                    .builder()
                    .depositCode(req.getParameter("depositCode"))
                    .bankInvolved(bank)
                    .build();
            CashDesk cashDesk=CashDesk
                    .builder()
                    .name(req.getParameter("name"))
                    .cashDeskNumber(Integer.parseInt(req.getParameter("cashDeskNumber")))
                    .cashier(user)
                    .build();
            CashTransaction cashTransaction=CashTransaction
                    .builder()
                    .cashDesk(cashDesk)
                    .trackingCode(Integer.parseInt(req.getParameter("trackingCode")))
                    .amount(Long.valueOf(req.getParameter("amount")))
                    .description(req.getParameter("description"))
                    .payer(user)
                    .financialDoc(financialDoc)
                    .faDateTime(LocalDateTime.parse(req.getParameter("faDateTime")))
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
