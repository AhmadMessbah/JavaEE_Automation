package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Bank;
import com.mftplus.automation.model.CardPayment;
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

    @Inject
    private CardPayment cardPayment;

    @Inject
    private User user;

    @Inject
    private Bank bank;

    @Inject
    private FinancialDoc financialDoc;

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
            String accountNumber=req.getParameter("accountNumber");
            int branchCode=Integer.parseInt(req.getParameter("branchCode"));
            String branchName=req.getParameter("branchName");
            String accountType=req.getParameter("accountType");

            bank = Bank
                            .builder()
                            .name(name)
                            .accountNumber(accountNumber)
                            .branchCode(branchCode)
                            .branchName(branchName)
                            .accountType(accountType)
                            .accountOwner(user)
                            .build();

            long docNumber=Long.valueOf(req.getParameter("docNumber"));
            String faDateTime=req.getParameter("faDateTime");

            financialDoc=FinancialDoc
                    .builder()
                    .docNumber(docNumber)
                    .faDateTime(LocalDateTime.parse(faDateTime))
                    .build();

            String depositCode=req.getParameter("depositCode");
            int trackingCode=Integer.parseInt(req.getParameter("trackingCode"));
            long amount=Long.valueOf(req.getParameter("amount"));
            String description=req.getParameter("description");
            String faDateTime2=req.getParameter("faDateTime");

            cardPayment = CardPayment
                    .builder()
                    .depositCode(depositCode)
                    .bankInvolved(bank)
                    .trackingCode(trackingCode)
                    .amount(amount)
                    .description(description)
                    .payer(user)
                    .financialDoc(financialDoc)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .build();

            bankDepositTransactionService.save(cardPayment);

            log.info("BankDepositTransactionServlet - BankDepositTransaction Saved");
            req.getRequestDispatcher("/jsp/bankDepositTransaction.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("BankDepositTransactionServlet - Error Save BankDepositTransaction");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/bankDepositTransaction.jsp").forward(req, resp);
        }
    }
}
