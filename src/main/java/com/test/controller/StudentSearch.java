package com.test.controller;

import com.test.model.Student;
import static com.test.service.StudentService.getInstance;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StudentSearch extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Student student = new Student();
        // Set response content type
        response.setContentType("text/html");

        String idString = request.getParameter("id");
        student.setId(Integer.parseInt(idString));
        getInstance().searchH(student,student.getId());

        PrintWriter out = response.getWriter();
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";

        out.println(docType +
                "<html>\n" +
                "<head><title>" + "Table" + "</title></head>\n" +
                "<body bgcolor = \"#f0f0f0\">\n" +
                "<h1 align = \"center\">" + "Student Table" + "</h1>\n" +
                "<table border='1' width = '400' style = 'text-align: center'>" +
                "<tr>" +
                "  <th>Id</th> " +
                "  <th>name</th> " +
                "  <th>age</th> " +
                "</tr>");
            out.println("<tr><td>" + student.getId() + "</td><td>" + student.getName() +
                    "</td><td>" + student.getAge() + "</td></tr>");

        out.println("</table>\n" +
                "</body>" +
                "</html>");

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
