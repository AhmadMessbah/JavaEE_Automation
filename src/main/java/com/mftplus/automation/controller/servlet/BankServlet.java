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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user=
                    User
                            .builder()
                            .username(req.getParameter("username"))
                            .password(req.getParameter("password"))
                            .build();
            Bank bank =
                    Bank
                            .builder()
                            .name(req.getParameter("name"))
                            .accountNumber(req.getParameter("accountNumber"))
                            .branchCode(Integer.parseInt(req.getParameter("branchCode")))
                            .branchName(req.getParameter("branchName"))
                            .accountType(req.getParameter("accountType"))
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
