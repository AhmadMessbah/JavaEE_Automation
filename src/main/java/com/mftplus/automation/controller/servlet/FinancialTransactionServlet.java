package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.*;
import com.mftplus.automation.model.enums.FinancialTransactionType;
import com.mftplus.automation.model.enums.PaymentType;
import com.mftplus.automation.service.impl.*;
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
@WebServlet(name = "/financialTransactionServlet", urlPatterns = "/FinancialTransaction.do")
public class FinancialTransactionServlet extends HttpServlet {
    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private CardPayment cardPayment;

    @Inject
    private BankServiceImpl bankService;

    @Inject
    private CheckPayment checkPayment;

    @Inject
    private CashDeskServiceImp cashDeskService;

    @Inject
    private FinancialTransaction financialTransaction;

    @Inject
    private SectionServiceImpl sectionService;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String checkNumber=req.getParameter("checkNumber");
            String faCheckDueDate=req.getParameter("faCheckDueDate");
            long amount3= Long.parseLong((req.getParameter("amount")));
            String faDateTime3=req.getParameter("faDateTime");

            checkPayment = CheckPayment
                    .builder()
                    .checkNumber(checkNumber)
                    .faCheckDueDate(LocalDateTime.parse(faCheckDueDate))
                    .cashDesk(cashDeskService.findByCashDeskNumber(Integer.parseInt(req.getParameter("cashDesk"))).get())
                    .amount(amount3)
                    .faDateTime(LocalDateTime.parse(faDateTime3))
                    .deleted(false)
                    .build();

            String depositCode=req.getParameter("depositCode");
            long amount2= Long.parseLong((req.getParameter("amount")));
            String faDateTime2=req.getParameter("faDateTime");

            cardPayment = CardPayment
                    .builder()
                    .depositCode(depositCode)
                    .bankInvolved(bankService.findByAccountNumber(req.getParameter("bank")).get())
                    .amount(amount2)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .deleted(false)
                    .build();

            String faDateTime1 = req.getParameter("faDateTime");
            Long amount1 = Long.valueOf(req.getParameter("amount"));
            int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
            String paymentType = req.getParameter("paymentType");
            String transactionType = req.getParameter("transactionType");

            financialTransaction = FinancialTransaction
                    .builder()
                    .user(userService.findByUsername(req.getParameter("cashier")).get())
                    .referringSection(sectionService.findByTitle(req.getParameter("section")).get())
                    .paymentType(PaymentType.valueOf(paymentType))
                    .cardPayment(cardPayment)
                    .checkPayment(checkPayment)
                    .amount(amount1)
                    .trackingCode(trackingCode)
                    .transactionType(FinancialTransactionType.valueOf(transactionType))
                    .faDateTime(LocalDateTime.parse(faDateTime1))
                    .deleted(false)
                    .build();

            financialTransactionService.save(financialTransaction);

            log.info("FinancialTransactionServlet - FinancialTransaction Saved");
            resp.sendRedirect("/financialTransaction.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String checkNumber=req.getParameter("checkNumber");
            String faCheckDueDate=req.getParameter("faCheckDueDate");
            long amount3= Long.parseLong((req.getParameter("amount")));
            String faDateTime3=req.getParameter("faDateTime");

            checkPayment = CheckPayment
                    .builder()
                    .checkNumber(checkNumber)
                    .faCheckDueDate(LocalDateTime.parse(faCheckDueDate))
                    .cashDesk(cashDeskService.findByCashDeskNumber(Integer.parseInt(req.getParameter("cashDesk"))).get())
                    .amount(amount3)
                    .faDateTime(LocalDateTime.parse(faDateTime3))
                    .deleted(false)
                    .build();

            String depositCode=req.getParameter("depositCode");
            long amount2= Long.parseLong((req.getParameter("amount")));
            String faDateTime2=req.getParameter("faDateTime");

            cardPayment = CardPayment
                    .builder()
                    .depositCode(depositCode)
                    .bankInvolved(bankService.findByAccountNumber(req.getParameter("bank")).get())
                    .amount(amount2)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .deleted(false)
                    .build();

            String faDateTime1 = req.getParameter("faDateTime");
            Long amount1 = Long.valueOf(req.getParameter("amount"));
            int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
            String paymentType = req.getParameter("paymentType");
            String transactionType = req.getParameter("transactionType");

            financialTransaction = FinancialTransaction
                    .builder()
                    .faDateTime(LocalDateTime.parse(faDateTime1))
                    .user(userService.findByUsername(req.getParameter("cashier")).get())
                    .referringSection(sectionService.findByTitle(req.getParameter("section")).get())
                    .paymentType(PaymentType.valueOf(paymentType))
                    .cardPayment(cardPayment)
                    .checkPayment(checkPayment)
                    .amount(amount1)
                    .trackingCode(trackingCode)
                    .transactionType(FinancialTransactionType.valueOf(transactionType))
                    .deleted(false)
                    .build();

            financialTransactionService.edit(financialTransaction);

            log.info("FinancialTransactionServlet - FinancialTransaction Edited");
            resp.sendRedirect("/financialTransaction.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("financialTransactionList", financialTransactionService.findAll());
            req.getRequestDispatcher("/jsp/financialTransaction.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id= Long.parseLong(req.getParameter("id"));
            financialTransactionService.removeById(id);

            log.info("FinancialTransactionServlet - FinancialTransaction Removed");
            resp.sendRedirect("/financialTransaction.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
