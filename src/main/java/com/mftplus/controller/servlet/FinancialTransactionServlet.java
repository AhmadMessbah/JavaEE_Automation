package com.mftplus.controller.servlet;

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
@WebServlet(urlPatterns = "/financialTransaction.do")
public class FinancialTransactionServlet extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

                financialTransactionService.save(financialTransaction);
                log.info("FinancialTransactionServlet - FinancialTransaction Saved");
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

                financialTransactionService.save(financialTransaction);
                log.info("FinancialTransactionServlet - FinancialTransaction Saved");
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

                financialTransactionService.save(financialTransaction);
                log.info("FinancialTransactionServlet - FinancialTransaction Saved");
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

                financialTransactionService.save(financialTransaction);
                log.info("FinancialTransactionServlet - FinancialTransaction Saved");
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

                financialTransactionService.save(financialTransaction);
                log.info("FinancialTransactionServlet - FinancialTransaction Saved");
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

                financialTransactionService.save(financialTransaction);
                log.info("FinancialTransactionServlet - FinancialTransaction Saved");
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

                financialTransactionService.save(financialTransaction);
                log.info("FinancialTransactionServlet - FinancialTransaction Saved");
            } else {
                log.info("Invalid Information");
            }
            resp.sendRedirect("/financialTransaction.do");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute("paymentTypes", Arrays.asList(PaymentType.values()));
            req.getSession().setAttribute("transactionTypes", Arrays.asList(FinancialTransactionType.values()));
            req.getSession().setAttribute("userList", userService.findAll());
            req.getSession().setAttribute("departmentList", departmentService.findAll());
            req.getSession().setAttribute("bankList", bankService.findAll());
            req.getSession().setAttribute("cashDeskList", cashDeskService.findAll());
            req.getSession().setAttribute("checkPaymentList", checkPaymentService.findAll());
            req.getSession().setAttribute("financialTransactionList", financialTransactionService.findAll());
            req.getRequestDispatcher("/jsp/financialTransaction.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            long id = Long.parseLong(req.getParameter("id"));
            financialTransactionService.removeById(id);

            log.info("FinancialTransactionServlet - FinancialTransaction Removed");
            resp.sendRedirect("/financialTransaction.do");
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}