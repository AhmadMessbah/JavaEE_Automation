package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.IdIsRequiredException;
import com.mftplus.model.FinancialTransaction;
import com.mftplus.model.enums.FinancialTransactionType;
import com.mftplus.model.enums.PaymentType;
import com.mftplus.service.impl.*;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/financialTransactionDisplay.do")
public class FinancialTransactionDisplayServlet extends HttpServlet {

    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private DepartmentServiceImp departmentService;

    @Inject
    private FinancialTransaction financialTransaction;

    @Inject
    private BankServiceImpl bankService;

    @Inject
    private CashDeskServiceImp cashDeskService;

    @Inject
    private CheckPaymentServiceImp checkPaymentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("FinancialTransactionDisplayServlet - Get");

        try {
            String idParam = req.getParameter("id");
            if (idParam == null) {
                throw new IdIsRequiredException("Please set financial transaction id !");
            } else {
                Long id = Long.valueOf(idParam);
                log.info("Fetching FinancialTransaction with id: {}", id);

                Optional<FinancialTransaction> financialTransaction = financialTransactionService.findById(id);
                if (financialTransaction.isPresent()) {
                    req.getSession().setAttribute("financialTransaction", financialTransaction.get());
                    log.info("FinancialTransaction found: {}", financialTransaction.get());
                } else {
                    log.warn("No FinancialTransaction found with id: {}", id);
                }

                req.getSession().setAttribute("paymentTypes", Arrays.asList(PaymentType.values()));
                req.getSession().setAttribute("transactionTypes", Arrays.asList(FinancialTransactionType.values()));
                req.getSession().setAttribute("userList", userService.findAll());
                req.getSession().setAttribute("departmentList", departmentService.findAll());
                req.getSession().setAttribute("bankList", bankService.findAll());
                req.getSession().setAttribute("cashDeskList", cashDeskService.findAll());
                req.getSession().setAttribute("checkPaymentList", checkPaymentService.findAll());
                req.getRequestDispatcher("/jsp/form/display/financialTransaction.jsp").forward(req, resp);
                req.getSession().removeAttribute("ok");
            }
        } catch (IdIsRequiredException e) {
            log.error("IdIsRequiredException: {}", e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (NumberFormatException e) {
            log.error("Invalid id format: {}", e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid id format.");
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            throw new ServletException(e);
        }
    }
}
