package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.FinancialDoc;
import com.mftplus.automation.model.FinancialTransaction;
import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.FinancialTransactionType;
import com.mftplus.automation.model.enums.PaymentType;
import com.mftplus.automation.service.impl.FinancialDocServiceImpl;
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
@WebServlet(name = "financialDocServlet", urlPatterns = "/financialDoc.do")
public class FinancialDocServlet extends HttpServlet {

    @Inject
    private FinancialDocServiceImpl financialDocService;

    @Inject
    private FinancialDoc financialDoc;

    @Inject
    private  FinancialTransaction financialTransaction;

    @Inject
    private Section section;

    @Inject
    private User user;

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

            section= Section
                    .builder()
                    .duty(duty)
                    .title(title)
                    .phoneNumber(phoneNumber)
                    .build();

            String faDateTime=req.getParameter("faDateTime");
            Long amount= Long.valueOf(req.getParameter("amount"));
            int trackingCode= Integer.parseInt(req.getParameter("trackingCode"));

            financialTransaction= FinancialTransaction
                    .builder()
                    .user(user)
                    .referringSection(section)
                    .paymentType(paymentType)
                    .amount(amount)
                    .trackingCode(trackingCode)
                    .transactionType(financialTransactionType)
                    .faDateTime(LocalDateTime.parse(faDateTime))
                    .build();

            long docNumber=Long.valueOf(req.getParameter("docNumber"));
            String faDateTime2=req.getParameter("faDateTime");
            String description=req.getParameter("description");

            financialDoc=FinancialDoc
                    .builder()
                    .docNumber(docNumber)
                    .faDateTime(LocalDateTime.parse(faDateTime2))
                    .description(description)
                    .financialTransaction(financialTransaction)
                    .build();

            financialDocService.save(financialDoc);

            log.info("FinancialDocServlet - FinancialDoc Saved");
            req.getRequestDispatcher("/jsp/financialDoc.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("FinancialDocServlet - Error Save FinancialDoc");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/financialDoc.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("financialDocList", financialDocService.findAll());
            req.getRequestDispatcher("/jsp/financialDoc.jsp").forward(req, resp);
            financialDocService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
