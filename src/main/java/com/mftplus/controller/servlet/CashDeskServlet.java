package com.mftplus.controller.servlet;

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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/cashDesk.do")
public class CashDeskServlet extends HttpServlet {
    @Inject
    private CashDeskService cashDeskService;

    @Inject
    private UserServiceImpl userService;

    @Valid
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CashDeskServlet - Post");
        try {
            req.setCharacterEncoding("utf-8");
            String name = req.getParameter("name");
            String cashDeskNumberStr = req.getParameter("cashDeskNumber");
            String cashBalanceStr = req.getParameter("cashBalance");
            String username = req.getParameter("username");
            Optional<User> userOptional = userService.findByUsername(username);
            System.out.println("User : " + userOptional.get());

            log.info("Received parameters: name={}, cashDeskNumber={}, cashBalance={}, username={}",
                    name, cashDeskNumberStr, cashBalanceStr, username);

            if (name == null || username.isEmpty() || cashDeskNumberStr == null || cashBalanceStr == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("All fields are required.");
            }

            assert cashDeskNumberStr != null;
            Long cashDeskNumber = Long.valueOf(cashDeskNumberStr);
            assert cashBalanceStr != null;
            Long cashBalance = Long.valueOf(cashBalanceStr);

            log.info("User found: {}", username);

            CashDesk cashDesk =
                    CashDesk
                            .builder()
                            .name(name)
                            .cashDeskNumber(cashDeskNumber)
                            .cashBalance(cashBalance)
                            .cashier(userOptional.get())
                            .deleted(false)
                            .build();
            // Validate the CashDesk entity
            BeanValidator<CashDesk> validator = new BeanValidator<>();
            String validationErrors = validator.validate(cashDesk).toString();
            if (!validationErrors.isEmpty()) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write(validationErrors);
            }
            System.out.println("CashDesk: " + cashDesk);

            cashDeskService.save(cashDesk);
            log.info("CashDesk saved successfully with ID: {}", cashDesk.getId());

            req.getSession().setAttribute("cashDeskId", cashDesk.getId());
            resp.sendRedirect("/cashDeskDisplay.do?id=" + cashDesk.getId());
            req.getSession().setAttribute("ok", "صندوق با موفقیت ثبت شد !");
        } catch (Exception e) {
            log.error("Error in CashDeskServlet POST: {}", e.getMessage(), e);
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("An unexpected error occurred: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CashDeskServlet - Get");
        try {
            req.getSession().setAttribute("cashDeskList", cashDeskService.findAll());
            req.getSession().setAttribute("userList", userService.findAll());
            req.getRequestDispatcher("/jsp/form/save/cashDesk-form.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error in CashDeskServlet GET: {}", e.getMessage(), e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("An unexpected error occurred: " + e.getMessage());
        }
    }
}
