package com.mftplus.controller.servlet;

import com.mftplus.service.impl.CashDeskServiceImp;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/cashDeskTable.do")
public class CashDeskTable extends HttpServlet {
    @Inject
    private CashDeskServiceImp cashDeskService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("LetterTableServlet - Get");
        try {
            req.getSession().setAttribute("cashDeskList", cashDeskService.findAll());
            req.getRequestDispatcher("/jsp/table/cashDesk.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}