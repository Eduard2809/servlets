package com.test.controller;

import com.test.model.Student;
import static com.test.service.StudentService.getInstance;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentChangeAge extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Student student = new Student();
        // Set response content type
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("DONE");
        String idString = request.getParameter("id");
        student.setId(Integer.parseInt(idString));
        String ageString = request.getParameter("age");
        student.setAge(Integer.parseInt(ageString));

        getInstance().changeAgeH(student.getAge(),student.getId());
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
