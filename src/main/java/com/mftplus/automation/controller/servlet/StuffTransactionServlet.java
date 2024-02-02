package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Section;
import com.mftplus.automation.model.Stuff;
import com.mftplus.automation.model.StuffTransaction;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.StuffTransactionType;
import com.mftplus.automation.service.impl.SectionServiceImpl;
import com.mftplus.automation.service.impl.StuffServiceImpl;
import com.mftplus.automation.service.impl.StuffTransactionServiceImpl;
import com.mftplus.automation.service.impl.UserServiceImpl;
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
@WebServlet(name = "StuffTransactionServlet", urlPatterns = "/stuffTransaction.do")
public class StuffTransactionServlet extends HttpServlet {
    @Inject
    private StuffTransactionServiceImpl stuffTransactionService;
    @Inject
    private UserServiceImpl userService;
    @Inject
    private SectionServiceImpl sectionService;
    @Inject
    private StuffServiceImpl stuffService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Transaction Servlet-post");
        try {
            Optional<User> user = userService.findByUsername(req.getParameter("stuffTransactionUser"));
            Optional<Section> section = sectionService.findByTitle(req.getParameter("stuffTransactionSection"));
            Optional<Stuff> stuff = stuffService.findByName(req.getParameter("stuffTransactionStuff"));
            if (user.isPresent() && section.isPresent() && stuff.isPresent()) {
                StuffTransaction stuffTransaction = StuffTransaction
                        .builder()
                        .user(user.get())
                        .section(section.get())
                        .stuff(stuff.get())
                        .transactionType(StuffTransactionType.valueOf(req.getParameter("stuffTransactionType")))
                        .build();
                stuffTransactionService.save(stuffTransaction);
                log.info("Stuff Transaction Servlet-post");
            }

            req.getRequestDispatcher("/jsp/stuffTransaction.jsp").forward(req, resp);

        } catch (Exception e) {
            log.error(e.getMessage());

        }
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Transaction Servlet-put");
        try {

        }
        catch (Exception e) {

        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Transaction Servlet-Get");
        try {
            req.getRequestDispatcher("/jsp/stuffTransaction.jsp").forward(req, resp);


        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Transaction Servlet-delete");
        try {

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        }

}