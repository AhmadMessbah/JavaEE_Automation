package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.CashDesk;
import com.mftplus.automation.model.User;
import com.mftplus.automation.service.impl.CashDeskServiceImp;
import com.mftplus.automation.service.impl.UserServiceImpl;
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
@WebServlet(urlPatterns = "/cashDesk.do")
public class CashDeskServlet extends HttpServlet {
    @Inject
    private CashDeskServiceImp cashDeskService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private CashDesk cashDesk;


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            int cashDeskNumber = Integer.parseInt(req.getParameter("cashDeskNumber"));
            Long cashBalance = Long.valueOf(req.getParameter("cashBalance"));
            Optional<User> optionalUser = userService.findByUsername(req.getParameter("cashier"));
            if (optionalUser.isPresent()) {
                cashDesk = CashDesk
                        .builder()
                        .name(name)
                        .cashDeskNumber(cashDeskNumber)
                        .cashBalance(cashBalance)
                        .deleted(false)
                        .cashier(optionalUser.get())
                        .build();
            } else {
                log.info("Invalid Cashier");
            }
            cashDeskService.save(cashDesk);

            log.info("CashDeskServlet - CashDesk Saved");
            resp.sendRedirect("/cashDesk.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            int cashDeskNumber = Integer.parseInt(req.getParameter("cashDeskNumber"));
            Long cashBalance = Long.valueOf(req.getParameter("cashBalance"));
            Optional<User> optionalUser = userService.findByUsername(req.getParameter("cashier"));
            if (optionalUser.isPresent()) {
                cashDesk = CashDesk
                        .builder()
                        .name(name)
                        .cashDeskNumber(cashDeskNumber)
                        .cashBalance(cashBalance)
                        .deleted(false)
                        .cashier(optionalUser.get())
                        .build();
            } else {
                log.info("Invalid Cashier");
            }

            cashDeskService.edit(cashDesk);

            log.info("CashDeskServlet - CashDesk Edited");
            resp.sendRedirect("/cashDesk.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("cashDeskList", cashDeskService.findAll());
            req.getRequestDispatcher("/jsp/cashDesk.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            cashDeskService.removeById(id);

            log.info("CashDeskServlet - CashDesk Removed");
            resp.sendRedirect("/cashDesk.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
