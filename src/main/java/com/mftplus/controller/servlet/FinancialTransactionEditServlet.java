package com.mftplus.controller.servlet;

import com.mftplus.controller.exception.IdIsRequiredException;
import com.mftplus.controller.exception.NoContentException;
import com.mftplus.controller.validation.BeanValidator;
import com.mftplus.model.*;
import com.mftplus.model.enums.FinancialTransactionType;
import com.mftplus.model.enums.PaymentType;
import com.mftplus.service.impl.*;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/financialTransactionEdit.do")
public class FinancialTransactionEditServlet extends HttpServlet {

    @Inject
    private FinancialTransactionServiceImpl financialTransactionService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private DepartmentServiceImp departmentService;

    @Inject
    private FinancialTransaction financialTransaction;

    @Inject
    private BankServiceImpl bankService;

    @Inject
    private CashDeskServiceImp cashDeskService;

    @Inject
    private CheckPaymentServiceImp checkPaymentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("FinancialTransactionEdit - Get");
        try {
            if (req.getParameter("id") == null) {
                throw new IdIsRequiredException("Please set FinancialTransaction id !");
            } else {
                Long id = Long.valueOf(req.getParameter("id"));
                Optional<FinancialTransaction> financialTransaction = financialTransactionService.findById(id);
                financialTransaction.ifPresent(value -> req.getSession().setAttribute("financialTransaction", value));

                req.getSession().setAttribute("paymentTypes", Arrays.asList(PaymentType.values()));
                req.getSession().setAttribute("transactionTypes", Arrays.asList(FinancialTransactionType.values()));
                req.getSession().setAttribute("userList", userService.findAll());
                req.getSession().setAttribute("departmentList", departmentService.findAll());
                req.getSession().setAttribute("bankList",bankService.findAll());
                req.getSession().setAttribute("cashDeskList",cashDeskService.findAll());
                req.getRequestDispatcher("/jsp/form/edit/editFinancialTransaction.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("FinancialTransactionEdit - Put");
        try {

            String username = req.getParameter("username");
            Optional<User> userOptional = userService.findByUsername(username);
            Long id = Long.valueOf(req.getParameter("dId"));
            Optional<Department> departmentOptional = departmentService.findById(id);
            Long bankId = Long.valueOf(req.getParameter("bankId"));
            Optional<Bank> bankOptional = bankService.findById(bankId);
            Long cashId = Long.valueOf(req.getParameter("cashId"));
            Optional<CashDesk> cashDeskOptional = cashDeskService.findById(cashId);
            Long checkId = Long.valueOf(req.getParameter("checkId"));
            Optional<CheckPayment> checkOptional = checkPaymentService.findById(checkId);

            //Card Payment Save
            if (userOptional.isPresent() && departmentOptional.isPresent() && bankOptional.isPresent() && cashDeskOptional.isEmpty() && checkOptional.isEmpty()) {

                String faDate = req.getParameter("date").replace("/", "-");
                int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
                String paymentType = req.getParameter("paymentType");
                String transactionType = req.getParameter("transactionType");
                Long bankAmount = Long.valueOf(req.getParameter("bankAmount"));
//                Long cashAmount = Long.valueOf(req.getParameter("cashAmount"));

                financialTransaction = FinancialTransaction
                        .builder()
                        .user(userOptional.get())
                        .referringDepartment(departmentOptional.get())
                        .paymentType(PaymentType.valueOf(paymentType))
                        .trackingCode(trackingCode)
                        .transactionType(FinancialTransactionType.valueOf(transactionType))
                        .faDate(faDate)
                        .bankAmount(bankAmount)
                        .bank(bankOptional.get())
                        .cashAmount(null)
                        .cashDesk(null)
                        .checkPayment(null)
                        .deleted(false)
                        .build();
                financialTransaction.setFaDate(faDate);

                // Validate the FinancialTransactionEditServlet
                BeanValidator<FinancialTransaction> validator = new BeanValidator<>();
                var validationErrors = validator.validate(financialTransaction);
                if (validationErrors != null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write(validationErrors.toString());
                    return;
                }

                financialTransactionService.edit(financialTransaction);
                log.info("FinancialTransaction edited successfully: {}", financialTransaction);
                resp.setStatus(HttpServletResponse.SC_OK);
                String msg = "تغییرات با موفقیت ثبت شد!";
                req.getSession().setAttribute("ok", msg);
            }

            //Cash Payment Save
            if (userOptional.isPresent() && departmentOptional.isPresent() && bankOptional.isEmpty() && cashDeskOptional.isPresent() && checkOptional.isEmpty()) {

                String faDate = req.getParameter("date").replace("/", "-");
                int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
                String paymentType = req.getParameter("paymentType");
                String transactionType = req.getParameter("transactionType");
//                Long bankAmount = Long.valueOf(req.getParameter("bankAmount"));
                Long cashAmount = Long.valueOf(req.getParameter("cashAmount"));

                financialTransaction = FinancialTransaction
                        .builder()
                        .user(userOptional.get())
                        .referringDepartment(departmentOptional.get())
                        .paymentType(PaymentType.valueOf(paymentType))
                        .trackingCode(trackingCode)
                        .transactionType(FinancialTransactionType.valueOf(transactionType))
                        .faDate(faDate)
                        .bankAmount(null)
                        .bank(null)
                        .cashAmount(cashAmount)
                        .cashDesk(cashDeskOptional.get())
                        .checkPayment(null)
                        .deleted(false)
                        .build();
                financialTransaction.setFaDate(faDate);

                // Validate the FinancialTransactionEditServlet
                BeanValidator<FinancialTransaction> validator = new BeanValidator<>();
                var validationErrors = validator.validate(financialTransaction);
                if (validationErrors != null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write(validationErrors.toString());
                    return;
                }

                financialTransactionService.edit(financialTransaction);
                log.info("FinancialTransaction edited successfully: {}", financialTransaction);
                resp.setStatus(HttpServletResponse.SC_OK);
                String msg = "تغییرات با موفقیت ثبت شد!";
                req.getSession().setAttribute("ok", msg);
            }

            //Check Payment Save
            if (userOptional.isPresent() && departmentOptional.isPresent() && bankOptional.isEmpty() && cashDeskOptional.isEmpty() && checkOptional.isPresent()) {

                String faDate = req.getParameter("date").replace("/", "-");
                int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
                String paymentType = req.getParameter("paymentType");
                String transactionType = req.getParameter("transactionType");
//                Long bankAmount = Long.valueOf(req.getParameter("bankAmount"));
//                Long cashAmount = Long.valueOf(req.getParameter("cashAmount"));

                financialTransaction = FinancialTransaction
                        .builder()
                        .user(userOptional.get())
                        .referringDepartment(departmentOptional.get())
                        .paymentType(PaymentType.valueOf(paymentType).checkPayment)
                        .trackingCode(trackingCode)
                        .transactionType(FinancialTransactionType.valueOf(transactionType))
                        .faDate(faDate)
                        .bankAmount(null)
                        .bank(null)
                        .cashAmount(null)
                        .cashDesk(null)
                        .checkPayment(checkOptional.get())
                        .deleted(false)
                        .build();
                financialTransaction.setFaDate(faDate);

                // Validate the FinancialTransactionEditServlet
                BeanValidator<FinancialTransaction> validator = new BeanValidator<>();
                var validationErrors = validator.validate(financialTransaction);
                if (validationErrors != null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write(validationErrors.toString());
                    return;
                }

                financialTransactionService.edit(financialTransaction);
                log.info("FinancialTransaction edited successfully: {}", financialTransaction);
                resp.setStatus(HttpServletResponse.SC_OK);
                String msg = "تغییرات با موفقیت ثبت شد!";
                req.getSession().setAttribute("ok", msg);
            }

            //Card And Cash Save
            if (userOptional.isPresent() && departmentOptional.isPresent() && bankOptional.isPresent() && cashDeskOptional.isPresent() && checkOptional.isEmpty()) {

                String faDate = req.getParameter("date").replace("/", "-");
                int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
                String paymentType = req.getParameter("paymentType");
                String transactionType = req.getParameter("transactionType");
                Long bankAmount = Long.valueOf(req.getParameter("bankAmount"));
                Long cashAmount = Long.valueOf(req.getParameter("cashAmount"));

                financialTransaction = FinancialTransaction
                        .builder()
                        .user(userOptional.get())
                        .referringDepartment(departmentOptional.get())
                        .paymentType(PaymentType.valueOf(paymentType).cardAndCash)
                        .trackingCode(trackingCode)
                        .transactionType(FinancialTransactionType.valueOf(transactionType))
                        .faDate(faDate)
                        .bankAmount(bankAmount)
                        .bank(bankOptional.get())
                        .cashAmount(cashAmount)
                        .cashDesk(cashDeskOptional.get())
                        .checkPayment(null)
                        .deleted(false)
                        .build();
                financialTransaction.setFaDate(faDate);

                // Validate the FinancialTransactionEditServlet
                BeanValidator<FinancialTransaction> validator = new BeanValidator<>();
                var validationErrors = validator.validate(financialTransaction);
                if (validationErrors != null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write(validationErrors.toString());
                    return;
                }

                financialTransactionService.edit(financialTransaction);
                log.info("FinancialTransaction edited successfully: {}", financialTransaction);
                resp.setStatus(HttpServletResponse.SC_OK);
                String msg = "تغییرات با موفقیت ثبت شد!";
                req.getSession().setAttribute("ok", msg);
            }

            //Card And Check Save
            if (userOptional.isPresent() && departmentOptional.isPresent() && bankOptional.isPresent() && cashDeskOptional.isEmpty() && checkOptional.isPresent()) {

                String faDate = req.getParameter("date").replace("/", "-");
                int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
                String paymentType = req.getParameter("paymentType");
                String transactionType = req.getParameter("transactionType");
                Long bankAmount = Long.valueOf(req.getParameter("bankAmount"));
//                Long cashAmount = Long.valueOf(req.getParameter("cashAmount"));

                financialTransaction = FinancialTransaction
                        .builder()
                        .user(userOptional.get())
                        .referringDepartment(departmentOptional.get())
                        .paymentType(PaymentType.valueOf(paymentType).cardAndCheck)
                        .trackingCode(trackingCode)
                        .transactionType(FinancialTransactionType.valueOf(transactionType))
                        .faDate(faDate)
                        .bankAmount(bankAmount)
                        .bank(bankOptional.get())
                        .cashAmount(null)
                        .cashDesk(null)
                        .checkPayment(checkOptional.get())
                        .deleted(false)
                        .build();
                financialTransaction.setFaDate(faDate);

                // Validate the FinancialTransactionEditServlet
                BeanValidator<FinancialTransaction> validator = new BeanValidator<>();
                var validationErrors = validator.validate(financialTransaction);
                if (validationErrors != null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write(validationErrors.toString());
                    return;
                }

                financialTransactionService.edit(financialTransaction);
                log.info("FinancialTransaction edited successfully: {}", financialTransaction);
                resp.setStatus(HttpServletResponse.SC_OK);
                String msg = "تغییرات با موفقیت ثبت شد!";
                req.getSession().setAttribute("ok", msg);
            }

            //Cash And Check
            if (userOptional.isPresent() && departmentOptional.isPresent() && bankOptional.isEmpty() && cashDeskOptional.isPresent() && checkOptional.isPresent()) {

                String faDate = req.getParameter("date").replace("/", "-");
                int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
                String paymentType = req.getParameter("paymentType");
                String transactionType = req.getParameter("transactionType");
//                Long bankAmount = Long.valueOf(req.getParameter("bankAmount"));
                Long cashAmount = Long.valueOf(req.getParameter("cashAmount"));

                financialTransaction = FinancialTransaction
                        .builder()
                        .user(userOptional.get())
                        .referringDepartment(departmentOptional.get())
                        .paymentType(PaymentType.valueOf(paymentType).cashAndCheck)
                        .trackingCode(trackingCode)
                        .transactionType(FinancialTransactionType.valueOf(transactionType))
                        .faDate(faDate)
                        .bankAmount(null)
                        .bank(null)
                        .cashAmount(cashAmount)
                        .cashDesk(cashDeskOptional.get())
                        .checkPayment(checkOptional.get())
                        .deleted(false)
                        .build();
                financialTransaction.setFaDate(faDate);

                // Validate the FinancialTransactionEditServlet
                BeanValidator<FinancialTransaction> validator = new BeanValidator<>();
                var validationErrors = validator.validate(financialTransaction);
                if (validationErrors != null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write(validationErrors.toString());
                    return;
                }

                financialTransactionService.edit(financialTransaction);
                log.info("FinancialTransaction edited successfully: {}", financialTransaction);
                resp.setStatus(HttpServletResponse.SC_OK);
                String msg = "تغییرات با موفقیت ثبت شد!";
                req.getSession().setAttribute("ok", msg);
            }

            //Card And Cash And Check
            if (userOptional.isPresent() && departmentOptional.isPresent() && bankOptional.isPresent() && cashDeskOptional.isPresent() && checkOptional.isPresent()) {

                String faDate = req.getParameter("date").replace("/", "-");
                int trackingCode = Integer.parseInt(req.getParameter("trackingCode"));
                String paymentType = req.getParameter("paymentType");
                String transactionType = req.getParameter("transactionType");
                Long bankAmount = Long.valueOf(req.getParameter("bankAmount"));
                Long cashAmount = Long.valueOf(req.getParameter("cashAmount"));

                financialTransaction = FinancialTransaction
                        .builder()
                        .user(userOptional.get())
                        .referringDepartment(departmentOptional.get())
                        .paymentType(PaymentType.valueOf(paymentType).cardAndCashAndCheck)
                        .trackingCode(trackingCode)
                        .transactionType(FinancialTransactionType.valueOf(transactionType))
                        .faDate(faDate)
                        .bankAmount(bankAmount)
                        .bank(bankOptional.get())
                        .cashAmount(cashAmount)
                        .cashDesk(cashDeskOptional.get())
                        .checkPayment(checkOptional.get())
                        .deleted(false)
                        .build();
                financialTransaction.setFaDate(faDate);

                // Validate the FinancialTransactionEditServlet
                BeanValidator<FinancialTransaction> validator = new BeanValidator<>();
                var validationErrors = validator.validate(financialTransaction);
                if (validationErrors != null) {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write(validationErrors.toString());
                    return;
                }

                financialTransactionService.edit(financialTransaction);
                log.info("FinancialTransaction edited successfully: {}", financialTransaction);
                resp.setStatus(HttpServletResponse.SC_OK);
                String msg = "تغییرات با موفقیت ثبت شد!";
                req.getSession().setAttribute("ok", msg);
            } else {
                throw new NoContentException("Invalid Information");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            resp.setStatus(500);
            resp.getWriter().write("An error occurred: " + e.getMessage());
        }
    }
}
