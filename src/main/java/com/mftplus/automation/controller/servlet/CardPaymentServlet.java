package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.*;
import com.mftplus.automation.service.impl.BankServiceImpl;
import com.mftplus.automation.service.impl.CardPaymentServiceImp;
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
@WebServlet(name = "cardPaymentServlet", urlPatterns = "/cardPayment.do")
public class CardPaymentServlet extends HttpServlet {

    @Inject
    private CardPaymentServiceImp cardPaymentService;

    @Inject
    private CardPayment cardPayment;

    @Inject
    private BankServiceImpl bankService;

    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String depositCode=req.getParameter("depositCode");
            long amount= Long.parseLong((req.getParameter("amount")));
            String faDateTime2=req.getParameter("faDateTime");

            cardPayment = CardPayment
                    .builder()
                    .depositCode(depositCode)
                    .bankInvolved(bankService.findByAccountNumber(req.getParameter("bank")).get())
                    .amount(amount)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .financialTransaction(financialTransactionService.findById(Long.valueOf(req.getParameter("financialTransaction"))).get())
                    .deleted(false)
                    .build();

            cardPaymentService.save(cardPayment);

            log.info("CardPaymentServlet - CardPayment Saved");
            resp.sendRedirect("/cardPayment.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            String depositCode=req.getParameter("depositCode");
            long amount= Long.parseLong((req.getParameter("amount")));
            String faDateTime2=req.getParameter("faDateTime");

            cardPayment = CardPayment
                    .builder()
                    .depositCode(depositCode)
                    .bankInvolved(bankService.findByAccountNumber(req.getParameter("bank")).get())
                    .amount(amount)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .financialTransaction(financialTransactionService.findById(Long.valueOf(req.getParameter("financial"))).get())
                    .deleted(false)
                    .build();

            cardPaymentService.edit(cardPayment);

            log.info("CardPaymentServlet - CardPayment Edited");
            resp.sendRedirect("/cardPayment.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("cardPaymentList", cardPaymentService.findAll());
            req.getRequestDispatcher("/jsp/cardPayment.jsp").forward(req, resp);
            cardPaymentService.findAll();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id= Long.parseLong(req.getParameter("id"));
            cardPaymentService.removeById(id);

            log.info("CardPaymentServlet - Card Payment Removed");
            resp.sendRedirect("/cardPayment.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
