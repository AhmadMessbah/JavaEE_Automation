package com.mftplus.controller.servlet;

import com.mftplus.model.*;
import com.mftplus.model.enums.Gender;
import com.mftplus.service.impl.*;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;

@Slf4j
public class LoginServlet extends HttpServlet {
    @Inject
    private OrganisationServiceImpl organisationService;

    @Inject
    private DepartmentServiceImp departmentService;

    @Inject
    private UserServiceImpl userService;

    @Inject
    private RolesServiceImpl rolesService;

    @Inject
    private PersonServiceImpl personService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("LoginServlet - Get");
        try {
            if (req.getUserPrincipal() != null) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home.do");
                dispatcher.forward(req, resp);
            }
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Init");
//        log.info("LoginServlet - Init");

        try {
            Organisation organisation =
                    Organisation
                            .builder()
                            .name("MFT")
                            .title("مجتمع فنی تهران")
                            .deleted(false)
                            .build();
            organisationService.save(organisation);
            System.out.println(organisation);


            Department department1 =
                    Department
                            .builder()
                            .title("فناوری اطلاعات و ارتباطات")
                            .duty("آموزشی")
                            .organisation(organisation)
                            .deleted(false)
                            .build();

            departmentService.save(department1);
            System.out.println(department1);


            Department department2 =
                    Department
                            .builder()
                            .title("برق و الکترونیک")
                            .duty("آموزشی")
                            .organisation(organisation)
                            .deleted(false)
                            .build();


            departmentService.save(department2);
            System.out.println(department2);

//            organisation.addDepartment(department1);
//            organisation.addDepartment(department2);
//            organisationService.edit(organisation);

            User admin =
                    User
                            .builder()
                            .username("admin")
                            .password("admin123")
                            .department(department1)
                            .deleted(false)
                            .build();

            userService.save(admin);
            System.out.println(admin);

            User user =
                    User
                            .builder()
                            .username("user")
                            .password("user123")
                            .department(department2)
                            .deleted(false)
                            .build();

            userService.save(user);
            System.out.println(user);

            Roles adminRole =
                    Roles
                            .builder()
                            .role("admin")
                            .user(admin)
                            .deleted(false)
                            .build();

            rolesService.save(adminRole);
            System.out.println(adminRole);

            Roles userRole =
                    Roles
                            .builder()
                            .role("user")
                            .user(user)
                            .deleted(false)
                            .build();

            rolesService.save(userRole);
            System.out.println(userRole);

            admin.addRole(adminRole);
            userService.edit(admin);

            user.addRole(userRole);
            userService.edit(user);

            Person person1 =
                    Person
                            .builder()
                            .name("Ahmad")
                            .family("Messbah")
                            .gender(Gender.male)
                            .nationalCode("1111111111")
                            .birthdate(LocalDate.now())
                            .user(admin)
                            .deleted(false)
                            .build();

            personService.save(person1);
            System.out.println(person1);

            Person person2 =
                    Person
                            .builder()
                            .name("Ali")
                            .family("Alawi")
                            .gender(Gender.male)
                            .nationalCode("2222222222")
                            .birthdate(LocalDate.now())
                            .user(user)
                            .deleted(false)
                            .build();

            personService.save(person2);
            System.out.println(person2);


        } catch (Exception e) {
            e.printStackTrace();
//            log.error(e.getMessage());
            System.out.println("ERROR : " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        session.setAttribute("username", req.getParameter("username"));
        session.setAttribute("password", req.getParameter("password"));
        resp.sendRedirect(resp.encodeRedirectURL("/home.do"));
    }
}