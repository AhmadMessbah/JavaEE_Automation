package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Bank;
import com.mftplus.automation.model.User;
import com.mftplus.automation.service.impl.BankServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "/bankServlet", urlPatterns = "/bank.do")
public class BankServlet extends HttpServlet {
    @PersistenceContext(unitName = "automation")

    @Inject
    private BankServiceImpl bankService;

    @Inject
    private User user;

    @Inject
    private Bank bank;

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

            bankService.save(bank);

            log.info("BankServlet - Bank Saved");
            req.getRequestDispatcher("/jsp/bank.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("BankServlet - Error Save Bank");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/bank.jsp").forward(req, resp);
        }
    }
}
