package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.IdIsRequiredException;
import com.mftplus.model.CashDesk;
import com.mftplus.service.impl.CashDeskServiceImp;
import com.mftplus.service.impl.UserServiceImpl;
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

    @Inject
    private UserServiceImpl userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CashDeskDisplayServlet - Get");
//        System.out.println("CashDeskDisplayServlet - Get");

        try {
            String idParam = req.getParameter("id");
            if (idParam == null) {
                throw new IdIsRequiredException("Please set Cash Desk id !");
            } else {
                Long id = Long.valueOf(idParam);
                log.info("Fetching CashDesk with id: {}", id);

                Optional<CashDesk> cashDesk = cashDeskService.findById(id);
                if (cashDesk.isPresent()) {
//                    System.out.println("CASH DESK DISPLAY CASH DESK " + cashDesk);
                    req.getSession().setAttribute("cashDesk", cashDesk.get());
                    log.info("CashDesk found: {}", cashDesk.get());
                } else {
                    log.warn("No CashDesk found with id: {}", id);
                }

                req.getSession().setAttribute("userList", userService.findAll());
                req.getRequestDispatcher("/jsp/form/display/cashDesk.jsp").forward(req, resp);
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