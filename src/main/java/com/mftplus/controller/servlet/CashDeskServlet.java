package com.mftplus.controller.servlet;

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
import jakarta.validation.Valid;
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

    @Valid
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("CashDeskServlet - Post");
        try {
            String name = req.getParameter("name");
            int cashDeskNumber = Integer.parseInt(req.getParameter("cashDeskNumber"));
            Long cashBalance = Long.valueOf(req.getParameter("cashBalance"));
            String username = req.getParameter("username");
            System.out.println(username);
            log.info(username);
            Optional<User> userOptional = userService.findByUsername(username);
            System.out.println(userOptional);
            log.info(String.valueOf(userOptional));

            if (userOptional.isPresent()) {
                CashDesk cashDesk =
                        CashDesk
                                .builder()
                                .name(name)
                                .cashDeskNumber(cashDeskNumber)
                                .cashBalance(cashBalance)
                                .cashier(userOptional.get())
                                .deleted(false)
                                .build();

                //validate
                BeanValidator<CashDesk> validator = new BeanValidator<>();
                if (validator.validate(cashDesk) != null) {
                    resp.setStatus(500);
                    resp.getWriter().write(validator.validate(cashDesk).toString());
                }

                cashDeskService.save(cashDesk);
                log.info("CashDeskServlet - CashDesk Saved");
                req.getSession().setAttribute("cashDeskId", cashDesk.getId());
                resp.sendRedirect("/cashDeskDisplay.do?id=" + cashDesk.getId());
                String msg = "صندوق با موفقیت ثبت شد !";
                req.getSession().setAttribute("ok", msg);
            } else {
                throw new NoContentException("The required user does not exist !");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
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
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}