package com.mftplus.controller.servlet;

import com.mftplus.service.impl.FinancialTransactionServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/financialTransactionBox.do")
public class FinancialTransactionBoxServlet extends HttpServlet {

    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("FinancialTransactionBox - Get");

        try {
            String username = req.getUserPrincipal().getName();
            log.info("Fetching Financial Transaction for user: {}", username);

            req.getSession().setAttribute("financialTransactionListByUsername", financialTransactionService.findByUser(username));
            log.info("financial transaction fetched successfully for username: {}", username);

            req.getRequestDispatcher("/jsp/table/financialTransaction-box.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error in FinancialTransactionBoxServlet: {}", e.getMessage());
            throw new ServletException(e);
        }
    }
}
