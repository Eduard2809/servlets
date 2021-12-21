package com.test.controller;

import com.test.exceptions.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutStudent extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        session.invalidate();

        Cookie[] cookies = request.getCookies();
        for (Cookie a : cookies){
                if (a.getName().equals("username")) {
                    a.setMaxAge(1);
                    response.addCookie(a);
                }
        }
        response.sendRedirect("homePage.html");
    }
}