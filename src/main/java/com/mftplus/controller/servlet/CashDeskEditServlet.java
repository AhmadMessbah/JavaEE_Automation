package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.IdIsRequiredException;
import com.mftplus.controller.exception.NoContentException;
import com.mftplus.controller.validation.BeanValidator;
import com.mftplus.model.CashDesk;
import com.mftplus.model.User;
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
@WebServlet(urlPatterns = "/cashDeskEdit.do")
public class CashDeskEditServlet extends HttpServlet {

    @Inject
    private CashDeskServiceImp cashDeskService;

    @Inject
    private UserServiceImpl userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CashDeskEditServlet - Get");
        try {
            String idParam = req.getParameter("id");
            if (idParam == null) {
                throw new IdIsRequiredException("Please set Cash Desk id!");
            }
            Long id = Long.valueOf(idParam);
            log.info("Fetching CashDesk with id: {}", id);

            Optional<CashDesk> cashDesk = cashDeskService.findById(id);
            if (cashDesk.isPresent()) {
                req.getSession().setAttribute("cashDeskEdit", cashDesk.get());
                log.info("CashDesk found: {}", cashDesk.get());
            } else {
                log.warn("No CashDesk found with id: {}", id);
            }

            req.getSession().setAttribute("userList", userService.findAll());
            req.getRequestDispatcher("/jsp/form/edit/editCashDesk.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error("Error in CashDeskEditServlet: {}", e.getMessage());
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
                var validationErrors = validator.validate(cashDesk);
                if (validationErrors != null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write(validationErrors.toString());
                    return;
                }

                cashDeskService.edit(cashDesk);
                log.info("CashDesk edited successfully: {}", cashDesk);
                resp.setStatus(HttpServletResponse.SC_OK);
                String msg = "تغییرات با موفقیت ثبت شد!";
                req.getSession().setAttribute("ok", msg);
            } else {
                throw new NoContentException("The required user does not exist!");
            }
        } catch (Exception e) {
            log.error("Error in CashDeskEditServlet: {}", e.getMessage());
            throw new ServletException(e);
        }
    }
}
