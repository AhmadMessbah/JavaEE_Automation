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
@WebServlet(urlPatterns = "/financialTransactionTable.do")
public class FinancialTransactionTable extends HttpServlet {

    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("FinancialTransactionTableServlet - Get");
        try {
            req.getSession().setAttribute("financialTransactionList", financialTransactionService.findAll());
            req.getRequestDispatcher("/jsp/table/financialTransaction.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
