package com.mftplus.automation.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = "/jsp/*")
public class JspFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURI().toLowerCase().endsWith(".jsp")) {
            request.getSession().setAttribute("error", "No Access");
            response.sendRedirect ("/");
        }else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
