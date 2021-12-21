package com.test.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import static com.test.service.StudentService.getInstance;
import com.test.model.Student;

public class StudentDelete extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Student student = new Student();
        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("DONE");
        String idString = request.getParameter("id");
        student.setId(Integer.parseInt(idString));
        getInstance().deleteH(student.getId());
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}