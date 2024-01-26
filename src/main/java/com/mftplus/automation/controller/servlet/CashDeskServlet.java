package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.CashDesk;
import com.mftplus.automation.model.User;
import com.mftplus.automation.service.impl.CashDeskServiceImp;
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
@WebServlet(name = "/CashDeskServlet", urlPatterns = "/CashDesk.do")
public class CashDeskServlet extends HttpServlet {
    @PersistenceContext(unitName = "automation")

    @Inject
    private CashDeskServiceImp cashDeskService;


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user=
                    User
                            .builder()
                            .username(req.getParameter("username"))
                            .password(req.getParameter("password"))
                            .build();
            CashDesk cashDesk=CashDesk
                    .builder()
                    .name(req.getParameter("name"))
                    .cashDeskNumber(Integer.parseInt(req.getParameter("cashDeskNumber")))
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
}
