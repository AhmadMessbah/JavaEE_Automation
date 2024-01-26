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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user=
                    User
                            .builder()
                            .username(req.getParameter("username"))
                            .password(req.getParameter("password"))
                            .build();
            CashDesk cashDesk=CashDesk
                    .builder()
                    .name(req.getParameter("name"))
                    .cashDeskNumber(Integer.parseInt(req.getParameter("cashDeskNumber")))
                    .cashier(user)
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
            CheckTransaction checkTransaction=CheckTransaction
                    .builder()
                    .checkNumber(req.getParameter("checkNumber"))
                    .faCheckDueDate(LocalDateTime.parse(req.getParameter("faCheckDueDate")))
                    .cashDesk(cashDesk)
                    .trackingCode(Integer.parseInt(req.getParameter("trackingCode")))
                    .amount(Long.valueOf(req.getParameter("amount")))
                    .description(req.getParameter("description"))
                    .payer(user)
                    .financialDoc(financialDoc)
                    .faDateTime(LocalDateTime.parse(req.getParameter("faDateTime")))
                    .build();
            log.info("CheckTransactionServlet - CheckTransaction Saved");
            req.getRequestDispatcher("/jsp/checkTransaction.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("CheckTransactionServlet - Error Save CheckTransaction");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/checkTransaction.jsp").forward(req, resp);
        }
    }
}
