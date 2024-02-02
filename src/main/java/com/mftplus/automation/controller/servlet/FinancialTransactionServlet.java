package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.*;
import com.mftplus.automation.model.enums.FinancialTransactionType;
import com.mftplus.automation.model.enums.PaymentType;
import com.mftplus.automation.service.impl.FinancialTransactionServiceImpl;
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

    @Inject
    private PaymentType paymentType;

    @Inject
    private FinancialTransactionType financialTransactionType;

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

            String title=req.getParameter("title");
            String duty=req.getParameter("duty");
            String phoneNumber=req.getParameter("phoneNumber");

            section=Section
                    .builder()
                    .duty(duty)
                    .title(title)
                    .phoneNumber(phoneNumber)
                    .build();

            String faDateTime=req.getParameter("faDateTime");
            Long amount= Long.valueOf(req.getParameter("amount"));
            int trackingCode= Integer.parseInt(req.getParameter("trackingCode"));

            financialTransaction=FinancialTransaction
                    .builder()
                    .user(user)
                    .referringSection(section)
                    .paymentType(paymentType)
                    .amount(amount)
                    .trackingCode(trackingCode)
                    .transactionType(financialTransactionType)
                    .faDateTime(LocalDateTime.parse(faDateTime))
                    .build();

            financialTransactionService.save(financialTransaction);

            log.info("FinancialTransactionServlet - FinancialTransaction Saved");
            req.getRequestDispatcher("/jsp/financialTransaction.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("FinancialTransactionServlet - Error Save FinancialTransaction");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/financialTransaction.jsp").forward(req, resp);
        }
    }
}