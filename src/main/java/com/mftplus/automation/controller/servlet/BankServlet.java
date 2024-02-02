package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Bank;
import com.mftplus.automation.service.impl.BankServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebServlet(urlPatterns = "/bank.do")
@Slf4j
public class BankServlet extends HttpServlet {
    @Inject
    private BankServiceImpl bankService;

    @Inject
    private Bank bank;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

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

            bankService.save(bank);

            log.info("BankServlet - Bank Saved");
            resp.sendRedirect("/bank.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("bankList", bankService.findAll());
            req.getRequestDispatcher("/jsp/bank.jsp").forward(req, resp);
            bankService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
