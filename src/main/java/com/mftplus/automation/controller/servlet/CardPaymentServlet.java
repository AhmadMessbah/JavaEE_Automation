package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.*;
import com.mftplus.automation.model.enums.FinancialTransactionType;
import com.mftplus.automation.model.enums.PaymentType;
import com.mftplus.automation.service.impl.CardPaymentServiceImp;
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
    private User user;

    @Inject
    private Bank bank;

    @Inject
    private Section section;

    @Inject
    private PaymentType paymentType;

    @Inject
    private FinancialTransactionType financialTransactionType;

    @Inject
    private FinancialTransaction financialTransaction;

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
            long accountBalance= Long.parseLong(req.getParameter("accountBalance"));

            bank = Bank
                    .builder()
                    .name(name)
                    .accountNumber(accountNumber)
                    .branchCode(branchCode)
                    .branchName(branchName)
                    .accountType(accountType)
                    .accountBalance(accountBalance)
                    .deleted(false)
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

            String depositCode=req.getParameter("depositCode");
            long amount2=Long.valueOf(req.getParameter("amount"));
            String faDateTime2=req.getParameter("faDateTime");

            cardPayment = CardPayment
                    .builder()
                    .depositCode(depositCode)
                    .bankInvolved(bank)
                    .amount(amount2)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .financialTransaction(financialTransaction)
                    .build();

            cardPaymentService.save(cardPayment);

            log.info("CardPaymentServlet - CardPayment Saved");
            req.getRequestDispatcher("/jsp/cardPayment.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("CardPaymentServlet - Error Save CardPayment");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/CardPayment.jsp").forward(req, resp);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("cardPaymentList", cardPaymentService.findAll());
            req.getRequestDispatcher("/jsp/cardPayment.jsp").forward(req, resp);
            cardPaymentService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
