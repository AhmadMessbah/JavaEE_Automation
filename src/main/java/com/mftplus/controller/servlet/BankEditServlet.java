package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.IdIsRequiredException;
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
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/bankEdit.do")
public class BankEditServlet extends HttpServlet {
    @Inject
    private BankServiceImpl bankService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("BankEditServlet - Get");
        try {
            if (req.getParameter("id") == null) {
                throw new IdIsRequiredException("Please set bank id !");
            } else {
                Long id = Long.valueOf(req.getParameter("id"));
                Optional<Bank> bank = bankService.findById(id);
                bank.ifPresent(value -> req.getSession().setAttribute("bank", value));

                req.setAttribute("accountTypes", AccountType.values());
                req.getRequestDispatcher("/jsp/form/edit/editBank.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("BankEditServlet - Put");
        try {
            String idStr = req.getParameter("id");
            String name = req.getParameter("name");
            String accountNumber = req.getParameter("accountNumber");
            String branchCodeStr = req.getParameter("branchCode");
            String branchName = req.getParameter("branchName");
            String accountType = req.getParameter("accountType");
            String accountBalanceStr = req.getParameter("accountBalance");

            // Log all received parameters
            log.info("Received parameters: id={}, name={}, accountNumber={}, branchCode={}, branchName={}, accountType={}, accountBalance={}",
                    idStr, name, accountNumber, branchCodeStr, branchName, accountType, accountBalanceStr);

            // Check for missing parameters
            if (idStr == null || idStr.isEmpty() ||
                    branchCodeStr == null || branchCodeStr.isEmpty() ||
                    accountBalanceStr == null || accountBalanceStr.isEmpty()) {
                log.error("Missing parameters: id={}, branchCode={}, accountBalance={}", idStr, branchCodeStr, accountBalanceStr);
                throw new IllegalArgumentException("Required parameters are missing");
            }

            Long id = Long.valueOf(idStr);
            Long branchCode = Long.parseLong(branchCodeStr);
            Long accountBalance = Long.parseLong(accountBalanceStr);

            // Construct Bank object
            Bank bank = Bank.builder()
                    .id(id)
                    .name(name)
                    .accountNumber(accountNumber)
                    .branchCode(branchCode)
                    .branchName(branchName)
                    .accountType(AccountType.valueOf(accountType))
                    .accountBalance(accountBalance)
                    .deleted(false)
                    .build();

            // Perform validation
            BeanValidator<Bank> validator = new BeanValidator<>();
            String validationResult = String.valueOf(validator.validate(bank));
            if (validationResult != null) {
                log.error("Validation failed: {}", validationResult);
                resp.setStatus(500);
                resp.getWriter().write(validationResult);
                return;
            }

            // Edit the bank
            bankService.edit(bank);

            // Redirect to bank display page
            log.info("BankEditServlet - Bank Edited successfully");
            resp.setStatus(200);
            String msg = "تغییرات با موفقیت ثبت شد !";
            req.getSession().setAttribute("ok", msg);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            resp.setStatus(500);
            resp.getWriter().write("An error occurred: " + e.getMessage());
        }
    }
}
