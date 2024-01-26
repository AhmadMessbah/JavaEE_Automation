package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Bank;
import com.mftplus.automation.model.BankDepositTransaction;
import com.mftplus.automation.model.FinancialDoc;
import com.mftplus.automation.model.User;
import com.mftplus.automation.service.impl.BankDepositTransactionServiceImp;
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
@WebServlet(name = "bankDepositTransactionServlet", urlPatterns = "/bankDepositTransaction.do")
public class BankDepositTransactionServlet extends HttpServlet {
    @PersistenceContext(unitName = "automation")

    @Inject
    private BankDepositTransactionServiceImp bankDepositTransactionService;

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
                    .trackingCode(Integer.parseInt(req.getParameter("trackingCode")))
                    .amount(Long.valueOf(req.getParameter("amount")))
                    .description(req.getParameter("description"))
                    .payer(user)
                    .financialDoc(financialDoc)
                    .faDateTime(LocalDateTime.parse(req.getParameter("faDateTime")))
                    .build();
            bankDepositTransactionService.save(bankDepositTransaction);
            log.info("BankDepositTransactionServlet - BankDepositTransaction Saved");
            req.getRequestDispatcher("/jsp/bankDepositTransaction.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("BankDepositTransactionServlet - Error Save BankDepositTransaction");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/bankDepositTransaction.jsp").forward(req, resp);
        }
    }
}
