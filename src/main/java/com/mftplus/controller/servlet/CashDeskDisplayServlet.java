package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.IdIsRequiredException;
import com.mftplus.model.CashDesk;
import com.mftplus.service.impl.CashDeskServiceImp;
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
@WebServlet(urlPatterns = "/cashDeskDisplay.do")
public class CashDeskDisplayServlet extends HttpServlet {
    @Inject
    private CashDeskServiceImp cashDeskService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CashDeskDisplayServlet - Get");
        try {
            if (req.getParameter("id") == null) {
                throw new IdIsRequiredException("Please set Cash Desk id !");
            } else {
                long id = Integer.parseInt(req.getParameter("id"));
                Optional<CashDesk> cashDesk = cashDeskService.findById(id);
                cashDesk.ifPresent(value -> req.getSession().setAttribute("cashDesk", value));

                req.getRequestDispatcher("/jsp/form/display/cashDesk.jsp").forward(req,resp);
                req.getSession().removeAttribute("ok");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}