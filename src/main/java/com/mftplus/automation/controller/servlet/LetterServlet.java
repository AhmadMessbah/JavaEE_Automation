package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Letter;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.FinancialDocType;
import com.mftplus.automation.model.enums.LetterAccessLevel;
import com.mftplus.automation.model.enums.LetterType;
import com.mftplus.automation.model.enums.TransferMethod;
import com.mftplus.automation.service.impl.LetterServiceImpl;
import com.mftplus.automation.service.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@WebServlet (urlPatterns = "/letter.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 10 MB
)
public class LetterServlet extends HttpServlet {

    @PersistenceContext(unitName = "automation")
    private EntityManager entityManager;

    @Inject
    private LetterServiceImpl letterService;

    @Inject
    private UserServiceImpl userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("LetterServlet - GET");

        req.getParameter("accessLevel");
        req.getSession().setAttribute("accessLevel", Arrays.asList(LetterAccessLevel.values()));
        req.getRequestDispatcher("/jsp/letter.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("LetterServlet - Post");
        try {
            //inputs
            String title = req.getParameter("title");
            String letterNumber = req.getParameter("letterNumber");
            String faDate = req.getParameter("faDate");
            String context = req.getParameter("context");
            String receiverName = req.getParameter("receiverName");
            String receiverTitle = req.getParameter("receiverTitle");
            String senderName = req.getParameter("senderName");
            String senderTitle = req.getParameter("senderTitle");
            //enum
            String accessLevel = req.getSession().getAttribute("accessLevel").toString();
            String carbonCopies = req.getParameter("carbonCopies");
            //getting username from session
            String username = req.getSession().getAttribute("username").toString();
            String transferMethod = req.getParameter("transferMethod");
            String letterType = req.getParameter("letterType");
            String registerNumber = req.getParameter("registerNumber");
            String indicatorCode = req.getParameter("indicatorCode");
            String refReceivers = req.getParameter("refReceiver");

            //for uploading letter image
            Part filePart = req.getPart("file");
            String fileName = filePart.getSubmittedFileName();
            for (Part part : req.getParts()) {
                part.write("c:\\root\\"+fileName); //todo set server path
            }
            resp.getWriter().print("The file uploaded successfully.");

            //verify
            if (context != null && receiverName != null &&
                    receiverTitle != null && senderName != null && senderTitle != null &&
                    accessLevel != null && username != null && letterType != null ){
                //todo
//                Secretariat indicator = new Secretariat();
//                indicator.setIndicatorCode(indicatorCode);

                //using username session to find user
                Optional<User> user = userService.findByUsername(username);
                if (user.isPresent()) {
                    //for register time
                    LocalDateTime localDateTime = LocalDateTime.now();
                    Letter letter =
                            Letter
                                    .builder()
                                    .title(title)
                                    .letterNumber(letterNumber)
                                    .date(LocalDate.parse(faDate))
                                    .context(context)
                                    .receiverName(receiverName)
                                    .receiverTitle(receiverTitle)
                                    .senderName(senderName)
                                    .senderTitle(senderTitle)
                                    .image(fileName)
                                    .accessLevel(LetterAccessLevel.valueOf(accessLevel))
                                    .user(user.get())
                                    .transferMethod(TransferMethod.valueOf(transferMethod))
                                    .letterType(LetterType.valueOf(letterType))
                                    .registerDateAndTime(localDateTime)
                                    .build();
                    letterService.save(letter);
                    log.info("LetterServlet - Letter Saved");
                    req.getSession().setAttribute("letter",letter);
                }
            }
        } catch (Exception e) {
            log.info("LetterServlet - Error Save Letter");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/letter.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String letterId = req.getParameter("id");

        try {
            letterService.removeById(Long.valueOf(letterId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.info("LetterServlet - Error Delete Letter By Id");
            req.getSession().setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/jsp/letter.jsp").forward(req, resp);
        }
    }
}
//todo indicator code
//todo letter number format
//todo carbonCopies
//todo refReceivers
//todo register number
//todo exception handling
