package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.*;
import com.mftplus.automation.model.enums.FinancialTransactionType;
import com.mftplus.automation.model.enums.PaymentType;
import com.mftplus.automation.service.impl.CheckPaymentServiceImp;
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
@WebServlet(name = "/checkPaymentServlet", urlPatterns = "/checkPayment.do")
public class CheckPaymentServlet extends HttpServlet {

    @Inject
    private CheckPaymentServiceImp checkPaymentService;

    @Inject
    private User user;

    @Inject
    private CashDesk cashDesk;

    @Inject
    private FinancialTransaction financialTransaction;

    @Inject
    private Section section;

    @Inject
    private CheckPayment checkPayment;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username=req.getParameter("username");
            String password=req.getParameter("password");
            String paymentType = req.getParameter("paymentType");
            String transactionType = req.getParameter("transactionType");

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
                    .paymentType(PaymentType.valueOf(paymentType))
                    .amount(amount)
                    .trackingCode(trackingCode)
                    .transactionType(FinancialTransactionType.valueOf(transactionType))
                    .faDateTime(LocalDateTime.parse(faDateTime))
                    .build();

            String checkNumber=req.getParameter("checkNumber");
            String faCheckDueDate=req.getParameter("faCheckDueDate");
            long amount2=Long.valueOf(req.getParameter("amount"));
            String faDateTime2=req.getParameter("faDateTime");

            checkPayment = CheckPayment
                    .builder()
                    .checkNumber(checkNumber)
                    .faCheckDueDate(LocalDateTime.parse(faCheckDueDate))
                    .cashDesk(cashDesk)
                    .amount(amount2)
                    .financialTransaction(financialTransaction)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .build();

            checkPaymentService.save(checkPayment);

            log.info("CheckPaymentServlet - CheckPayment Saved");
            resp.sendRedirect("/checkPayment.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username=req.getParameter("username");
            String password=req.getParameter("password");
            String paymentType = req.getParameter("paymentType");
            String transactionType = req.getParameter("transactionType");

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
                    .paymentType(PaymentType.valueOf(paymentType))
                    .amount(amount)
                    .trackingCode(trackingCode)
                    .transactionType(FinancialTransactionType.valueOf(transactionType))
                    .faDateTime(LocalDateTime.parse(faDateTime))
                    .build();

            String checkNumber=req.getParameter("checkNumber");
            String faCheckDueDate=req.getParameter("faCheckDueDate");
            long amount2=Long.valueOf(req.getParameter("amount"));
            String faDateTime2=req.getParameter("faDateTime");

            checkPayment = CheckPayment
                    .builder()
                    .checkNumber(checkNumber)
                    .faCheckDueDate(LocalDateTime.parse(faCheckDueDate))
                    .cashDesk(cashDesk)
                    .amount(amount2)
                    .financialTransaction(financialTransaction)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .build();

            checkPaymentService.edit(checkPayment);

            log.info("CheckPaymentServlet - CheckPayment Edited");
            resp.sendRedirect("/checkPayment.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("checkPaymentList", checkPaymentService.findAll());
            req.getRequestDispatcher("/jsp/checkPayment.jsp").forward(req, resp);
            checkPaymentService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id= Long.parseLong(req.getParameter("id"));
            checkPaymentService.removeById(id);

            log.info("CheckPaymentServlet - CheckPayment Removed");
            resp.sendRedirect("/checkPayment.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
