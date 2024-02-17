package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.*;
import com.mftplus.automation.service.impl.CashDeskServiceImp;
import com.mftplus.automation.service.impl.CheckPaymentServiceImp;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@WebServlet(name = "/checkPaymentServlet", urlPatterns = "/checkPayment.do")
public class CheckPaymentServlet extends HttpServlet {

    @Inject
    private CheckPaymentServiceImp checkPaymentService;

    @Inject
    private CashDeskServiceImp cashDeskService;

    @Inject
    private CheckPayment checkPayment;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String checkNumber=req.getParameter("checkNumber");
            String faCheckDueDate=req.getParameter("faCheckDueDate");
            long amount= Long.parseLong((req.getParameter("amount")));
            String faDateTime2=req.getParameter("faDateTime");
            Optional<CashDesk> cashDesk=cashDeskService.findByCashDeskNumber(Integer.parseInt(req.getParameter("cashDeskNumber")));

            if(cashDesk.isPresent()) {
                checkPayment = CheckPayment
                        .builder()
                        .checkNumber(checkNumber)
                        .faCheckDueDate(LocalDateTime.parse(faCheckDueDate))
                        .cashDesk(cashDesk.get())
                        .amount(amount)
                        .faDateTime(LocalDateTime.parse(faDateTime2))
                        .deleted(false)
                        .build();

                checkPaymentService.save(checkPayment);
                log.info("CheckPaymentServlet - CheckPayment Save");
                resp.sendRedirect("/checkPayment.do");
            }
            else {
                log.info("Invalid CashDesk");
                resp.sendRedirect("/checkPayment.do");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String checkNumber=req.getParameter("checkNumber");
            String faCheckDueDate=req.getParameter("faCheckDueDate");
            long amount= Long.parseLong((req.getParameter("amount")));
            String faDateTime2=req.getParameter("faDateTime");
            Optional<CashDesk> cashDesk=cashDeskService.findByCashDeskNumber(Integer.parseInt(req.getParameter("cashDeskNumber")));

            if(cashDesk.isPresent()) {
                checkPayment = CheckPayment
                        .builder()
                        .checkNumber(checkNumber)
                        .faCheckDueDate(LocalDateTime.parse(faCheckDueDate))
                        .cashDesk(cashDesk.get())
                        .amount(amount)
                        .faDateTime(LocalDateTime.parse(faDateTime2))
                        .deleted(false)
                        .build();

                checkPaymentService.save(checkPayment);
                log.info("CheckPaymentServlet - CheckPayment Save");
                resp.sendRedirect("/checkPayment.do");
            }
            else {
                log.info("Invalid CashDesk");
                resp.sendRedirect("/checkPayment.do");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("checkPaymentList", checkPaymentService.findAll());
            req.getRequestDispatcher("/jsp/checkPayment.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id= Long.parseLong(req.getParameter("id"));
            checkPaymentService.removeById(id);

            log.info("CheckPaymentServlet - CheckPayment Edited");
            resp.sendRedirect("/checkPayment.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
