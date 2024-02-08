package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.*;
import com.mftplus.automation.model.enums.FinancialTransactionType;
import com.mftplus.automation.model.enums.PaymentType;
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
@WebServlet(name = "/financialTransactionServlet", urlPatterns = "/FinancialTransaction.do")
public class FinancialTransactionServlet extends HttpServlet {
    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Inject
    private User user;

    @Inject
    private FinancialTransaction financialTransaction;

    @Inject
    private Section section;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String paymentType = req.getParameter("paymentType");
            String transactionType = req.getParameter("transactionType");

            user = User
                    .builder()
                    .username(username)
                    .build();

            String title = req.getParameter("title");
            String duty = req.getParameter("duty");
            String phoneNumber = req.getParameter("phoneNumber");

            section = Section
                    .builder()
                    .duty(duty)
                    .title(title)
                    .phoneNumber(phoneNumber)
                    .build();

            String faDateTime = req.getParameter("faDateTime");
            Long amount = Long.valueOf(req.getParameter("amount"));
            int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));

            financialTransaction = FinancialTransaction
                    .builder()
                    .user(user)
                    .referringSection(section)
                    .paymentType(PaymentType.valueOf(paymentType))
                    .amount(amount)
                    .trackingCode(trackingCode)
                    .transactionType(FinancialTransactionType.valueOf(transactionType))
                    .faDateTime(LocalDateTime.parse(faDateTime))
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
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String paymentType = req.getParameter("paymentType");
            String transactionType = req.getParameter("transactionType");

            user = User
                    .builder()
                    .username(username)
                    .password(password)
                    .build();

            String title = req.getParameter("title");
            String duty = req.getParameter("duty");
            String phoneNumber = req.getParameter("phoneNumber");

            section = Section
                    .builder()
                    .duty(duty)
                    .title(title)
                    .phoneNumber(phoneNumber)
                    .build();

            String faDateTime = req.getParameter("faDateTime");
            Long amount = Long.valueOf(req.getParameter("amount"));
            int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));

            financialTransaction = FinancialTransaction
                    .builder()
                    .user(user)
                    .referringSection(section)
                    .paymentType(PaymentType.valueOf(paymentType))
                    .amount(amount)
                    .trackingCode(trackingCode)
                    .transactionType(FinancialTransactionType.valueOf(transactionType))
                    .faDateTime(LocalDateTime.parse(faDateTime))
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
            financialTransactionService.findAll();
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
