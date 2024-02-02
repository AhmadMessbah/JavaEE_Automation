package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.CashDesk;
import com.mftplus.automation.model.User;
import com.mftplus.automation.service.impl.CashDeskServiceImp;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet(name = "/CashDeskServlet", urlPatterns = "/CashDesk.do")
public class CashDeskServlet extends HttpServlet {
    @Inject
    private CashDeskServiceImp cashDeskService;

    @Inject
    private User user;

    @Inject
    private  CashDesk cashDesk;


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
            int cashDeskNumber=Integer.parseInt(req.getParameter("cashDeskNumber"));

            cashDesk=CashDesk
                    .builder()
                    .name(name)
                    .cashDeskNumber(cashDeskNumber)
                    .cashier(user)
                    .build();

            cashDeskService.save(cashDesk);

            log.info("CashDeskServlet - CashDesk Saved");
            req.getRequestDispatcher("/jsp/CashDesk.jsp").forward(req, resp);
        } catch (Exception e) {
            log.info("CashDeskServlet - Error Save CashDesk");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/CashDesk.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("cashDeskList", cashDeskService.findAll());
            req.getRequestDispatcher("/jsp/cashDesk.jsp").forward(req, resp);
            cashDeskService.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
