package com.mftplus.controller.servlet;

import com.mftplus.controller.validation.BeanValidator;
import com.mftplus.model.Bank;
import com.mftplus.model.enums.AccountType;
import com.mftplus.service.impl.BankServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;


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

            String name = req.getParameter("name");
            String accountNumber = req.getParameter("accountNumber");
            int branchCode = Integer.parseInt(req.getParameter("branchCode"));
            String branchName = req.getParameter("branchName");
            String accountType = req.getParameter("accountType");
            long accountBalance = Long.parseLong(req.getParameter("accountBalance"));

            bank = Bank
                    .builder()
                    .name(name)
                    .accountNumber(accountNumber)
                    .branchCode(branchCode)
                    .branchName(branchName)
                    .accountType(AccountType.valueOf(accountType))
                    .accountBalance(accountBalance)
                    .deleted(false)
                    .build();

            //validate
            BeanValidator<Bank> validator = new BeanValidator<>();
            if (validator.validate(bank) != null) {
                resp.setStatus(500);
                resp.getWriter().write(validator.validate(bank).toString());
            }

            bankService.save(bank);
            log.info("BankServlet - Bank Saved");
            req.getSession().setAttribute("bankId", bank.getId());
            resp.sendRedirect("/bank.do");


        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("accessTypes", Arrays.asList(AccountType.values()));
            req.getSession().setAttribute("bankList", bankService.findAll());
            req.getRequestDispatcher("/jsp/bank.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            bankService.removeById(id);

            log.info("BankServlet - Bank Removed");
            resp.sendRedirect("/bank.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
