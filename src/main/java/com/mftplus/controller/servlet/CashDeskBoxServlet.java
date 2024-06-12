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
@WebServlet(urlPatterns = "/cashDeskBox.do")
public class CashDeskBoxServlet extends HttpServlet {
    @Inject
    private CashDeskServiceImp cashDeskService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CashDeskBoxServlet - Get");

        try {
            String name = req.getUserPrincipal().getName();
            log.info("Fetching cash desks for user: {}", name);

            req.getSession().setAttribute("cashDeskListByName", cashDeskService.findByName(name));
            log.info("Cash desks fetched successfully for user: {}", name);

            req.getRequestDispatcher("/jsp/table/cashDesk-box.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error in CashDeskBoxServlet: {}", e.getMessage());
            throw new ServletException(e);
        }
    }
}
