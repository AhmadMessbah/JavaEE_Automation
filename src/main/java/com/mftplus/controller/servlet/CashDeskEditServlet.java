package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.IdIsRequiredException;
import com.mftplus.controller.validation.BeanValidator;
import com.mftplus.model.CashDesk;
import com.mftplus.model.User;
import com.mftplus.service.CashDeskService;
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
@WebServlet(urlPatterns = "/cashDeskEdit.do")
public class CashDeskEditServlet extends HttpServlet {
    @Inject
    private CashDeskService cashDeskService;

    @Inject
    private UserServiceImpl userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CashDeskEditServlet - Get");
        try {
            if (req.getParameter("id") == null) {
                throw new IdIsRequiredException("Please set Cash Desk id!");
            } else {
                Long id = Long.valueOf(req.getParameter("id"));
                Optional<CashDesk> cashDesk = cashDeskService.findById(id);
                cashDesk.ifPresent(value -> req.getSession().setAttribute("cashDesk", value));

                req.getSession().setAttribute("userList", userService.findAll());
                req.getRequestDispatcher("/jsp/form/edit/editCashDesk.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CashDeskEditServlet - Put");
        try {
            Long id = Long.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");
            Long cashDeskNumber = Long.valueOf(req.getParameter("cashDeskNumber"));
            Long cashBalance = Long.valueOf(req.getParameter("cashBalance"));
            String username = req.getParameter("username");
            Optional<User> user = userService.findByUsername(username);

            if (user.isPresent()) {
                CashDesk cashDesk = CashDesk.builder()
                        .id(id)
                        .name(name)
                        .cashDeskNumber(cashDeskNumber)
                        .cashBalance(cashBalance)
                        .cashier(user.get())
                        .deleted(false)
                        .build();

                // Validate the cash desk
                BeanValidator<CashDesk> validator = new BeanValidator<>();
                String validationResult = String.valueOf(validator.validate(cashDesk));
                if (validationResult != null && !validationResult.isEmpty()) {
                    log.error("Validation failed: {}", validationResult);
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write(validationResult);
                }

                cashDeskService.edit(cashDesk);
                log.info("CashDesk edited successfully with ID: {}", cashDesk.getId());
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("User not found.");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("An unexpected error occurred: " + e.getMessage());
        }
    }
}
