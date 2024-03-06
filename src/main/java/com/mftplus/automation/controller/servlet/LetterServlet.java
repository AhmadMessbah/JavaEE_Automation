package com.mftplus.automation.controller.servlet;

import com.mftplus.automation.model.Letter;
import com.mftplus.automation.model.User;
import com.mftplus.automation.model.enums.LetterAccessLevel;
import com.mftplus.automation.model.enums.LetterType;
import com.mftplus.automation.model.enums.TransferMethod;
import com.mftplus.automation.service.impl.LetterServiceImpl;
import com.mftplus.automation.service.impl.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@WebServlet(urlPatterns = "/letter.do")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 10 MB
)
public class LetterServlet extends HttpServlet {
    @Inject
    private LetterServiceImpl letterService;

    @Inject
    private UserServiceImpl userService;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("LetterServlet - GET");

        try {
            if (req.getParameter("username") == null) {
                resp.sendRedirect("/login.do");
            }else {
                req.getSession().setAttribute("accessLevels", Arrays.asList(LetterAccessLevel.values()));
                req.getSession().setAttribute("transferMethods", Arrays.asList(TransferMethod.values()));
                req.getSession().setAttribute("letterTypes", Arrays.asList(LetterType.values()));
                req.getSession().setAttribute("letterList", letterService.findAll());
                req.getRequestDispatcher("/jsp/letter.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("LetterServlet - Post");
        try {
            //inputs
            String title = req.getParameter("l_title");
            String letterNumber = req.getParameter("l_letter_number");
            String faDate = req.getParameter("l_date").replace("/", "-");
            String context = req.getParameter("l_context");
            String receiverName = req.getParameter("l_receiver_name");
            String receiverTitle = req.getParameter("l_receiver_title");
            String senderName = req.getParameter("l_sender_name");
            String senderTitle = req.getParameter("l_sender_title");
            String accessLevel = req.getParameter("accessLevel");
            String transferMethod = req.getParameter("transferMethod");
            String letterType = req.getParameter("letterType");

            //getting username from session
            String username = req.getSession().getAttribute("username").toString();

            //for uploading letter image
            String fileName = null;
                Part filePart = req.getPart("file");
            if (filePart.getSize()>0) {
                fileName = filePart.getSubmittedFileName();
                for (Part part : req.getParts()) {
                    part.write(fileName); //todo set server path
                }
                resp.getWriter().print("The file uploaded successfully.");
            }
            //verify
//            if (username != null){
//            using username session to find user
                Optional<User> user = userService.findByUsername(username);
                if (user.isPresent()) {

             Letter letter =
                    Letter
                            .builder()
                            .user(user.get())
                            .title(title)
                            .letterNumber(letterNumber)
                            .context(context)
                            .receiverName(receiverName)
                            .receiverTitle(receiverTitle)
                            .senderName(senderName)
                            .senderTitle(senderTitle)
                            .image(fileName)
                            .deleted(false)
                            .faDate(faDate)
                            .accessLevel(LetterAccessLevel.valueOf(accessLevel))
                            .transferMethod(TransferMethod.valueOf(transferMethod))
                            .letterType(LetterType.valueOf(letterType))
                            .registerDateAndTime(LocalDateTime.now())
                            .build();
            letter.setFaDate(faDate);
            letterService.save(letter);
            log.info("LetterServlet - Letter Saved");
            req.getSession().setAttribute("letterId",letter.getId());
            resp.sendRedirect("/letter.do?selectedLetter="+letter.getId());
                }
//            }
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

//todo indicator code
//todo letter number format
//todo carbonCopies
//todo refReceivers
//todo register number
//todo exception handling
//todo test needed
