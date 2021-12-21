package com.test.controller;

import com.test.exceptions.NotFoundException;
import com.test.model.Student;
import static com.test.service.StudentService.getInstance;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentLogin extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Login.html");
        /*response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Student student = null;

        HttpSession session = request.getSession();
        Object object = session.getAttribute("student");

        Integer count = (Integer) session.getAttribute("count");
        if (count == null) {
            session.setAttribute("count", 1);
            count = 0;
        } else {
            session.setAttribute("count", count + 1);
        }

        if (object != null) {
            if (object instanceof Student) {
                student = (Student) object;
                out.println("Welcome back " + student.getUsername());
                out.println("Counter in session : " + count);
            }
        }else {

            boolean s = false;
            StudentService ss = new StudentService();
            Cookie[] cookies = request.getCookies();

            if (cookies == null) {
                cookies = new Cookie[]{new Cookie("1", "1")};
            }
            for (Cookie a : cookies) {
                try {
                    if (a.getName().equals("username") && ss.getByUsername(a.getValue())) {
                        s = true;
                        break;
                    }
                } catch (NotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (s) {
                response.sendRedirect("userPage.html");
            } else {
                response.sendRedirect("Login.html");
            }
        }*/
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            Student student = new Student();
            // Set response content type
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();

            student.setUsername(request.getParameter("username"));
            student.setPassword(request.getParameter("password"));
            String rememberMe = request.getParameter("rememberMe");

            try {
                getInstance().loginH(student.getUsername(), student.getPassword());

                HttpSession session = request.getSession(true);
                session.setAttribute("student", student);
                session.setMaxInactiveInterval(10);

                if (rememberMe != null && rememberMe.equals("on")) {
                    Cookie cookie = new Cookie("username", student.getUsername());
                    cookie.setMaxAge(5000);
                    response.addCookie(cookie);
                }
                response.sendRedirect("/userPage.html");

            } catch (NotFoundException e) {
                out.println("USER NOT FOUND");
                e.printStackTrace();
            }


    }
}
