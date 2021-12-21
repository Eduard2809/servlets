package com.test.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.test.exceptions.DuplicateException;
import com.test.exceptions.NotFoundException;
import static com.test.service.StudentService.getInstance;
import com.test.model.Student;

public class StudentRegister extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Register.html");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Student student = new Student();
        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        student.setName(request.getParameter("name"));
        String ageString = request.getParameter("age");
        student.setAge(Integer.parseInt(ageString));
        student.setUsername(request.getParameter("username"));
        student.setPassword(request.getParameter("password"));

//        try {
            getInstance().saveH(student.getName(), student.getAge(),
                    student.getUsername(), student.getPassword());
            response.sendRedirect("Login.html");
//        } catch (DuplicateException e) {
//            out.println("DUPLICATE USERNAME");
//            e.printStackTrace();
//        }
    }
}
