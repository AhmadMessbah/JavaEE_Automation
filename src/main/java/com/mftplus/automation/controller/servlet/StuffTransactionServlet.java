package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.service.impl.StuffTransactionServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
@WebServlet(name = "StuffTransactionServlet", urlPatterns = "/StuffTransaction.do")
public class StuffTransactionServlet extends HttpServlet {


    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Inject
    private StuffTransactionServiceImpl stuffTransactionService;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Transaction Servlet-post");
        try {

        }catch (Exception e){

        }
    }



    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Transaction Servlet-put");
        try {

        }catch (Exception e){

        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Transaction Servlet-get");
        try {

        }catch (Exception e){

        }
    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Stuff Transaction Servlet-delete");
        try {

        }catch (Exception e){

        }
    }
}